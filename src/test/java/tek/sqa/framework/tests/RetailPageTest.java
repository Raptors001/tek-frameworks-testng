package tek.sqa.framework.tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import tek.sqa.framework.base.BaseUITest;
import tek.sqa.framework.config.POMFactory;
import tek.sqa.framework.utilities.ExcelReader;

public class RetailPageTest extends BaseUITest {

	private POMFactory factory;

	@BeforeMethod
	public void initializeTest() {
		this.factory = POMFactory.getPOMFactory();
	}

	@Test(dataProvider = "informationData")
	public void RetailApplicationTest(String cardNumV, String nameOnCardV, String expMonthV, String expYearV,
			String cvcCodeV) throws InterruptedException {
		List<Map<String, Object>> dbInfo = getDbUtility().convertResultToMap("select * from users where id = 3798;");
		String userName = dbInfo.get(0).get("email").toString();
		String password = getProperty("userPassword");
		POMFactory.getPOMFactory().retailPage().signIn(userName, password);
		Thread.sleep(5000);
		POMFactory.getPOMFactory().retailPage().clickOnAccountOption();
		Thread.sleep(5000);
		sendText(POMFactory.getPOMFactory().retailPage().cardInputField, cardNumV);
		sendText(POMFactory.getPOMFactory().retailPage().nameOnCardInputField,nameOnCardV);
		selectValueByVisibleText(POMFactory.getPOMFactory().retailPage().expirationYear, expYearV);
		selectValueByVisibleText(POMFactory.getPOMFactory().retailPage().expirationMonth, expMonthV);
		sendText(POMFactory.getPOMFactory().retailPage().securityCodeInput, cvcCodeV);
		click(POMFactory.getPOMFactory().retailPage().addYourCardButton);
		Thread.sleep(5000);
		List<Map<String, Object>> creditCard = getDbUtility().
				convertResultToMap("select * from payments where cardNumber ="+cardNumV+ ";" );
		Assert.assertEquals(creditCard.get(0).get("cardNumber"),cardNumV, "This is assertion");
		

	}

	@DataProvider(name = "informationData")
	public Object[][] data() throws IOException {
		// path to excel file
		String ExcelFilePath = ".\\src\\test\\resources\\testData\\userInformation.xlsx";
		List<Map<String, Object>> infoData = ExcelReader
				.readSheetWithFirstRowAsHeader(ExcelReader.getExcelSheet(ExcelFilePath, 1));
		Object[][] object = new Object[infoData.size()][infoData.get(0).size()];
		for (int i = 0; i < object.length; i++) {
			String cardNumber = String.valueOf(infoData.get(i).get("cardNumber"));
			object[i][0] = cardNumber;
			object[i][1] = infoData.get(i).get("nameOnCard");
			String expMonth = String.valueOf(infoData.get(i).get("expirationMonth"));
			object[i][2] = expMonth.substring(0, expMonth.length() - 2);
			String expYear = String.valueOf(infoData.get(i).get("expirationYear"));
			object[i][3] = expYear.substring(0, expYear.length() - 2);
			String cvcCode = String.valueOf(infoData.get(i).get("cvcCode"));
			object[i][4] = cvcCode.substring(0, cvcCode.length() - 2);
		}
		return object;
	}

}
