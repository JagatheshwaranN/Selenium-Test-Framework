# Selenium Test Framework – Release Notes - Version 2.0.0

## 🎯 Overview
This release focuses on improving the stability, reliability, and thread-safety of the test automation framework, especially under parallel execution.

### Key Areas Addressed:
- Driver lifecycle management
- Extent Reports reliability
- Thread safety in reporting
- Framework design improvements
- Runtime exception handling

## 🐛 Bug Fixes & Enhancements

## 🐛 Bug 1 - Double Driver Teardown (Browser Not Closing Cleanly)

**Files Impacted:**
- `DriverCleanupListener.java`
- `BaseTest.java`

**Issue:**
Multiple components were triggering driver teardown:
- `@AfterMethod` in `BaseTest`
- Listener methods (`onTestSuccess`, `onTestFailure`, etc.)
- `onFinish(ITestContext)` during parallel execution

**Impact:**
- Attempts to close already-closed drivers
- Risk of terminating browsers in other parallel threads

**Fix:**
- Removed redundant cleanup calls from `DriverCleanupListener`
- Restricted cleanup to:
    - `@AfterMethod` (primary teardown)
    - Optional fallback in `onFinish(ISuite)`
- Ensured `DriverCleanupListener` is removed

**Result:**
- Stable and predictable driver lifecycle
- Eliminates cross-thread browser termination

## 🐛 Bug 2 - Extent Report Not Generated (Null Reference Issue)

**File Impacted:**
- `TestListener.java`

**Issue:**
`extentReports` was an instance variable. In parallel execution:
- Multiple listener instances were created
- Reference was lost
- `extentReports.flush()` executed on `null`

**Fix:**
```java
private static ExtentReports extentReports;
```

## 🐛 Bug 3 - Race Condition in Test Reporting (Missing Logs)

**File Impacted:**
- `TestListener.java`

**Issue:**
`extentTest` was shared across threads:
- Parallel tests overwrote each other’s logs
- Missing or incorrect report entries

**Fix:**
```
Removed shared instance variable
Used ThreadLocal<ExtentTest> via ExtentReportManager
```

## 🐛 Bug 4 - Incorrect Inheritance in BaseTest (Singleton Violation)

**File Impacted:**
- `BaseTest.java`

**Issue:**
- `BaseTest` extended DriverManager (Singleton), causing:
- Multiple unintended instances
- Broken Singleton pattern
- Risk of inconsistent driver management

**Fix:**
- Replaced inheritance with composition
```java
public class BaseTest {

    protected DriverManager driverManager = DriverManager.getInstance();

    @BeforeMethod
    public void testStartUp() {
        driverManager.initializeDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void testTearDown() {
        driverManager.closeDriver();
    }
}
```

## 🐛 Bug 5 - NullPointerException in Screenshot Capture
**File Impacted:**
- `ScreenCapture.java`

**Issue:**
- System.getProperty() could return `null`, leading to:
- .equalsIgnoreCase() on null
- Runtime crash during screenshot capture

**Fix:**
```java
String screenshotMode  = System.getProperty(FULL_PAGE_SCREENSHOT, "No");
```