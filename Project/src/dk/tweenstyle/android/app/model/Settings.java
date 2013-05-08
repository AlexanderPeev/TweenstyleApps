package dk.tweenstyle.android.app.model;

import java.util.HashMap;

public class Settings {

	private HashMap<String, String> settings;

	public Settings() {
		setStrings(new HashMap<String, String>());
	}

	public String put(String key, String value) {
		return settings.put(key, value);
	}

	public boolean containsKey(String key) {
		return settings.containsKey(key);
	}

	public String getValue(String key) {
		return settings.get(key);
	}

	public HashMap<String, String> getStrings() {
		return settings;
	}

	public void setStrings(HashMap<String, String> strings) {
		this.settings = strings;
	}

}
