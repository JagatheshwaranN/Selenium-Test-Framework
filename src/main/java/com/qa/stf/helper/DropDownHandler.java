package com.qa.stf.helper;

import java.util.List;
import java.util.stream.Collectors;

import com.qa.stf.handler.BaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.qa.stf.base.BasePage;

/**
 * The DropDownHandler class provides utility methods to interact with dropdown
 * elements in Selenium WebDriver. These methods allow selecting options from
 * a dropdown by value, index, visible text, or a list of available options.
 * The class also provides methods for retrieving the selected value and
 * fetching all available options from a dropdown.
 *
 * <p>Features:
 * <ul>
 *   <li>Option Selection: Methods to select dropdown options by value, index, visible
 *   text, or from a list of options.</li>
 *   <li>Retrieve Selected Value: A method to retrieve the currently selected value
 *   from the dropdown.</li>
 *   <li>Get All Options: A method to retrieve all available options from the dropdown.</li>
 * </ul>
 *
 * <p>Exception Handling:
 * <ul>
 *   <li>Custom exceptions from the {@link com.qa.stf.handler.BaseException} class
 *       are thrown for more descriptive error handling.</li>
 *   <li>Detailed logging is provided for successful operations and error scenarios.</li>
 * </ul>
 *
 * <p>Note:
 * The class assumes proper WebDriver setup and element interactions. Ensure
 * the dropdown is visible before attempting to select or retrieve options to
 * avoid interaction errors.
 *
 * <p>Example:
 * <pre>
 * {@code
 * DropDownHandler dropDownHelper = new DropDownHandler(verificationHandler);
 * WebElement dropdown = driver.findElement(By.id("dropdown"));
 * dropDownHelper.selectByValue(dropdown, "value", "Dropdown Element");
 * String selectedValue = dropDownHelper.getSelectedValue(dropdown, "Dropdown Element");
 * List<String> allOptions = dropDownHelper.getAllDropdownValues(dropdown, "Dropdown Element");
 * }
 * </pre>
 *
 * @author Jagatheshwaran N
 * @version 1.1
 */
public class DropDownHandler extends BasePage {

    private static final Logger log = LogManager.getLogger(DropDownHandler.class);

    private final VerificationHandler verificationHandler;

    public DropDownHandler(VerificationHandler verificationHandler) {
        this.verificationHandler = verificationHandler;
    }

    /**
     * Selects an option from the dropdown based on its value attribute.
     * <p>
     * This method attempts to select an option from the dropdown by matching the
     * value attribute. If the dropdown element is not displayed, no action is performed.
     * </p>
     *
     * @param dropdown     The WebElement representing the dropdown.
     * @param value        The value attribute of the option to be selected.
     * @param elementLabel The label for logging purposes.
     * @throws BaseException.OptionNotFoundException If the specified value is not found
     *                                               in the dropdown.
     */
    public void selectByValue(WebElement dropdown, String value, String elementLabel) {
        try {
            if (verificationHandler.isElementDisplayed(dropdown, elementLabel)) {
                var select = new Select(dropdown);
                select.selectByValue(value);
                log.info("The value '{}' is selected from the '{}' dropdown", value, elementLabel);
            }
        } catch (Exception ex) {
            log.error("Exception occurred while selecting value '{}' in the '{}' dropdown", value, elementLabel, ex);
            throw new BaseException.OptionNotFoundException(value, elementLabel, ex);
        }
    }

    /**
     * Selects an option from the dropdown based on its index.
     * <p>
     * This method attempts to select an option from the dropdown by its index.
     * If the dropdown element is not displayed, no action is performed.
     * </p>
     *
     * @param dropdown     The WebElement representing the dropdown.
     * @param index        The index of the option to be selected (0-based).
     * @param elementLabel The label for logging purposes.
     * @throws BaseException.OptionNotFoundException If the specified index is not found
     *                                               in the dropdown.
     */
    public void selectByIndex(WebElement dropdown, int index, String elementLabel) {
        try {
            if (verificationHandler.isElementDisplayed(dropdown, elementLabel)) {
                var select = new Select(dropdown);
                select.selectByIndex(index);
                log.info("The value at index '{}' is selected from the '{}' dropdown", index, elementLabel);
            }
        } catch (Exception ex) {
            log.error("Exception occurred while selecting index '{}' in the '{}' dropdown", index, elementLabel, ex);
            throw new BaseException.OptionNotFoundException("Index " + index, elementLabel, ex);
        }
    }

    /**
     * Selects an option from the dropdown based on its visible text.
     * <p>
     * This method attempts to select an option from the dropdown by its visible text.
     * If the dropdown element is not displayed, no action is performed.
     * </p>
     *
     * @param dropdown     The WebElement representing the dropdown.
     * @param visibleText  The visible text of the option to be selected.
     * @param elementLabel The label for logging purposes.
     * @throws BaseException.OptionNotFoundException If the specified visible text is not
     *                                               found in the dropdown.
     */
    public void selectByVisibleText(WebElement dropdown, String visibleText, String elementLabel) {
        try {
            if (verificationHandler.isElementDisplayed(dropdown, elementLabel)) {
                var select = new Select(dropdown);
                select.selectByVisibleText(visibleText);
                log.info("The visible text '{}' is selected from the '{}' dropdown", visibleText, elementLabel);
            }
        } catch (Exception ex) {
            log.error("Exception occurred while selecting visible text '{}' in the '{}' dropdown", visibleText, elementLabel, ex);
            throw new BaseException.OptionNotFoundException(visibleText, elementLabel, ex);
        }
    }

    /**
     * Selects an option from the dropdown based on a list of options.
     * <p>
     * This method attempts to click on the dropdown and select the specified option
     * from a list of available options. If the dropdown or the option is not found,
     * a BaseException is thrown.
     * </p>
     *
     * @param dropdown     The WebElement representing the dropdown.
     * @param optionsList  A list of all available options in the dropdown.
     * @param value        The value of the option to be selected.
     * @param elementLabel The label for logging purposes.
     * @throws BaseException.OptionNotFoundException If the specified value is not found in
     *                                               the dropdown options.
     */
    public void selectDropdownOption(WebElement dropdown, List<WebElement> optionsList, String value, String elementLabel) {
        if (!verificationHandler.isElementDisplayed(dropdown, elementLabel)) {
            throw new BaseException.ElementNotFoundException(elementLabel);
        }
        try {
            dropdown.click();
            WebElement option = optionsList.stream()
                    .filter(opt -> opt.getText().equalsIgnoreCase(value))
                    .findFirst()
                    .orElseThrow(() -> {
                        log.error("'{}' option not found in the '{}' dropdown", value, elementLabel);
                        return new BaseException.OptionNotFoundException(value, elementLabel);
                    });
            option.click();
            log.info("The option '{}' is selected from the '{}' dropdown", value, elementLabel);
        } catch (Exception ex) {
            log.error("Exception occurred while selecting option '{}' in the '{}' dropdown", value, elementLabel, ex);
            throw new BaseException.OptionNotFoundException(value, elementLabel, ex);
        }
    }

    /**
     * Retrieves the currently selected value from the dropdown.
     * <p>
     * This method attempts to retrieve the value of the selected option from the dropdown.
     * If the dropdown is not displayed, null is returned.
     * </p>
     *
     * @param dropdown     The WebElement representing the dropdown.
     * @param elementLabel The label for logging purposes.
     * @return The selected value from the dropdown.
     */
    public String getSelectedValue(WebElement dropdown, String elementLabel) {
        String value = null;
        if (verificationHandler.isElementDisplayed(dropdown, elementLabel)) {
            value = new Select(dropdown).getFirstSelectedOption().getText();
        }
        log.info("The '{}' option is selected in the '{}' dropdown", value, elementLabel);
        return value;
    }

    /**
     * Retrieves all available options from the dropdown.
     * <p>
     * This method attempts to retrieve all the option values from the dropdown as a list.
     * If the dropdown is not displayed, a BaseException is thrown.
     * </p>
     *
     * @param dropdown     The WebElement representing the dropdown.
     * @param elementLabel The label for logging purposes.
     * @return A list of all option values in the dropdown.
     * @throws BaseException.ElementNotFoundException If the dropdown is not displayed.
     */
    public List<String> getAllDropdownValues(WebElement dropdown, String elementLabel) {
        if (!verificationHandler.isElementDisplayed(dropdown, elementLabel)) {
            throw new BaseException.ElementNotFoundException(elementLabel);
        }
        Select select = new Select(dropdown);
        return select.getOptions().stream()
                .map(WebElement::getText)
                .peek(option -> log.info("The '{}' dropdown has the option value '{}'", elementLabel, option))
                .collect(Collectors.toList());
    }

}
