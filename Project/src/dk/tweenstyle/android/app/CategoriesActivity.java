package dk.tweenstyle.android.app;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
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
import dk.tweenstyle.android.app.dao.MemoryDAO;
import dk.tweenstyle.android.app.model.Group;

public class CategoriesActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categories);

		final ListView listview = (ListView) findViewById(R.id.listView1);

		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
				Log.i("MyLog", "DONE DONE Listener Is set!");
			}
		});

		MemoryDAO.getInstance();
		final GroupArrayAdapter adapter = new GroupArrayAdapter(this, R.layout.category_item_row, MemoryDAO.getGroups());
		listview.setAdapter(adapter);
	}

	// class Group {
	//
	// private String name;
	// private int image;
	//
	// public Group(String name, int image) {
	// this.name = name;
	// this.image = image;
	// }
	// }

	private class GroupArrayAdapter extends ArrayAdapter<Group> {

		Context context;
		ArrayList<Group> objects;

		public GroupArrayAdapter(Context context, int textViewResourceId, ArrayList<Group> objects) {
			super(context, textViewResourceId, objects);
			this.context = context;
			this.objects = objects;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.category_item_row, parent, false);
			Group group = (Group) objects.get(position);
			TextView textView = (TextView) rowView.findViewById(R.id.rowText);
			ImageView imageView = (ImageView) rowView.findViewById(R.id.rowImage);
			textView.setText(group.getName());
			imageView.setImageResource(R.drawable.jeans);
			return rowView;
		}

	}

}
