package dk.tweenstyle.android.app.dao;

import org.json.JSONObject;

import android.util.Log;
import dk.tweenstyle.android.app.model.VariantOption;

public class VariantOptionJSONLoader implements JSONLoader<VariantOption> {
	
	@Override
	public VariantOption loadObject(JSONObject object) {
		VariantOption result = new VariantOption();
		if (!this.loadObject(object, result)) {
			result = null;
		}
		return result;
	}
	
	@Override
	public boolean loadObject(JSONObject object, VariantOption output) {
		boolean result = false;
		if (object != null && output != null) {
			try {
				output.setId(object.optString("id"));
				output.setGroupId(object.optString("groupId"));
				output.setName(object.optString("name"));
				output.setImage(object.optString("image"));
				
				result = true;
			}
			catch (Throwable ex) {
				Log.d("json", "Trouble loading Variant Option object from JSON", ex);
				result = false;
			}
		}
		return result;
	}
	
}
