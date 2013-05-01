package dk.tweenstyle.android.app.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Discount class-
 * @author Christopher
 *
 */
public class Discount {
	
	private String id;
	private String type, name;
	private double priceFixed,pricePercentage;
	
	private List<Product> products = new ArrayList<Product>();
	
	public Discount(String id, String type, String name,  double price,  double percentage){
		this.id =id;
		this.type=type;
		this.name =name;
		this.priceFixed=price;
		this.pricePercentage=percentage;
	}
	public Discount() {
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPriceFixed() {
		return priceFixed;
	}
	public void setPriceFixed(double priceFixed) {
		this.priceFixed = priceFixed;
	}
	public double getPricePercentage() {
		return pricePercentage;
	}
	public void setPricePercentage(double pricePercentage) {
		this.pricePercentage = pricePercentage;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public void addProduct(Product p){
		if(!this.products.contains(p))
			products.add(p);
	}
	public void removeProduct(Product p){
		if(this.products.contains(p))
			products.remove(p);
	}
	
}
