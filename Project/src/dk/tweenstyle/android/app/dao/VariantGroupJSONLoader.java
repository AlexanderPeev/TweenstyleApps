package dk.tweenstyle.android.app.dao;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;
import dk.tweenstyle.android.app.model.VariantGroup;
import dk.tweenstyle.android.app.model.VariantOption;

public class VariantGroupJSONLoader implements JSONLoader<VariantGroup> {
	private JSONLoader<VariantOption> ldrOption = new VariantOptionJSONLoader();
	
	public JSONLoader<VariantOption> getLdrOption() {
		return ldrOption;
	}
	
	public void setLdrOption(JSONLoader<VariantOption> ldrOption) {
		if (ldrOption != null) {
			this.ldrOption = ldrOption;
		}
	}
	
	@Override
	public VariantGroup loadObject(JSONObject object) {
		VariantGroup result = new VariantGroup();
		if (!this.loadObject(object, result)) {
			result = null;
		}
		return result;
	}
	
	@Override
	public boolean loadObject(JSONObject object, VariantGroup output) {
		boolean result = false;
		if (object != null && output != null) {
			try {
				output.setId(object.optString("id"));
				output.setName(object.optString("name"));
				output.setLabel(object.optString("label"));
				output.setColors(object.optBoolean("isColors"));
				
				JSONArray options = object.optJSONArray("options");
				if (options != null && options.length() > 0) {
					for (int i = 0, max = options.length(), len = output
							.getVariantOptionsCount(); i < max; i++) {
						JSONObject option = options.getJSONObject(i);
						if (i >= len) {
							VariantOption opt = new VariantOption();
							if (this.ldrOption.loadObject(option, opt)) {
								output.addVariantOption(opt);
							}
							else {
								throw new Exception("VariantOption failed to load. ");
							}
							len = output.getVariantOptionsCount();
						}
						else {
							VariantOption opt = output.getVariantOptionAt(i);
							if (opt == null) {
								opt = new VariantOption();
								output.setVariantOptionAt(i, opt);
							}
							if (!this.ldrOption.loadObject(option, opt)) {
								throw new Exception("VariantOption failed to load. ");
							}
						}
					}
					for (int i = options.length(), max = output
							.getVariantOptionsCount(); i < max; i++) {
						output.removeVariantOption(output.getVariantOptionAt(i));
					}
				}
				else {
					output.clearVariantOptions();
				}
				
				result = true;
			}
			catch (Throwable ex) {
				Log.d("json", "Trouble loading Variant Group object from JSON",
						ex);
				result = false;
			}
		}
		return result;
	}
	
}
