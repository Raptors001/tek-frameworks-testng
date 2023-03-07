package tek.sqa.framework.base;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.service.ExtentTestManager;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import org.apache.poi.ss.formula.eval.ExternalNameEval;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import tek.sqa.framework.utilities.CommonUtility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Instant;

@Listeners({ExtentITestListenerClassAdapter.class})
public class BaseUITest extends CommonUtility {

    @BeforeMethod
    public void setUpTest() {
        super.setupBrowser();
        super.getConnectedToDatabase();
    }

    @AfterMethod
    public void closeTest(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {

            ExtentTestManager.getTest(result).fail("ITestResult.FAILURE, e vent afterMethod",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShot()).build());
        }
      //  super.quiteBrowser();
    }

//    /**
//     * !!This code block is just an example only!!
//     * !!Real-world implementation would require capturing a screenshot!!
//     *
//     * @return Image path
//     * @throws java.io.IOException
//     */
//    private String getImage() throws IOException {
//        Files.copy(new File(IMG_PATH).toPath(), new File(OUTPUT_PATH + IMG_NAME).toPath());
//        return IMG_NAME;
//    }

}
