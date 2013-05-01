package dk.tweenstyle.android.app.dao;

import java.util.ArrayList;

import dk.tweenstyle.android.app.model.Discount;
import dk.tweenstyle.android.app.model.Group;
import dk.tweenstyle.android.app.model.Product;
import dk.tweenstyle.android.app.model.Settings;

public class MemoryDAO {

	private ArrayList<Product> products = new ArrayList<Product>();
	private ArrayList<Group> groups = new ArrayList<Group>();
	private ArrayList<Discount> discounts = new ArrayList<Discount>();
	private Settings settings = null;

	public Product addProduct(Product product) {
		products.add(product);
		return product;
	}

	public boolean removeProduct(Product product) {
		return products.remove(product);
	}

	public Group addGroup(Group group) {
		groups.add(group);
		return group;
	}

	public boolean removeGroup(Group group) {
		return groups.remove(group);
	}

	public Discount addDiscount(Discount discount) {
		discounts.add(discount);
		return discount;
	}

	public boolean removeDiscount(Discount discount) {
		return discounts.remove(discount);
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

}
