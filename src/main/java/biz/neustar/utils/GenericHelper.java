package biz.neustar.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import biz.neustar.interfaces.IConstants;
import biz.neustar.settings.ObjectRepo;
import biz.neustar.test.datautils.FormatDates;

/**
 * @author adnan hasan
 *
 */
public class GenericHelper {

	public String takeScreenShot(String name) throws IOException {

		File destDir = new File(IConstants.Screenshot.SCREENSHOT_PATH
				+ FormatDates.getCurrentDate());
		if (!destDir.exists())
			destDir.mkdir();

		File destPath = new File(destDir.getAbsolutePath()
				+ System.getProperty("file.separator") + name + ".jpg");
		try {
			FileUtils.copyFile(((TakesScreenshot) ObjectRepo.driver)
					.getScreenshotAs(OutputType.FILE), destPath);
		} catch (IOException e) {
			throw e;
		}
		return destPath.getAbsolutePath();
	}

	public String takeScreenShot() {
		return ((TakesScreenshot) ObjectRepo.driver)
				.getScreenshotAs(OutputType.BASE64);
	}

}
