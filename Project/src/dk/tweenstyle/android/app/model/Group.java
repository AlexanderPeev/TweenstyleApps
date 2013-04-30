package dk.tweenstyle.android.app.model;

import java.util.ArrayList;
import java.util.List;

public class Group {

	private int Id;
	private String name;
	private List<Group> groups = new ArrayList<Group>();
	private List<Product> products = new ArrayList<Product>();
	private List<Discount> discounts = new ArrayList<Discount>();

	public Group(int id, String name, List<Group> groups,
			List<Product> products, List<Discount> discounts) {

		Id = id;
		this.name = name;
		this.groups = groups;
		this.products = products;
		this.discounts = discounts;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public void addGroup(Group group) {

		groups.add(group);
	}

	public void removeGroup(Group group) {

		groups.remove(group);

	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void addProduct(Product product) {

		products.add(product);
	}

	public void removeProduct(Product product) {

		products.remove(product);

	}

	public List<Discount> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
	}

	public void addDiscount(Discount discount) {

		discounts.add(discount);
	}

	public void removeDiscount(Discount discount) {

		discounts.remove(discount);

	}

}
