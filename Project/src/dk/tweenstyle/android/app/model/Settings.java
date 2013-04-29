package dk.tweenstyle.android.app.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Settings {

	private HashMap<String, String> strings;

	public Settings() {
		setStrings(new HashMap<String, String>());
	}

	public String put(String key, String value) {
		return strings.put(key, value);
	}

	public String getValue(String key) {
		return strings.get(key);
	}

	public String getKey(String value) {
		Iterator<Entry<String, String>> it = strings.entrySet().iterator();
		String ret = null;
		while (it.hasNext()) {
			Entry<String, String> o = it.next();
			if (o.getValue().equals(value)) {
				ret = o.getKey();
				break;
			}
		}
		return ret;
	}

	public HashMap<String, String> getStrings() {
		return strings;
	}

	public void setStrings(HashMap<String, String> strings) {
		this.strings = strings;
	}

}
