package dk.tweenstyle.android.app.dao;

import org.json.JSONObject;

public interface JSONLoader<T> {
	T loadObject(JSONObject object);
	boolean loadObject(JSONObject object, T output);
}
