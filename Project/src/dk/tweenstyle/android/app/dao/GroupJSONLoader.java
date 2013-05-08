package dk.tweenstyle.android.app.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import dk.tweenstyle.android.app.model.Group;

public class GroupJSONLoader implements JSONLoader<Group> {

	@Override
	public Group loadObject(JSONObject object) {
		Group group = new Group();

		try {
			JSONArray gArray = object.getJSONArray("parents");
			for (int i = 0; i < gArray.length(); i++) {
				String groupId = gArray.getString(i);
				group.addGroup(groupId);
			}
			
			 String id = object.getString("id");
			 group.setId(id);
			 String name = object.getString("name");
			 group.setName(name);
			
			
		} catch (JSONException e) {
			group = null;
			Log.d("json", "Trouble loading Group object from JSON", e);
		}
		return group;
	}

}
