package dk.tweenstyle.android.app;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import dk.tweenstyle.android.app.model.Product;

public class ProductListFragment extends Fragment {
	
	private ProductListProvider productListProvider;
	
	public ProductListProvider getProductListProvider() {
		return productListProvider;
	}
	
	public void setProductListProvider(ProductListProvider productListProvider) {
		this.productListProvider = productListProvider;
	}
	
	private ViewTransferDelegate<Product> productViewTransferDelegate;
	
	public ViewTransferDelegate<Product> getProductViewTransferDelegate() {
		return productViewTransferDelegate;
	}
	
	public void setProductViewTransferDelegate(
			ViewTransferDelegate<Product> productViewTransferDelegate) {
		this.productViewTransferDelegate = productViewTransferDelegate;
	}
	
	public ProductListFragment() {
		
	}
	
	private List<Product> productList;
	
	public List<Product> getProductList() {
		return productList;
	}
	
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			container = new FrameLayout(this.getActivity());
			Log.d("trouble",
					"Problem inflating ProductListFragment - container was null. ",
					new Exception());
			// return null;
		}
		View result = inflater.inflate(R.layout.fragment_products, container,
				false);
		
		final AbsListView listview = GUIUtil.getViewAs(AbsListView.class,
				result, R.id.productview);
		
		List<Product> list = this.productList;
		
		if (list == null && this.productListProvider != null) {
			list = this.productListProvider.fetchProductList();
		}
		
		if (list == null) {
			list = new ArrayList<Product>();
		}
		
		final ProductArrayAdapter adapter = new ProductArrayAdapter(
				this.getActivity(), R.layout.listview_itemrow_product, list);
		
		if (listview != null) {
			
			if (listview instanceof ListView) {
				ListView lv = (ListView) listview;
				lv.setAdapter(adapter);
			}
			else if (listview instanceof GridView) {
				GridView gv = (GridView) listview;
				gv.setAdapter(adapter);
			}
			
			listview.setOnItemClickListener(new ListView.OnItemClickListener() {
				
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					Object item = listview.getItemAtPosition(position);
					if (item != null && item instanceof Product) {
						Product product = (Product) item;
						ViewTransferDelegate<Product> delegate = productViewTransferDelegate;
						if (delegate != null) {
							delegate.onViewTransferRequest(
									ProductListFragment.this, product);
						}
						else {
							Log.d("uievent",
									"No product details delegate to transfer product to, in ProductListFragment. ");
						}
					}
				}
			});
			
		}
		
		return result;
	}
	
	// class Product{
	//
	// private String name;
	// private int image;
	//
	// public Product(String name, int image) {
	// this.name = name;
	// this.image = image;
	// }
	// }
	
	private class ProductArrayAdapter extends ArrayAdapter<Product> {
		
		private Context context;
		private List<Product> objects;
		
		public ProductArrayAdapter(Context context, int textViewResourceId,
				List<Product> objects) {
			super(context, textViewResourceId, objects);
			this.context = context;
			this.objects = objects;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.listview_itemrow_product,
					parent, false);
			Product product = (Product) objects.get(position);
			TextView textView = (TextView) rowView
					.findViewById(R.id.productname);
			// ImageView imageView = (ImageView)
			// rowView.findViewById(R.id.productImage);
			Log.d("groups-product", "Product: " + product.getName());
			textView.setText(product.getName());
			// imageView.setImageBitmap(product.getImage());
			return rowView;
		}
		
	}
	
}
