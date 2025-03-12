package org.Testing.API;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class ExtentTestNGITestListener implements ITestListener {
    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // 监听测试开始
    @Override
    public void onStart(ITestContext context) {
        System.out.println("*** Test Suite " + context.getName() + " started ***");
    }

    // 监听测试结束
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("*** Test Suite " + context.getName() + " ending ***");
        extent.flush();
    }

    // 监听测试用例开始
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("*** Running test method " + result.getMethod().getMethodName() + "...");
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        test.set(extentTest);
    }

    // 监听测试用例成功
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
        if (test.get() != null) {
            test.get().log(Status.PASS, "Test passed");
        }
    }

    // 监听测试用例失败
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
        if (test.get() != null) {
            test.get().log(Status.FAIL, result.getThrowable());
        }
    }

    // 监听测试用例跳过
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
        if (test.get() != null) {
            test.get().log(Status.SKIP, "Test Skipped");
        }
    }

    // 监听测试用例部分失败
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
    }
}