package dk.tweenstyle.android.app.dao;

import org.json.JSONObject;

import dk.tweenstyle.android.app.model.Discount;

/**
 * JSON class, parsing the JSON object to a Discount object.
 * @author Christopher
 *
 */
public class DiscountJSONLoader implements JSONLoader<Discount> {

	@Override
	public Discount loadObject(JSONObject object) {

		Discount discount = new Discount();
		try{
			discount.setId(object.getString("id"));
			discount.setType(object.getString("type"));
			discount.setName(object.getString("name"));
			discount.setPriceFixed(object.getDouble("priceFixed"));
			discount.setPricePercentage(object.getDouble("pricePercentage"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return discount;
	}
}
