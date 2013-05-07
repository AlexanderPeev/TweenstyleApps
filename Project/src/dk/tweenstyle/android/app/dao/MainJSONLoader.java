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

import android.app.ProgressDialog;
import android.util.Log;
import dk.tweenstyle.android.app.model.Discount;
import dk.tweenstyle.android.app.model.Group;
import dk.tweenstyle.android.app.model.Product;
import dk.tweenstyle.android.app.model.Settings;

public class MainJSONLoader {
	private JSONLoader<Product> ldrProduct;
	private JSONLoader<Discount> ldrDiscount;
	private JSONLoader<Group> ldrGroup;
	private JSONLoader<Settings> ldrSettings;
	private ProgressDialog processDialog;

	public MainJSONLoader(ProgressDialog pd) {
		this.setupLoaders();
		this.processDialog = pd;
	}

	private void setupLoaders() {
		this.ldrProduct = new ProductJSONLoader();
		this.ldrDiscount = new DiscountJSONLoader();
		this.ldrGroup = new GroupJSONLoader();
		this.ldrSettings = new SettingsJSONLoader();
	}

	public String fetchJSONData(URI uri) throws InterruptedException {
		Thread t = new Thread(new JSONLoaderThread(uri));
		t.start();
		t.join();
		return ((JSONLoaderThread) t).getValue();
	}

	class JSONLoaderThread extends Thread {
		private String ret;
		private URI uri;

		public JSONLoaderThread(URI uri) {
			this.uri = uri;
		}

		@Override
		public void run() {
			try {
				DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
				HttpPost httppost = new HttpPost(uri);
				httppost.setHeader("Content-type", "application/json");

				InputStream inputStream = null;
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();

				inputStream = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
				StringBuilder sb = new StringBuilder();

				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				ret = sb.toString();
			} catch (Exception e) {
				Log.e("json", "Error while fetching data from CMS:" + e);
			}
			if (processDialog != null) {
				processDialog.dismiss();
			}
		}

		public String getValue() {
			return ret;
		}
	}

	public MemoryDAO loadJSONData(String data) {
		MemoryDAO dao = new MemoryDAO();
		try {
			JSONObject jObject = new JSONObject(data);
			JSONArray products = jObject.getJSONArray("products");
			for (int i = 0; i < products.length(); i++) {
				JSONObject oneObject = products.getJSONObject(i);
				dao.addProduct(ldrProduct.loadObject(oneObject));
			}
			JSONArray groups = jObject.getJSONArray("groups");
			for (int i = 0; i < groups.length(); i++) {
				JSONObject oneObject = groups.getJSONObject(i);
				dao.addGroup(ldrGroup.loadObject(oneObject));
			}
			JSONArray discounts = jObject.getJSONArray("discounts");
			for (int i = 0; i < discounts.length(); i++) {
				JSONObject oneObject = discounts.getJSONObject(i);
				dao.addDiscount(ldrDiscount.loadObject(oneObject));
			}
			JSONObject settings = jObject.getJSONObject("settings");
			dao.setSettings(ldrSettings.loadObject(settings));
		} catch (JSONException e) {
			Log.e("json", "Error while fetching data from JSON:" + e);
		}
		return dao;
	}
}
