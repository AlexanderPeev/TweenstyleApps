package dk.tweenstyle.android.app;

import daoTest.ProductSource;
import modelTest.Product;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.app.Activity;


/**
 * This is the basket activity, handling the items in the basket.
 * @author Christopher
 *
 */

@SuppressLint("NewApi")
public class BasketActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basket);
		
		Button btngoback = (Button) findViewById(R.id.btngoback);
		btngoback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});

		Button btnCheckout = (Button) findViewById(R.id.btnCheckout);
		btnCheckout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		final ListView myListView = (ListView)findViewById(R.id.listView1);
		
		 myListView.setOnItemClickListener(new OnItemClickListener() {

		    	@Override
		        public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
		            Log.i("MyLog", "DONE DONE Listener Is set!");
					// TODO Auto-generated method stub
					
				}
			});
		
		final MyArrayAdapter<Product> adapter2 = new MyArrayAdapter<Product>(this, R.layout.listview_itemrow_basket, ProductSource.getInstance().getProducts().toArray(new Product[0]));
		myListView.setAdapter(adapter2);

		myListView.setOnItemLongClickListener (new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
				PopupMenu menu = new PopupMenu(BasketActivity.this, view);
				menu.getMenuInflater().inflate(R.menu.main, menu.getMenu());
				menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						Product p =ProductSource.getInstance().getSelected(position);
						
						if(item.getTitle().equals("Delete Item")){
							ProductSource.getInstance().deleteSelected(position);
							Toast toasting = Toast.makeText(getApplicationContext(), "You deleted " + p.getProductName() + " with " + item.getTitle(), Toast.LENGTH_LONG);
							toasting.show();
							final MyArrayAdapter<Product> adapter2 = new MyArrayAdapter<Product>(this, R.layout.listview_itemrow_basket, ProductSource.getInstance().getProducts().toArray(new Product[0]));
							myListView.setAdapter(adapter2);
						}
						else if(item.equals("See details")){
							//Do Some intet here to a detail activity.
						}
						return true;
					}
				});
				menu.show();
				return false;
			}
		});

		// Create the Array Adapter to bind the array to the List View


	}

	
}