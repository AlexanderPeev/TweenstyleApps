package dk.tweenstyle.android.app;

import dk.tweenstyle.android.app.model.Product;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ProductDetailsFragment extends Fragment {
	private Product product;
	private View view;
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
		this.updateView();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			container = new FrameLayout(this.getActivity());
			Log.d("trouble",
					"Problem inflating ProductDetailsFragment - container was null. ",
					new Exception());
			// return null;
		}
		View result = inflater.inflate(R.layout.fragment_product, container,
				false);
		
		this.view = result;
		
		this.updateView();
		
		return result;
	}
	
	private void updateView() {
		if (this.view != null) {
			Product product = this.product;
			TextView lblProductManufacturer = GUIUtil.getViewAs(TextView.class, this.view, R.id.lblProductManufacturer);
			TextView lblProductName = GUIUtil.getViewAs(TextView.class, this.view, R.id.lblProductName);
			TextView lblProductNumber = GUIUtil.getViewAs(TextView.class, this.view, R.id.lblProductNumber);
			TextView lblProductOldPrice = GUIUtil.getViewAs(TextView.class, this.view, R.id.lblProductOldPrice);
			TextView lblProductPrice = GUIUtil.getViewAs(TextView.class, this.view, R.id.lblProductPrice);
			
			if (product != null) {
				double basePrice = product.getBasePrice();
				double currentPrice = product.getCurrentPrice();
				if(lblProductManufacturer != null){
					lblProductManufacturer.setText(product.getManufacturerName());
				}
				if(lblProductName != null){
					lblProductName.setText(product.getName());
				}
				if(lblProductNumber != null){
					lblProductNumber.setText(product.getNumber());
				}
				if(lblProductOldPrice != null){
					lblProductName.setText(basePrice + "");
					if(basePrice != currentPrice){
						lblProductName.setVisibility(View.VISIBLE);
					}
					else{
						lblProductName.setVisibility(View.GONE);
					}
				}
				if(lblProductPrice != null){
					lblProductName.setText(currentPrice + "");
				}
			}
			else {
				double basePrice = 0;
				double currentPrice = 0;
				if(lblProductManufacturer != null){
					lblProductManufacturer.setText("");
				}
				if(lblProductName != null){
					lblProductName.setText("");
				}
				if(lblProductNumber != null){
					lblProductNumber.setText("");
				}
				if(lblProductOldPrice != null){
					lblProductName.setText(basePrice + "");
					if(basePrice != currentPrice){
						lblProductName.setVisibility(View.VISIBLE);
					}
					else{
						lblProductName.setVisibility(View.GONE);
					}
				}
				if(lblProductPrice != null){
					lblProductName.setText(currentPrice + "");
				}
			}
		}
	}
}
