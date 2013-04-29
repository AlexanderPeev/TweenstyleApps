package dk.tweenstyle.android.app.model;

import java.util.HashMap;

public class Settings {

	private HashMap<String, String> strings;

	public Settings() {
		setStrings(new HashMap<String, String>());
	}

	public String put(String key, String value) {
		return strings.put(key, value);
	}

	public boolean containsKey(String key) {
		return strings.containsKey(key);
	}

	public String getValue(String key) {
		return strings.get(key);
	}

	public HashMap<String, String> getStrings() {
		return strings;
	}

	public void setStrings(HashMap<String, String> strings) {
		this.strings = strings;
	}

}
