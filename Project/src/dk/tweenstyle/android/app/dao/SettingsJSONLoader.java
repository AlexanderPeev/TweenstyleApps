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
		Iterator<String> it = object.keys();
		try {
		while(it.hasNext()){
			String k = it.next();
				settings.put(k, object.getString(k));
		}
		} catch (JSONException e) {
			   settings = null;
			   Log.d("json", "Trouble loading Settings object from JSON", e);
			  }
		return settings;
	}
	

	
}
