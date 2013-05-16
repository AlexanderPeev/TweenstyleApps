package dk.tweenstyle.android.app.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import dk.tweenstyle.android.app.model.test.SettingsTest;

import junit.framework.TestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	SettingsTest.class
})
public class TweenstyleAppTestSuite extends TestSuite {
	public TweenstyleAppTestSuite() {
		this.addTestSuite(SettingsTest.class);
	}
}
