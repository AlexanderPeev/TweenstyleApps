package modelTest;

import android.graphics.Bitmap;

/**
 * This is a simple Product class, made it possible for me to test it out.
 * @author Christopher
 *
 */

public class Product {
	private Bitmap image;
	private String productName;
	private double price;

	public Product() {
	}

	public Product(String pName, double price) {
		this.setProductName(pName);
		this.price = price;
		this.image = null;
	}

	@Override
	public String toString() {
		return productName + ":" + price;
	}

	public Product(String name) {
		this.productName = name;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}
}
