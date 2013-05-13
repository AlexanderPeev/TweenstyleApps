package dk.tweenstyle.android.app;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import dk.tweenstyle.android.app.dao.MemoryDAO;
import dk.tweenstyle.android.app.model.Group;
import dk.tweenstyle.android.app.model.Product;

public class GroupsActivity extends FragmentActivity {
	public static final String INTENT_EXTRA_KEY_GROUP_ID = "GroupID";
	
	private ProductListFragment frgProductList;
	private MiniBarFragment frgMiniBar;
	private GroupListFragment frgGroupList;
	
	// private ViewGroup vgrProductList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_groups);
		
		Intent intent = this.getIntent();
		
		String groupID = null;
		
		if (intent != null) {
			Bundle extras = intent.getExtras();
			if (extras != null && extras.containsKey(INTENT_EXTRA_KEY_GROUP_ID)) {
				groupID = extras.getString(INTENT_EXTRA_KEY_GROUP_ID);
			}
		}
		
		List<Group> children = new ArrayList<Group>();
		Group group = null;
		MemoryDAO dao = MemoryDAO.getInstance();
		List<Product> productList = null;
		if (dao != null) {
			group = dao.getGroupById(groupID);
			if (group != null) {
				children = group.getChildren();
				productList = dao.getFlatProductList(group);
			}
		}
		
		if (productList == null) {
			productList = new ArrayList<Product>();
		}
		
		Log.d("groups", "GroupID == " + groupID);
		Log.d("groups", "group == " + group);
		Log.d("groups", "children == (total: " + children.size() + ") "
				+ children);
		
		Log.d("groups", "productList == (total: " + productList.size() + ") ");
		
		if (this.frgGroupList == null) {
			this.frgGroupList = new GroupListFragment();
		}
		
		if (this.frgProductList == null) {
			this.frgProductList = new ProductListFragment();
		}
		
		if (this.frgMiniBar == null) {
			this.frgMiniBar = new MiniBarFragment();
		}
		
		this.frgGroupList.setGroups(children);
		this.frgProductList.setProductList(productList);
		
		ViewPager pnlViewPager = GUIUtil.getViewAs(ViewPager.class, this,
				R.id.pnlPager);
		
		// this.vgrProductList = (ViewGroup)
		// this.findViewById(R.id.vgrProductList);
		// if(this.vgrProductList != null){
		// this.vgrProductList.add
		// }
		
		if (pnlViewPager != null) {
			FragmentManager fm = this.getSupportFragmentManager();
			if (fm != null) {
				SwipeCollectionPagerAdapter swipeCollectionPagerAdapter = new SwipeCollectionPagerAdapter(fm);
				
				pnlViewPager.setAdapter(swipeCollectionPagerAdapter);
				
				FragmentTransaction ft = fm.beginTransaction();
				if (ft != null) {
					ft.add(R.id.pnlMenuBar, this.frgMiniBar, "miniBar");
					ft.commit();
				}
			}
		}
		else {
			FragmentManager fm = this.getSupportFragmentManager();
			if (fm != null) {
				FragmentTransaction ft = fm.beginTransaction();
				if (ft != null) {
					ft.add(R.id.pnlGroupList, this.frgGroupList, "groupList");
					ft.add(R.id.pnlProductList, this.frgProductList,
							"productList");
					ft.add(R.id.pnlMenuBar, this.frgMiniBar, "miniBar");
					ft.commit();
				}
			}
		}
		
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
	
	public class SwipeCollectionPagerAdapter extends FragmentStatePagerAdapter {
		public SwipeCollectionPagerAdapter(FragmentManager fm) {
			super(fm);
		}
		
		@Override
		public Fragment getItem(int i) {
			if (i == 0) {
				return frgGroupList;
			}
			else {
				return frgProductList;
			}
		}
		
		@Override
		public int getCount() {
			return 2;
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			if (position == 0) {
				return getResources().getText(R.string.title_fragment_groups);
			}
			else {
				return getResources().getText(R.string.title_fragment_products);
			}
		}
	}
	
}
