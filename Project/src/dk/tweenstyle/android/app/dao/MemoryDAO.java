package dk.tweenstyle.android.app.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dk.tweenstyle.android.app.model.Discount;
import dk.tweenstyle.android.app.model.Group;
import dk.tweenstyle.android.app.model.Product;
import dk.tweenstyle.android.app.model.Settings;

public class MemoryDAO {
	
	private static MemoryDAO instance = null;
	
	private ArrayList<Product> products = new ArrayList<Product>();
	private Map<String, Group> groups = new HashMap<String, Group>();
	private ArrayList<Discount> discounts = new ArrayList<Discount>();
	private Settings settings = null;
	
	private MemoryDAO() {
	}
	
	public static MemoryDAO getInstance() {
		if (instance == null) {
			synchronized (MemoryDAO.class) {
				if (instance == null) {
					instance = new MemoryDAO();
				}
			}
		}
		return instance;
	}
	
	public int getTotalProducts() {
		return this.products.size();
	}
	
	public Product addProduct(Product product) {
		products.add(product);
		return product;
	}
	
	public boolean removeProduct(Product product) {
		return products.remove(product);
	}
	
	public void hookupGroups() {
		for (Group g : this.groups.values()) {
			if (g != null) {
				List<String> parents = g.getGroups();
				if (parents != null) {
					for (String parentID : parents) {
						if (parentID != null) {
							Group parent = this.groups.get(parentID);
							if (parent != null) {
								parent.addChild(g);
							}
						}
					}
				}
			}
		}
	}
	
	public void hookupProducts() {
		for (Product p : this.products) {
			if (p != null) {
				List<String> parents = p.getGpr();
				if (parents != null) {
					for (String parentID : parents) {
						if (parentID != null) {
							Group parent = this.groups.get(parentID);
							if (parent != null) {
								parent.addProduct(p);
							}
						}
					}
				}
			}
		}
	}
	
	public List<Product> getFlatProductList(Group g) {
		if (g == null){
			return new LinkedList<Product>();
		}
		HashSet<Group> processed = new HashSet<Group>();
		LinkedList<Group> queue = new LinkedList<Group>();
		List<Product> products = g.getProducts();
		List<Product> result = new LinkedList<Product>();
		if(products != null){
			result.addAll(products);
		}
		List<Group> children = g.getChildren();
		processed.add(g);
		if (children != null) {
			queue.addAll(children);
			while(!queue.isEmpty()){
				g = queue.poll();
				if(g != null && !processed.contains(g)){
					products = g.getProducts();
					if(products != null){
						result.addAll(products);
					}

					children = g.getChildren();
					if (children != null) {
						queue.addAll(children);
					}
					
					processed.add(g);
				}
			}
		}
		return result;
	}
	
	public Group addGroup(Group group) {
		if (group != null) {
			String id = group.getId();
			if (id != null) {
				groups.put(id, group);
			}
		}
		return group;
	}
	
	public Group removeGroup(Group group) {
		if (group != null) {
			String id = group.getId();
			if (id != null) {
				return groups.remove(id);
			}
		}
		return null;
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
	
	public ArrayList<Discount> getDiscounts() {
		return discounts;
	}
	
	public Map<String, Group> getGroups() {
		return groups;
	}
	
	public Group getGroupById(String groupID) {
		return this.groups.get(groupID);
	}
	
	public ArrayList<Product> getProducts() {
		return products;
	}
	
}
