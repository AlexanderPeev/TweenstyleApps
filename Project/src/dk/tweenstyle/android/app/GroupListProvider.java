package dk.tweenstyle.android.app;

import java.util.List;

import dk.tweenstyle.android.app.model.Group;

public interface GroupListProvider {
	List<Group> fetchGroupList();
}
