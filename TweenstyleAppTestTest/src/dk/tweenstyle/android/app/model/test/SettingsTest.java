package dk.tweenstyle.android.app.model.test;

import junit.framework.Assert;
import junit.framework.TestCase;

import dk.tweenstyle.android.app.model.Settings;

public class SettingsTest extends TestCase {
	private Settings s;
	
	// @BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	// @AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	// @Before
	public void setUp() throws Exception {
		this.s = new Settings();
	}
	
	// @After
	public void tearDown() throws Exception {
	}
	
	// @Test
	public void test() {
		String key = "testKey", value = "testValue";
		this.s.put(key, value);
		Assert.assertTrue(this.s.containsKey(key));
		Assert.assertEquals(value, this.s.getValue(key));
		int ran = 0;
		for (String k : this.s) {
			Assert.assertEquals(key, k);
			++ran;
		}
		Assert.assertEquals(1, ran);
	}
	
}
