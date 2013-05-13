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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import dk.tweenstyle.android.app.dao.MemoryDAO;
import dk.tweenstyle.android.app.model.Group;
import dk.tweenstyle.android.app.model.Settings;

public class GroupListFragment extends Fragment {
	private List<Group> groups;
	
	public List<Group> getGroups() {
		return groups;
	}
	
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			container = new FrameLayout(this.getActivity());
			Log.d("trouble",
					"Problem inflating GroupListFragment - container was null. ",
					new Exception());
			// return null;
		}
		View result = inflater.inflate(R.layout.fragment_groups, container,
				false);
		
		final ListView listview = (ListView) result
				.findViewById(R.id.groupview);
		
		List<Group> list = this.groups;
		
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
				
				List<Group> children = new ArrayList<Group>();
				Group group = null;
				MemoryDAO dao = MemoryDAO.getInstance();
				// List<Product> productList = null;
				if (dao != null) {
					group = dao.getGroupById(groupID);
					if (group != null) {
						children = group.getChildren();
						// productList = dao.getFlatProductList(group);
					}
				}
				if (children != null) {
					list = children;
				}
			}
		}
		
		if (list == null) {
			list = new ArrayList<Group>();
		}
		
		final GroupArrayAdapter adapter = new GroupArrayAdapter(
				this.getActivity(), R.layout.listview_itemrow_group, list);
		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(new ListView.OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long arg3) {
				Object item = listview.getItemAtPosition(position);
				if (item != null && item instanceof Group) {
					Group g = (Group) item;
					String gID = g.getId();
					Intent i = new Intent(getActivity(), GroupsActivity.class);
					MemoryDAO dao = MemoryDAO.getInstance();
					if (dao != null) {
						Settings settings = dao.getSettings();
						if (settings != null) {
							Log.d("groups", "Sending groupID == " + gID);
							if (gID != null) {
								i.putExtra(
										GroupsActivity.INTENT_EXTRA_KEY_GROUP_ID,
										gID);
							}
						}
					}
					startActivity(i);
				}
			}
		});
		
		return result;
		// return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}
	
	private class GroupArrayAdapter extends ArrayAdapter<Group> {
		
		Context context;
		List<Group> groups;
		
		public GroupArrayAdapter(Context context, int textViewResourceId,
				List<Group> groups) {
			super(context, textViewResourceId, groups);
			this.context = context;
			this.groups = groups;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.listview_itemrow_group,
					parent, false);
			Group group = (Group) groups.get(position);
			TextView textView = (TextView) rowView.findViewById(R.id.rowText);
			ImageView imageView = (ImageView) rowView
					.findViewById(R.id.rowImage);
			textView.setText(group.getName());
			imageView.setImageResource(R.drawable.jeans);
			return rowView;
		}
		
	}
}
