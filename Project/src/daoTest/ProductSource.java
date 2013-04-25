package daoTest;

import java.util.ArrayList;

import modelTest.Product;

/**
 * Tis is just a proto of a DAO with some products, so I could test if the items works.
 * @author Christopher
 *
 */

public class ProductSource {

	private ArrayList<Product> products = new ArrayList<Product>();

	private static ProductSource singleton = null;

	private ProductSource(){
		products.add(new Product("Shirt", 100));
		products.add(new Product("Jeans", 100));
		products.add(new Product("Sweater", 100));
		products.add(new Product("Socks", 100));
		products.add(new Product("Jacket", 100));		
		products.add(new Product("Gloves", 100));
		products.add(new Product("Underwear", 100));
		products.add(new Product("Pants", 100));		
		products.add(new Product("Shoes", 100));
		products.add(new Product("Cap", 100));
		products.add(new Product("Earrings", 100));		
		products.add(new Product("Tank top", 100));
	}
	public static ProductSource getInstance(){
		if(singleton==null) singleton = new ProductSource();
		return singleton;
	}

	public ArrayList<Product> getProducts(){
		return products;
	}
	
	public Product getSelected(int i){
		Product p = null;
		if(i<=products.size()&& i>=0)
			p = products.get(i);
		return p;
	}
	
	public void deleteSelected(int i){
		if(i<=products.size()&& i>=0)
			products.remove(i);
	}



}
