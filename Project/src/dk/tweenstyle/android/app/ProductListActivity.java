package dk.tweenstyle.android.app;

import java.util.ArrayList;

import modelTest.Product;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ProductListActivity extends FragmentActivity {

	
	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_products);
	    

	    final ListView listview = (ListView) findViewById(R.id.productview);
	    

	    final ArrayList<Product> list = new ArrayList<Product>();

	    list.add(new Product("Hummel",550.00));
	    list.add(new Product("SUPERDRYJPN",600.00));
	    
	    final ProductArrayAdapter adapter = new ProductArrayAdapter(this,
	        R.layout.listview_itemrow_product, list);
	    listview.setAdapter(adapter);
	    
	    listview.setOnItemClickListener(new ListView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				
				
			}
		});
	    
	    
	    
	    
	  }
	
//	class Product{
//	
//		private String name;
//		private int image;
//
//		public Product(String name, int image) {
//			this.name = name;
//			this.image = image;
//		}
//	}
	

	  private class ProductArrayAdapter extends ArrayAdapter<Product> {

		  Context context;
		  ArrayList<Product> objects;
		  
	    public ProductArrayAdapter(Context context, int textViewResourceId,
	        ArrayList<Product> objects) {
	      super(context, textViewResourceId, objects);
	      this.context = context;
	      this.objects = objects;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	      LayoutInflater inflater = (LayoutInflater) context
	          .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	      View rowView = inflater.inflate(R.layout.listview_itemrow_product, parent, false);
	      Product product = (Product) objects.get(position);
	      TextView textView = (TextView) rowView.findViewById(R.id.productname);
	      ImageView imageView = (ImageView) rowView.findViewById(R.id.productImage);
	      textView.setText(product.getProductName());
	      imageView.setImageBitmap(product.getImage());
	      return rowView;
	    }

	  }


}
