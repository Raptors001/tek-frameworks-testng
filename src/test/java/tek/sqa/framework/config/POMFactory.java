package tek.sqa.framework.config;

import tek.sqa.framework.base.BaseSetup;
import tek.sqa.framework.pages.RetailPage;

public class POMFactory extends BaseSetup {
	private static POMFactory pomFactory;
	private RetailPage retailPage;
	

	private POMFactory() {
		this.retailPage = new RetailPage();

	}

	public static POMFactory getPOMFactory() {
		if (pomFactory == null)
			pomFactory = new POMFactory();
		return pomFactory;
	}
	
	public RetailPage retailPage() {
		return this.retailPage;
	}

}
