package com.mystore.utility;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;


public class ListenerClass extends ExtentManager implements ITestListener {

	Action action= new Action();
	private static ThreadLocal<com.aventstack.extentreports.ExtentTest> test = new ThreadLocal<>();
	public void onTestStart(ITestResult result) {
		com.aventstack.extentreports.ExtentTest extentTest = extent.createTest(result.getName());
        test.set(extentTest);
	}

	public void onTestSuccess(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.get().log(Status.PASS, "Pass Test case is: " + result.getName());
		}
	}

	public void onTestFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				 test.get().log(Status.FAIL,
	                        MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
				 test.get().log(Status.FAIL,
	                        MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
				String imgPath = action.screenShot(BaseClass.getDriver(), result.getName());
			
				test.get().fail("ScreenShot is Attached", MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void onTestSkipped(ITestResult result) {
		if (result.getStatus() == ITestResult.SKIP) {
			 test.get().log(Status.SKIP, "Skipped Test case is: " + result.getName());
		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
		extent = ExtentManager.setExtent();
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		extent.flush();
		// TODO Auto-generated method stub
	}
}