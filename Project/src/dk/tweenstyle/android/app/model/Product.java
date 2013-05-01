package dk.tweenstyle.android.app.model;

import java.sql.Date;
import java.util.ArrayList;

public class Product {

	private int id;
	private Gender gender;
	private int variantId;
	private double basePrice;
	private int number;
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
	private String website;
	private String logo;
	private String description;
	private ArrayList<String> discounts = new ArrayList<String>();
	private ArrayList<String> gpr = new ArrayList<String>();
	private ArrayList<Product> products = new ArrayList<Product>();
	
	void addProduct(Product p) {
		if (products.contains(p))
		return;
		products.add(p);
		}
	
	void removeProduct(Product p) {
		if (!products.contains(p))
		return;
		products.remove(p);
		}
	
	public ArrayList<String> getGpr() {
		return new ArrayList<String>(gpr);
		}
	
	void addGpr(String g) {
		if (gpr.contains(g))
		return;
		gpr.add(g);
		}
	
	void removeGpr(String g) {
		if (!gpr.contains(g))
		return;
		gpr.remove(g);
		}
	
	public ArrayList<String> getDiscounts() {
		return new ArrayList<String>(discounts);
		}
	
	void addDiscount(String d) {
		if (discounts.contains(d))
		return;
		discounts.add(d);
		}
	
	void removeDicount(String d) {
		if (!discounts.contains(d))
		return;
		discounts.remove(d);
		}
	
		
	
	public Product(){

	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public int getVariantId() {
		return variantId;
	}
	public void setVariantId(int variantId) {
		this.variantId = variantId;
	}
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
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

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
