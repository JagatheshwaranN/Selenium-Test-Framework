package com.qa.stf.helper;

import com.qa.stf.base.WebPage;
import com.qa.stf.handler.BaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.qa.stf.base.BasePage;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Collections;

public class InteractionHandler extends BasePage implements WebPage {

    private static final Logger log = LogManager.getLogger(InteractionHandler.class);

    private final Actions builder;
    private final VerificationHandler verificationHandler;

    public InteractionHandler(VerificationHandler verificationHandler) {
        this.builder = new Actions(driverManager.getDriver());
        this.verificationHandler = verificationHandler;
    }

    /**
     * Clicks on the specified WebElement using Actions.
     * <p>
     * Verifies the visibility of the element before performing the click operation.
     * </p>
     *
     * @param element      The WebElement to be clicked.
     * @param elementLabel The label describing the element.
     */
    @Override
    public void clickElement(WebElement element, String elementLabel) {
        if (verificationHandler.isElementDisplayed(element, elementLabel)) {
            builder.click(element).perform();
            log.info("Clicked the '{}' element using Actions", elementLabel);
        }
    }

    /**
     * Clicks on an element located dynamically using a provided locator and value.
     * <p>
     * Constructs a dynamic locator using the value, retrieves the element, verifies its
     * visibility, and performs the click operation using Actions.
     * </p>
     *
     * @param locator      The dynamic locator for the element.
     * @param value        The value to substitute in the locator.
     * @param elementLabel The label describing the element.
     * @throws BaseException.InteractionException If an error occurs while click on an
     *                                            element.
     */
    @Override
    public void clickElement(By locator, String value, String elementLabel) {
        try {
            WebElement element = driverManager.getDriver().findElement(By.xpath(String.format(locator.toString().replace("By.xpath: ", ""), value)));
            if (verificationHandler.isElementDisplayed(element, elementLabel)) {
                builder.click(element).perform();
                log.info("Clicked the '{}' element using Actions", elementLabel);
            }
        } catch (ElementClickInterceptedException ex) {
            log.error("Failed to click the '{}' element using Actions", elementLabel, ex);
            throw new BaseException.InteractionException("Exception occurred while clicking " + elementLabel + " element using JavaScriptExecutor", ex);
        }
    }

    /**
     * Types text into the specified WebElement using Actions.
     * <p>
     * Verifies the visibility of the element before performing the type operation.
     * </p>
     *
     * @param element      The WebElement to enter text into.
     * @param text         The text to be entered.
     * @param elementLabel The label describing the element.
     */
    @Override
    public void typeElement(WebElement element, String text, String elementLabel) {
        if (verificationHandler.isElementDisplayed(element, elementLabel)) {
            if (text != null) {
                builder.sendKeys(element, text).perform();
                log.info("Entered '{}' text into the '{}' element using Actions", text, elementLabel);
            }
        }
    }

    /**
     * Clears the content of the specified WebElement using Actions.
     * <p>
     * Verifies the visibility of the element before clearing its content.
     * </p>
     *
     * @param element      The WebElement to clear.
     * @param elementLabel The label describing the element.
     */
    @Override
    public void clearElement(WebElement element, String elementLabel) {
        if (verificationHandler.isElementDisplayed(element, elementLabel)) {
            log.info("Cleared the content of '{}' element using Actions", elementLabel);
        }
    }

    public void mouseHover(WebElement element, String elementLabel) {
        if (verificationHandler.isElementDisplayed(element, elementLabel)) {
            builder.moveToElement(element).perform();
            log.info("The mouse hovered and clicked on the '{}' element", elementLabel);
        }
    }

    public void mouseRightClick(WebElement element, String elementLabel) {
        if (verificationHandler.isElementDisplayed(element, elementLabel)) {
            builder.contextClick(element).perform();
            log.info("The mouse right clicked on the '{}' element", elementLabel);
        }
    }

    public void mouseDoubleClick(WebElement element, String elementLabel) {
        if (verificationHandler.isElementDisplayed(element, elementLabel)) {
            builder.doubleClick(element).perform();
            log.info("The mouse double clicked on the '{}' element", elementLabel);
        }
    }

    public void mouseHoverAndClick(WebElement element, String elementLabel) {
        if (verificationHandler.isElementDisplayed(element, elementLabel)) {
            mouseHover(element, elementLabel);
            builder.click(element).perform();
            log.info("The mouse hovered and clicked on the '{}' element", elementLabel);
        }
    }

    public void mouseClickAndRelease(WebElement element, String elementLabel) {
        if (verificationHandler.isElementDisplayed(element, elementLabel)) {
            builder.click(element)
                    .release(element)
                    .perform();
            log.info("The mouse click and released on the '{}' element", elementLabel);
        }
    }

    public void mouseClickAndHold(WebElement element, String elementLabel) {
        if (verificationHandler.isElementDisplayed(element, elementLabel)) {
            builder.clickAndHold(element).perform();
            log.info("The mouse clicked and hold on the '{}' element", elementLabel);
        }
    }

    public void dragAndDropElement(WebElement draggableElement, WebElement droppableElement, String elementLabel1, String elementLabel2) {
        if (verificationHandler.isElementDisplayed(draggableElement, elementLabel1) && verificationHandler.isElementDisplayed(droppableElement, elementLabel2)) {
            builder.clickAndHold(draggableElement)
                    .moveToElement(droppableElement)
                    .release(droppableElement)
                    .perform();
            log.info("The '{}' element is drag and dropped on the '{}' element", elementLabel1, elementLabel2);
        }
    }

    public void dragAndDropElementUsingBuiltin(WebElement draggableElement, WebElement droppableElement, String elementLabel1, String elementLabel2) {
        if (verificationHandler.isElementDisplayed(draggableElement, elementLabel1) && verificationHandler.isElementDisplayed(droppableElement, elementLabel2)) {
            builder.dragAndDrop(draggableElement, droppableElement).perform();
            log.info("The '{}' element is drag and dropped on the '{}' element", elementLabel1, elementLabel2);
        }
    }

    public void mouseBackClick() {
        PointerInput mouse = new PointerInput(PointerInput.Kind.MOUSE, "Default Mouse");
        Sequence sequence = new Sequence(mouse, 0)
                .addAction(mouse.createPointerDown(PointerInput.MouseButton.BACK.asArg()))
                .addAction(mouse.createPointerUp(PointerInput.MouseButton.BACK.asArg()));
        ((RemoteWebDriver) driverManager.getDriver()).perform(Collections.singletonList(sequence));
        log.info("The mouse clicked on the back button");
    }

    public void mouseForwardClick() {
        PointerInput mouse = new PointerInput(PointerInput.Kind.MOUSE, "Default Mouse");
        Sequence sequence = new Sequence(mouse, 0)
                .addAction(mouse.createPointerDown(PointerInput.MouseButton.FORWARD.asArg()))
                .addAction(mouse.createPointerUp(PointerInput.MouseButton.FORWARD.asArg()));
        ((RemoteWebDriver) driverManager.getDriver()).perform(Collections.singletonList(sequence));
        log.info("The mouse clicked on the forward button");
    }

}

