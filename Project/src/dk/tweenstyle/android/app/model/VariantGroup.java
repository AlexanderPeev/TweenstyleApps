package dk.tweenstyle.android.app.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VariantGroup implements Iterable<VariantOption> {
	private String id, name, label;
	private boolean colors;
	private List<VariantOption> variantOptions = new ArrayList<VariantOption>();
	
	public VariantGroup() {
		
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
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public boolean isColors() {
		return colors;
	}
	
	public void setColors(boolean colors) {
		this.colors = colors;
	}
	
	public void clearVariantOptions() {
		if (this.variantOptions != null) {
			this.variantOptions.clear();
		}
	}
	
	public int getVariantOptionsCount() {
		int result = 0;
		if (this.variantOptions != null) {
			result = this.variantOptions.size();
		}
		return result;
	}
	
	public VariantOption getVariantOptionAt(int pos) {
		VariantOption result = null;
		if (this.variantOptions != null && pos >= 0
				&& pos < this.variantOptions.size()) {
			result = this.variantOptions.get(pos);
		}
		return result;
	}
	
	public void setVariantOptionAt(int pos, VariantOption value) {
		if (this.variantOptions != null && value != null
				&& !this.variantOptions.contains(value) && pos >= 0
				&& pos < this.variantOptions.size()) {
			this.variantOptions.set(pos, value);
		}
	}
	
	public void addVariantOption(VariantOption option) {
		if (option != null && this.variantOptions != null
				&& !this.variantOptions.contains(option)) {
			this.variantOptions.add(option);
		}
	}
	
	public void removeVariantOption(VariantOption option) {
		if (option != null && this.variantOptions != null
				&& this.variantOptions.contains(option)) {
			this.variantOptions.remove(option);
		}
	}

	@Override
	public Iterator<VariantOption> iterator() {
		return this.variantOptions.iterator();
	}
}
