package dk.tweenstyle.android.app;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import dk.tweenstyle.android.app.dao.MemoryDAO;
import dk.tweenstyle.android.app.model.Group;
import dk.tweenstyle.android.app.model.Product;

public class ProductListFragment extends Fragment {
	
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
		
		if (list == null) {
			Activity activity = this.getActivity();
			if (activity != null) {
				Intent intent = activity.getIntent();
				
				String groupID = null;
				
				if (intent != null) {
					Bundle extras = intent.getExtras();
					if (extras != null
							&& extras
									.containsKey(GroupsActivity.INTENT_EXTRA_KEY_GROUP_ID)) {
						groupID = extras
								.getString(GroupsActivity.INTENT_EXTRA_KEY_GROUP_ID);
					}
				}
				
				// List<Group> children = new ArrayList<Group>();
				Group group = null;
				MemoryDAO dao = MemoryDAO.getInstance();
				List<Product> productList = null;
				if (dao != null) {
					group = dao.getGroupById(groupID);
					if (group != null) {
						// children = group.getChildren();
						productList = dao.getFlatProductList(group);
					}
				}
				if (productList != null) {
					list = productList;
				}
			}
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
						int arg2, long arg3) {
					
					// TODO: Transfer to product view from here
					
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
