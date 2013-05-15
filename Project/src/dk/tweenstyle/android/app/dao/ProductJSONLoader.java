package dk.tweenstyle.android.app.dao;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;
import dk.tweenstyle.android.app.model.Gender;
import dk.tweenstyle.android.app.model.Product;

public class ProductJSONLoader implements JSONLoader<Product> {
	private static SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss", Locale.UK);
	
	@Override
	public Product loadObject(JSONObject object) {
		Product product = new Product();
		if (!this.loadObject(object, product)) {
			product = null;
		}
		return product;
	}
	
	@Override
	public boolean loadObject(JSONObject object, Product product) {
		return this.loadObject(object, product, false);
	}
	
	public boolean loadObject(JSONObject object, Product product,
			boolean isVariant) {
		boolean result = false;
		if (product != null) {
			try {
				product.setId(object.getString("id"));
				String gender = object.getString("gender");
				if (gender.startsWith("f")) {
					product.setGender(Gender.FEMALE);
				}
				else if (gender.startsWith("m")) {
					product.setGender(Gender.MALE);
				}
				else {
					product.setGender(Gender.UNISEX);
				}
				product.setVariantId(object.getString("variantId"));
				product.setBasePrice(object.getDouble("basePrice"));
				double currentPrice = object.optDouble("currentPrice");
				if (!Double.isNaN(currentPrice)) {
					product.setCurrentPrice(currentPrice);
				}
				else{
					product.setCurrentPrice(product.getBasePrice());
				}
				product.setNumber(object.getString("number"));
				product.setName(object.getString("name"));
				product.setActive(object.getBoolean("isActive"));
				product.setStock(object.getInt("stock"));
				product.setShortDescription(object
						.getString("shortDescription"));
				product.setLongDescription(object.getString("longDescription"));
				// DateFormat format =
				// DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
				// DateFormat.MEDIUM);
				product.setTimeCreated(format.parse(object
						.getString("timeCreated")));
				product.setTimeUpdated(format.parse(object
						.getString("timeUpdated")));
				product.setDefaultVariantCombination(object
						.getString("defaultVariantCombination"));
				product.setNew(object.getBoolean("isNew"));
				product.setMinAge(object.getInt("minAge"));
				product.setMaxAge(object.getInt("maxAge"));
				product.setMinShoeSize(object.getInt("minShoeSize"));
				product.setMaxShoeSize(object.getInt("maxShoeSize"));
				product.setManufacturerName(object.getString("manufacturerName"));
				product.setManufacturerWebsite(object.getString("manufacturerWebsite"));
				product.setManufacturerLogo(object.getString("manufacturerLogo"));
				product.setManufacturerDescription(object
						.getString("manufacturerDescription"));
				
				JSONArray discounts = object.optJSONArray("discounts");
				product.clearDiscounts();
				if (discounts != null) {
					for (int i = 0, max = discounts.length(); i < max; i++) {
						String discount = discounts.getString(i);
						product.addDiscount(discount);
					}
				}
				
				JSONArray groups = object.optJSONArray("groups");
				product.clearGprs();
				if (groups != null) {
					for (int i = 0, max = groups.length(); i < max; i++) {
						String group = groups.getString(i);
						product.addGpr(group);
					}
				}
				
				JSONArray variants = object.optJSONArray("variants");
				if (variants != null && !isVariant) {
					for (int i = 0, max = variants.length(), len = product
							.getProductsCount(); i < max; i++) {
						JSONObject variant = variants.getJSONObject(i);
						if (i >= len) {
							Product var = new Product();
							if (this.loadObject(variant, var, true)) {
								product.addProduct(var);
							}
							else {
								throw new Exception("Variant failed to load. ");
							}
							len = product.getProductsCount();
						}
						else {
							Product var = product.getProductAt(i);
							if (var == null) {
								var = new Product();
								product.setProductAt(i, var);
							}
							if (!this.loadObject(variant, var, true)) {
								throw new Exception("Variant failed to load. ");
							}
						}
					}
					for (int i = variants.length(), max = product
							.getProductsCount(); i < max; i++) {
						product.removeProduct(product.getProductAt(i));
					}
				}
				else {
					product.clearProducts();
				}
				result = true;
			}
			catch (Exception e) {
				Log.d("json", "Trouble loading Product object from JSON", e);
				result = false;
			}
		}
		return result;
	}
	
}
