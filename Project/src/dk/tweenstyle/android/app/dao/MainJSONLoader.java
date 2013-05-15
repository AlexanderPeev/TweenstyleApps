package dk.tweenstyle.android.app.dao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import dk.tweenstyle.android.app.model.Discount;
import dk.tweenstyle.android.app.model.Group;
import dk.tweenstyle.android.app.model.Product;
import dk.tweenstyle.android.app.model.Settings;
import dk.tweenstyle.android.app.model.VariantGroup;

public class MainJSONLoader {
	public static final int READ_BUFFER_SIZE = 2048;
	private JSONLoader<Product> ldrProduct;
	private JSONLoader<Discount> ldrDiscount;
	private JSONLoader<Group> ldrGroup;
	private JSONLoader<Settings> ldrSettings;
	private JSONLoader<VariantGroup> ldrVariantGroups;

	public MainJSONLoader() {
		this.setupLoaders();
	}

	private void setupLoaders() {
		this.ldrProduct = new ProductJSONLoader();
		this.ldrDiscount = new DiscountJSONLoader();
		this.ldrGroup = new GroupJSONLoader();
		this.ldrSettings = new SettingsJSONLoader();
		this.ldrVariantGroups = new VariantGroupJSONLoader();
	}

	public String fetchJSONData(URI uri) {
		String result = "";
		try {
			DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
			HttpPost httppost = new HttpPost(uri);
			httppost.setHeader("Content-type", "application/json");

			InputStream inputStream = null;
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();

			inputStream = entity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
			StringBuilder sb = new StringBuilder(5000);
			char[] buffer = new char[READ_BUFFER_SIZE];

			int amountRead = 0;//, i = 0;
			//String line = null;
			while((amountRead = reader.read(buffer, 0, READ_BUFFER_SIZE)) >= 0){
				if(amountRead > 0){
					sb.append(new String(buffer, 0, amountRead));
					//Log.d("fetch", "Fetched: " + ++i);
				}
			}
			//while ((line = reader.readLine()) != null) {
			//	sb.append(line + "\n");
			//	Log.d("fetch", "Fetched: " + ++i);
			//}
			result = sb.toString();
		} catch (Exception e) {
			Log.e("json", "Error while fetching data from CMS:" + e);
		}
		return result;
	}

	public MemoryDAO loadJSONData(String data) {
		MemoryDAO dao = MemoryDAO.getInstance();
		try {
			JSONObject root = new JSONObject(data);
			
			JSONArray products = root.getJSONArray("products");
			for (int i = 0, max = products.length(); i < max; i++) {
				JSONObject oneObject = products.getJSONObject(i);
				dao.addProduct(ldrProduct.loadObject(oneObject));
			}
			
			JSONArray groups = root.getJSONArray("groups");
			for (int i = 0, max = groups.length(); i < max; i++) {
				JSONObject oneObject = groups.getJSONObject(i);
				dao.addGroup(ldrGroup.loadObject(oneObject));
			}
			
			JSONArray discounts = root.getJSONArray("discounts");
			for (int i = 0, max = discounts.length(); i < max; i++) {
				JSONObject oneObject = discounts.getJSONObject(i);
				dao.addDiscount(ldrDiscount.loadObject(oneObject));
			}
			
			JSONArray variantGroups = root.getJSONArray("variantGroups");
			for (int i = 0, max = variantGroups.length(); i < max; i++) {
				JSONObject oneObject = variantGroups.getJSONObject(i);
				dao.addVariantGroup(ldrVariantGroups.loadObject(oneObject));
			}
			
			JSONObject settings = root.getJSONObject("settings");
			dao.setSettings(ldrSettings.loadObject(settings));
		} catch (JSONException e) {
			Log.e("json", "Error while fetching data from JSON:" + e);
		}
		return dao;
	}
}
