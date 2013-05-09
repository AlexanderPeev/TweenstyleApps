package dk.tweenstyle.android.app.model;

import java.util.ArrayList;
import java.util.List;

public class Group {

	private String id;
	private String name;
	private List<String> groups = new ArrayList<String>();
	private List<Product> products = new ArrayList<Product>();
	private List<Discount> discounts = new ArrayList<Discount>();
	private List<Group> children = new ArrayList<Group>();
	
	public Group(){}

	public Group(String id, String name, List<String> groups,
			List<Product> products, List<Discount> discounts) {

		this.id = id;
		this.name = name;
		this.groups = groups;
		this.products = products;
		this.discounts = discounts;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getGroups() {
		return groups;
	}

	public void setGroups(List<String> groups) {
		this.groups = groups;
	}

	public void addGroup(String group) {

		groups.add(group);
	}

	public void removeGroup(String group) {

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

	public List<Group> getChildren() {
		return children;
	}

	public void setChildren(List<Group> children) {
		this.children = children;
	}

	public void addChild(Group child) {
		children.add(child);
	}

	public void removeChild(Group child) {
		children.remove(child);
	}

}
