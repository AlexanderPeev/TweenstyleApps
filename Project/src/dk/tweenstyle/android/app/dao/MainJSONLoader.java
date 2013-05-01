package dk.tweenstyle.android.app.dao;

import dk.tweenstyle.android.app.model.Discount;
import dk.tweenstyle.android.app.model.Group;
import dk.tweenstyle.android.app.model.Product;
import dk.tweenstyle.android.app.model.Settings;
import android.net.Uri;

public class MainJSONLoader {
	private JSONLoader<Product> ldrProduct;
	private JSONLoader<Discount> ldrDiscount;
	private JSONLoader<Group> ldrGroup;
	private JSONLoader<Settings> ldrSettings;
	
	public MainJSONLoader() {
		this.setupLoaders();
	}
	
	private void setupLoaders() {
		this.ldrProduct = new ProductJSONLoader();
		this.ldrDiscount = new DiscountJSONLoader();
		this.ldrGroup = new GroupJSONLoader();
		this.ldrSettings = new SettingsJSONLoader();
	}
	
	public String fetchJSONData(Uri uri) {
		// TODO Implement this!!! Get a JSON String from the remote server.
		return null;
	}
	
	public MemoryDAO loadJSONData(String data) {
		MemoryDAO dao = new MemoryDAO();
		
		// TODO Implement this!!! Call the loaders and put the data into the
		// dao.
		
		return dao;
	}
}
