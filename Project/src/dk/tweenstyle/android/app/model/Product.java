package dk.tweenstyle.android.app.model;

import java.util.Date;
import java.util.ArrayList;

public class Product {
	
	private String id;
	private Gender gender;
	private String variantId;
	private double basePrice;
	private double currentPrice;
	private String number;
	private String name;
	private boolean isActive;
	private int stock;
	private String shortDescription;
	private String longDescription;
	private Date timeCreated;
	private Date timeUpdated;
	private String defaultVariantCombination;
	private boolean isNew;
	private int minAge;
	private int maxAge;
	private int minShoeSize;
	private int maxShoeSize;
	private String manufacturerName;
	private String manufacturerWebsite;
	private String manufacturerLogo;
	private String manufacturerDescription;
	private ArrayList<String> discounts = new ArrayList<String>();
	private ArrayList<String> gpr = new ArrayList<String>();
	private ArrayList<Product> products = new ArrayList<Product>();
	
	public int getProductsCount() {
		return this.products.size();
	}
	
	public void clearProducts() {
		this.products.clear();
	}
	
	public void setProductAt(int pos, Product product) {
		if (product == this || pos < 0 || pos >= this.products.size()
				|| this.products.contains(product)) {
			return;
		}
		this.products.set(pos, product);
	}
	
	public Product getProductAt(int pos) {
		if (pos < 0 || pos >= this.products.size()) {
			return null;
		}
		return this.products.get(pos);
	}
	
	public void addProduct(Product p) {
		if (p == this || products.contains(p))
			return;
		products.add(p);
	}
	
	public void removeProduct(Product p) {
		if (!products.contains(p))
			return;
		products.remove(p);
	}
	
	public ArrayList<String> getGpr() {
		return new ArrayList<String>(gpr);
	}
	
	public void clearGprs() {
		gpr.clear();
	}
	
	public void addGpr(String g) {
		if (gpr.contains(g))
			return;
		gpr.add(g);
	}
	
	public void removeGpr(String g) {
		if (!gpr.contains(g))
			return;
		gpr.remove(g);
	}
	
	public ArrayList<String> getDiscounts() {
		return new ArrayList<String>(discounts);
	}
	
	public void clearDiscounts() {
		discounts.clear();
	}
	
	public void addDiscount(String d) {
		if (discounts.contains(d))
			return;
		discounts.add(d);
	}
	
	public void removeDicount(String d) {
		if (!discounts.contains(d))
			return;
		discounts.remove(d);
	}
	
	public Product() {
		
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public String getVariantId() {
		return variantId;
	}
	
	public void setVariantId(String variantId) {
		this.variantId = variantId;
	}
	
	public double getBasePrice() {
		return basePrice;
	}
	
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	
	public double getCurrentPrice() {
		return currentPrice;
	}
	
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public String getShortDescription() {
		return shortDescription;
	}
	
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
	public String getLongDescription() {
		return longDescription;
	}
	
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	
	public Date getTimeCreated() {
		return timeCreated;
	}
	
	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}
	
	public Date getTimeUpdated() {
		return timeUpdated;
	}
	
	public void setTimeUpdated(Date timeUpdated) {
		this.timeUpdated = timeUpdated;
	}
	
	public String getDefaultVariantCombination() {
		return defaultVariantCombination;
	}
	
	public void setDefaultVariantCombination(String defaultVariantCombination) {
		this.defaultVariantCombination = defaultVariantCombination;
	}
	
	public boolean isNew() {
		return isNew;
	}
	
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	
	public int getMinAge() {
		return minAge;
	}
	
	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}
	
	public int getMaxAge() {
		return maxAge;
	}
	
	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}
	
	public int getMinShoeSize() {
		return minShoeSize;
	}
	
	public void setMinShoeSize(int minShoeSize) {
		this.minShoeSize = minShoeSize;
	}
	
	public int getMaxShoeSize() {
		return maxShoeSize;
	}
	
	public void setMaxShoeSize(int maxShoeSize) {
		this.maxShoeSize = maxShoeSize;
	}
	
	public String getManufacturerName() {
		return manufacturerName;
	}
	
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	
	public String getManufacturerWebsite() {
		return manufacturerWebsite;
	}
	
	public void setManufacturerWebsite(String website) {
		this.manufacturerWebsite = website;
	}
	
	public String getManufacturerLogo() {
		return manufacturerLogo;
	}
	
	public void setManufacturerLogo(String logo) {
		this.manufacturerLogo = logo;
	}
	
	public String getManufacturerDescription() {
		return manufacturerDescription;
	}
	
	public void setManufacturerDescription(String description) {
		this.manufacturerDescription = description;
	}
}
