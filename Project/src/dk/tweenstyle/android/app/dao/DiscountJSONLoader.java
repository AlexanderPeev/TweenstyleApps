package dk.tweenstyle.android.app.dao;

import org.json.JSONObject;

import android.util.Log;

import dk.tweenstyle.android.app.model.Discount;

/**
 * JSON class, parsing the JSON object to a Discount object.
 * 
 * @author Christopher
 * 
 */
public class DiscountJSONLoader implements JSONLoader<Discount> {
	
	@Override
	public Discount loadObject(JSONObject object) {
		Discount discount = new Discount();
		if (!this.loadObject(object, discount)) {
			discount = null;
		}
		return discount;
	}
	
	@Override
	public boolean loadObject(JSONObject object, Discount discount) {
		boolean result = false;
		if (discount != null) {
			try {
				discount.setId(object.getString("id"));
				discount.setType(object.getString("type"));
				discount.setName(object.getString("name"));
				discount.setPriceFixed(object.getDouble("priceFixed"));
				discount.setPricePercentage(object.getDouble("pricePercentage"));
				result = true;
			}
			catch (Exception e) {
				Log.d("json", "Trouble loading Discount object from JSON", e);
				result = false;
			}
		}
		
		return result;
	}
}
