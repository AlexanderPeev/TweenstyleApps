package dk.tweenstyle.android.app.service;

import modelTest.Product;

public class ProductComparatorSortByPrice implements ProductComparator {
	private boolean descending = false;
	
	public ProductComparatorSortByPrice() {
	}
	
	public ProductComparatorSortByPrice(boolean descending) {
		this.descending = descending;
	}
	
	public boolean isDescending() {
		return this.descending;
	}
	
	public void setDescending(boolean descending) {
		this.descending = descending;
	}
	
	@Override
	public int compare(Product a, Product b) {
		int result = 0;
		if (a != null && b != null) {
			result = Double.compare(a.getPrice(), b.getPrice());
			if (this.descending) {
				result *= -1;
			}
		}
		return result;
	}
}
