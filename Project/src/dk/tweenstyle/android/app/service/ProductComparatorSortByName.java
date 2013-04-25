package dk.tweenstyle.android.app.service;

import modelTest.Product;

public class ProductComparatorSortByName implements ProductComparator {
	private boolean descending = false;
	
	public ProductComparatorSortByName() {
	}
	
	public ProductComparatorSortByName(boolean descending) {
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
			result = String.CASE_INSENSITIVE_ORDER.compare(a.getProductName(), b.getProductName());
			if (this.descending) {
				result *= -1;
			}
		}
		return result;
	}
}
