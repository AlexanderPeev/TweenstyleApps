package dk.tweenstyle.android.app.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dk.tweenstyle.android.app.model.Discount;
import dk.tweenstyle.android.app.model.Group;
import dk.tweenstyle.android.app.model.Product;
import dk.tweenstyle.android.app.model.Settings;
import dk.tweenstyle.android.app.model.VariantGroup;
import dk.tweenstyle.android.app.model.VariantOption;

public class MemoryDAO {
	
	private static MemoryDAO instance = null;
	
	private List<Product> products = new LinkedList<Product>();
	private Map<String, Group> groups = new HashMap<String, Group>();
	private List<Discount> discounts = new LinkedList<Discount>();
	private Map<String, VariantGroup> variantGroups = new HashMap<String, VariantGroup>();
	private Map<String, VariantOption> variantOptions = new HashMap<String, VariantOption>();
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
	
	public void hookupVariants() {
		if (this.variantGroups != null && this.variantOptions != null) {
			for (VariantGroup group : this.variantGroups.values()) {
				if (group != null) {
					for (VariantOption option : group) {
						if (option != null) {
							String id = option.getId();
							if (id != null) {
								this.variantOptions.put(id, option);
							}
						}
					}
				}
			}
		}
	}
	
	public List<Product> getFlatProductList(Group g) {
		if (g == null) {
			return new LinkedList<Product>();
		}
		HashSet<Group> processed = new HashSet<Group>();
		LinkedList<Group> queue = new LinkedList<Group>();
		List<Product> products = g.getProducts();
		HashMap<String, Product> result = new HashMap<String, Product>();
		if (products != null) {
			for (Product p : products) {
				String id = null;
				if (p != null && (id = p.getId()) != null) {
					result.put(id, p);
				}
			}
		}
		List<Group> children = g.getChildren();
		processed.add(g);
		if (children != null) {
			queue.addAll(children);
			while (!queue.isEmpty()) {
				g = queue.poll();
				if (g != null && !processed.contains(g)) {
					products = g.getProducts();
					if (products != null) {
						for (Product p : products) {
							String id = null;
							if (p != null && (id = p.getId()) != null) {
								result.put(id, p);
							}
						}
					}
					
					children = g.getChildren();
					if (children != null) {
						queue.addAll(children);
					}
					
					processed.add(g);
				}
			}
		}
		return new LinkedList<Product>(result.values());
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
	
	public List<Discount> getDiscounts() {
		return discounts;
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
	
	public Map<String, Group> getGroups() {
		return groups;
	}
	
	public Group getGroupById(String groupID) {
		return this.groups.get(groupID);
	}
	
	public VariantOption getVariantOptionById(String voID) {
		return this.variantOptions.get(voID);
	}
	
	public VariantGroup getVariantGroupById(String vgID) {
		return this.variantGroups.get(vgID);
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	public Map<String, VariantGroup> getVariantGroups() {
		return variantGroups;
	}
	
	public VariantGroup addVariantGroup(VariantGroup variantGroup) {
		String id = null;
		if (variantGroup != null && ((id = variantGroup.getId()) != null)) {
			variantGroups.put(id, variantGroup);
		}
		return variantGroup;
	}
	
	public VariantGroup removeVariantGroup(VariantGroup variantGroup) {
		VariantGroup result = null;
		if (variantGroup != null) {
			String id = variantGroup.getId();
			if (id != null) {
				result = variantGroups.remove(id);
			}
		}
		return result;
	}
	
}
