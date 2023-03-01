package tek.sqa.framework.config;

import tek.sqa.framework.base.BaseSetup;

public class POMFactory extends BaseSetup {
	private static POMFactory pomFactory;

	private POMFactory() {

	}

	public static POMFactory getPOMFactory() {
		if (pomFactory == null)
			pomFactory = new POMFactory();
		return pomFactory;
	}

}
