package dk.tweenstyle.android.app.dao;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import dk.tweenstyle.android.app.model.Settings;

public class SettingsJSONLoader implements JSONLoader<Settings> {
	
	@Override
	public Settings loadObject(JSONObject object) {
		Settings settings = new Settings();
		if(!this.loadObject(object, settings)){
			settings = null;
		}
		return settings;
	}
	
	@Override
	public boolean loadObject(JSONObject object, Settings settings) {
		boolean result = false;
		if (settings != null) {
			@SuppressWarnings("unchecked")
			Iterator<String> it = object.keys();
			try {
				while (it.hasNext()) {
					String k = it.next() + "";
					settings.put(k, object.get(k) + "");
				}
				result = true;
			}
			catch (JSONException e) {
				Log.d("json", "Trouble loading Settings object from JSON", e);
				result = false;
			}
		}
		return result;
	}
	
}
