package dk.tweenstyle.android.app;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
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
	public static final String INTENT_EXTRA_KEY_GROUP_ID = "GroupID";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categories);
		
		Intent intent = this.getIntent();
		
		String groupID = null;
		
		if (intent != null) {
			Bundle extras = intent.getExtras();
			if (extras != null && extras.containsKey(INTENT_EXTRA_KEY_GROUP_ID)) {
				groupID = extras.getString(INTENT_EXTRA_KEY_GROUP_ID);
			}
		}
		
		final ListView listview = (ListView) findViewById(R.id.listView1);
		
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long arg3) {
				Log.i("MyLog", "DONE DONE Listener Is set!");
			}
		});
		List<Group> children = new ArrayList<Group>();
		MemoryDAO dao = MemoryDAO.getInstance();
		if (dao != null) {
			Group group = dao.getGroupById(groupID);
			if (group != null) {
				children = group.getChildren();
			}
		}
		final GroupArrayAdapter adapter = new GroupArrayAdapter(this,
				R.layout.category_item_row, children);
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
		List<Group> objects;
		
		public GroupArrayAdapter(Context context, int textViewResourceId,
				List<Group> objects) {
			super(context, textViewResourceId, objects);
			this.context = context;
			this.objects = objects;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.category_item_row, parent,
					false);
			Group group = (Group) objects.get(position);
			TextView textView = (TextView) rowView.findViewById(R.id.rowText);
			ImageView imageView = (ImageView) rowView
					.findViewById(R.id.rowImage);
			textView.setText(group.getName());
			imageView.setImageResource(R.drawable.jeans);
			return rowView;
		}
		
	}
	
}
