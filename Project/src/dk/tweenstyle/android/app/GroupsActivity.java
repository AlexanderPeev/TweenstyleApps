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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import dk.tweenstyle.android.app.dao.MemoryDAO;
import dk.tweenstyle.android.app.model.Group;
import dk.tweenstyle.android.app.model.Product;

public class GroupsActivity extends FragmentActivity implements
		ViewTransferDelegate<Product>, ProductListProvider, GroupListProvider {
	public static final String INTENT_EXTRA_KEY_GROUP_ID = "GroupID";
	
	private ProductListFragment frgProductList;
	private MiniBarFragment frgMiniBar;
	private GroupListFragment frgGroupList;
	private ProductDetailsFragment frgProductDetails;
	private boolean usingSwipeNavigation = false;
	private ViewPager pnlViewPager;
	private SwipeCollectionPagerAdapter swipeCollectionPagerAdapter;
	private List<Product> productList;
	private List<Group> groupList;
	private String groupID;
	private Group group;
	
	private void ensureDataPreloaded() {
		
		if (this.groupID == null) {
			Intent intent = this.getIntent();
			
			if (intent != null) {
				Bundle extras = intent.getExtras();
				if (extras != null
						&& extras.containsKey(INTENT_EXTRA_KEY_GROUP_ID)) {
					this.groupID = extras.getString(INTENT_EXTRA_KEY_GROUP_ID);
				}
			}
		}
		
		MemoryDAO dao = MemoryDAO.getInstance();
		if (this.group == null) {
			if (dao != null) {
				this.group = dao.getGroupById(groupID);
			}
		}
		
		if (this.group != null) {
			if (this.groupList == null || this.groupList.size() <= 0) {
				this.groupList = this.group.getChildren();
			}
			if (this.productList == null || this.productList.size() <= 0) {
				this.productList = dao.getFlatProductList(this.group);
			}
		}
		
		if (this.groupList == null) {
			this.groupList = new ArrayList<Group>();
		}
		
		if (this.productList == null) {
			this.productList = new ArrayList<Product>();
		}
		
		Log.d("groups", "GroupID == " + this.groupID);
		Log.d("groups", "group == " + this.group);
		Log.d("groups", "children == (total: " + this.groupList.size() + ") "
				+ this.groupList);
		
		Log.d("groups", "productList == (total: " + this.productList.size()
				+ ") ");
	}
	
	// private ViewGroup vgrProductList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_groups);
		
		this.ensureDataPreloaded();
		
		if (this.group != null) {
			this.setTitle(group.getName());
		}
		
		if (this.frgGroupList == null) {
			this.frgGroupList = new GroupListFragment();
			this.frgGroupList.setGroupListProvider(this);
		}
		
		if (this.frgProductList == null) {
			this.frgProductList = new ProductListFragment();
			this.frgProductList.setProductListProvider(this);
		}
		
		if (this.frgMiniBar == null) {
			this.frgMiniBar = new MiniBarFragment();
		}
		
		if (this.frgProductDetails == null) {
			this.frgProductDetails = new ProductDetailsFragment();
			this.frgProductList.setProductViewTransferDelegate(this);
		}
		
		this.frgGroupList.setGroups(groupList);
		this.frgProductList.setProductList(productList);
		
		this.pnlViewPager = GUIUtil.getViewAs(ViewPager.class, this,
				R.id.pnlPager);
		
		// this.vgrProductList = (ViewGroup)
		// this.findViewById(R.id.vgrProductList);
		// if(this.vgrProductList != null){
		// this.vgrProductList.add
		// }
		
		this.usingSwipeNavigation = false;
		
		if (this.pnlViewPager != null) {
			this.usingSwipeNavigation = true;
			FragmentManager fm = this.getSupportFragmentManager();
			if (fm != null) {
				this.swipeCollectionPagerAdapter = new SwipeCollectionPagerAdapter(
						fm);
				
				this.pnlViewPager.setAdapter(this.swipeCollectionPagerAdapter);
				this.pnlViewPager.setCurrentItem(1, true);
				
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
					ft.add(R.id.pnlProductDetails, this.frgProductDetails,
							"productDetails");
					ft.add(R.id.pnlMenuBar, this.frgMiniBar, "miniBar");
					ft.commit();
				}
			}
		}
		
		Button btnProductDetailsBack = GUIUtil.getViewAs(Button.class, this,
				R.id.btnProductDetailsBack);
		if (btnProductDetailsBack != null) {
			btnProductDetailsBack
					.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							ViewGroup productDetailsWrapper = GUIUtil
									.getViewAs(ViewGroup.class,
											GroupsActivity.this,
											R.id.pnlProductDetailsWrapper);
							if (productDetailsWrapper != null) {
								productDetailsWrapper
										.setVisibility(ViewGroup.INVISIBLE);
							}
							ViewGroup productList = GUIUtil.getViewAs(
									ViewGroup.class, GroupsActivity.this,
									R.id.pnlProductList);
							if (productList != null) {
								productList.setVisibility(ViewGroup.VISIBLE);
							}
						}
					});
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
			else if (i == 1) {
				return frgProductList;
			}
			else {
				return frgProductDetails;
			}
		}
		
		@Override
		public int getCount() {
			return 3;
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			if (position == 0) {
				return getResources().getText(R.string.title_fragment_groups);
			}
			else if (position == 1) {
				return getResources().getText(R.string.title_fragment_products);
			}
			else {
				return getResources().getText(
						R.string.title_fragment_product_details);
			}
		}
	}
	
	@Override
	public void onViewTransferRequest(Object source, Product target) {
		if (target != null) {
			if (usingSwipeNavigation) {
				if (this.frgProductDetails != null) {
					this.frgProductDetails.setProduct(target);
					if (this.pnlViewPager != null) {
						this.pnlViewPager.setCurrentItem(2, true);
					}
				}
			}
			else {
				if (this.frgProductDetails != null) {
					this.frgProductDetails.setProduct(target);
					ViewGroup productDetailsWrapper = GUIUtil.getViewAs(
							ViewGroup.class, this,
							R.id.pnlProductDetailsWrapper);
					if (productDetailsWrapper != null) {
						productDetailsWrapper.setVisibility(ViewGroup.VISIBLE);
					}
					ViewGroup productList = GUIUtil.getViewAs(
							ViewGroup.class, GroupsActivity.this,
							R.id.pnlProductList);
					if (productList != null) {
						productList.setVisibility(ViewGroup.INVISIBLE);
					}
				}
			}
		}
	}
	
	@Override
	public List<Group> fetchGroupList() {
		this.ensureDataPreloaded();
		return this.groupList;
	}
	
	@Override
	public List<Product> fetchProductList() {
		this.ensureDataPreloaded();
		return this.productList;
	}
	
}
