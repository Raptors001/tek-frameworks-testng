package testNG.basics;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tek.sqa.framework.utilities.ExcelReader;

public class TestNGDataProviderWithExcelSheet {

	@Test(dataProvider = "informationData")
	public void testOne(String name, String email, String city, String state, String zipcode) {
		System.out.println(name + " This is name from excel sheet");
		System.out.println(email + " This is email from excel sheet");
		System.out.println(city + " This is city from excel sheet");
		System.out.println(state + " This is state from excel sheet");
		System.out.println(zipcode + " This is zipcode from excel sheet");
	}

	@DataProvider(name = "informationData")
	public Object[][] data() throws IOException {
		// path to excel file
		String ExcelFilePath = ".\\src\\test\\resources\\testData\\userInformation.xlsx";
		List<Map<String, Object>> infoData = ExcelReader
				.readSheetWithFirstRowAsHeader(ExcelReader.getExcelSheet(ExcelFilePath, 0));
		Object[][] object = new Object[infoData.size()][infoData.get(0).size()];
		for (int i = 0; i < object.length; i++) {
			object[i][0] = infoData.get(i).get("name");
			object[i][1] = infoData.get(i).get("email");
			object[i][2] = infoData.get(i).get("city");
			object[i][3] = infoData.get(i).get("state");
			String temp = String.valueOf(infoData.get(i).get("zipcode"));
			object[i][4] = temp.substring(0,temp.length()-2);
		}
		return object;
	}

}
