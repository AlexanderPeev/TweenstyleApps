package dk.tweenstyle.android.app;

import daoTest.ProductSource;
import modelTest.Product;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
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
public class BasketActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basket);

		final ListView myListView = (ListView)findViewById(R.id.listView1);
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