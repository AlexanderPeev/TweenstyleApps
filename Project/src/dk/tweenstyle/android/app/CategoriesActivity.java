package dk.tweenstyle.android.app;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CategoriesActivity extends Activity {

	
	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_categories);

	    final ListView listview = (ListView) findViewById(R.id.listView1);

	    listview.setOnItemClickListener(new OnItemClickListener() {

	    	@Override
	        public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
	            Log.i("MyLog", "DONE DONE Listener Is set!");
				// TODO Auto-generated method stub
				
			}
		});
	    
	    final ArrayList<Category> list = new ArrayList<Category>();

	    list.add(new Category("Jeans",R.drawable.jeans));
	    list.add(new Category("T-Shirt",R.drawable.tshirt));
	    
	    final CategoryArrayAdapter adapter = new CategoryArrayAdapter(this,
	        R.layout.category_item_row, list);
	    listview.setAdapter(adapter);
	  }
	
	class Category {
	
		private String name;
		private int image;

		public Category(String name, int image) {
			this.name = name;
			this.image = image;
		}
	}
	

	  private class CategoryArrayAdapter extends ArrayAdapter<Category> {

		  Context context;
		  ArrayList<Category> objects;
		  
	    public CategoryArrayAdapter(Context context, int textViewResourceId,
	        ArrayList<Category> objects) {
	      super(context, textViewResourceId, objects);
	      this.context = context;
	      this.objects = objects;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	      LayoutInflater inflater = (LayoutInflater) context
	          .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	      View rowView = inflater.inflate(R.layout.category_item_row, parent, false);
	      Category category = (Category) objects.get(position);
	      TextView textView = (TextView) rowView.findViewById(R.id.rowText);
	      ImageView imageView = (ImageView) rowView.findViewById(R.id.rowImage);
	      textView.setText(category.name);
	      imageView.setImageResource(category.image);
	      return rowView;
	    }

	  }




}
