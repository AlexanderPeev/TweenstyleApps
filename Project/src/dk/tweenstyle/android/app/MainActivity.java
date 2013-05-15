package dk.tweenstyle.android.app;

import java.net.URI;
import java.net.URISyntaxException;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import dk.tweenstyle.android.app.dao.MainJSONLoader;
import dk.tweenstyle.android.app.dao.MemoryDAO;
import dk.tweenstyle.android.app.model.Settings;

/**
 * Reverted manually by Aleksandar.
 * 
 */
public class MainActivity extends FragmentActivity {
	// Thomas and Piotr
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Resources res = this.getResources();
		if(res != null){
			DisplayMetrics dm = res.getDisplayMetrics();
			if(dm != null){
				int dpi = dm.densityDpi;
				Log.d("dpi", "DPI: " + dpi + " " + dm);
				if(dpi <= DisplayMetrics.DENSITY_LOW){
					Log.d("dpi", "DPI is LOW");
				}
				else if(dpi <= DisplayMetrics.DENSITY_MEDIUM){
					Log.d("dpi", "DPI is MEDIUM");
				}
				else if(dpi <= DisplayMetrics.DENSITY_TV){
					Log.d("dpi", "DPI is TV");
				}
				else if(dpi <= DisplayMetrics.DENSITY_HIGH){
					Log.d("dpi", "DPI is HIGH");
				}
				else if(dpi <= DisplayMetrics.DENSITY_XHIGH){
					Log.d("dpi", "DPI is XHIGH");
				}
				else if(dpi <= DisplayMetrics.DENSITY_XXHIGH){
					Log.d("dpi", "DPI is XXHIGH");
				}
				else{
					Log.d("dpi", "DPI is over XXHIGH");
				}
			}
		}
		
		Button btnBoys = (Button) findViewById(R.id.btnBoys);
		btnBoys.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, GroupsActivity.class);
				MemoryDAO dao = MemoryDAO.getInstance();
				if (dao != null) {
					Settings settings = dao.getSettings();
					if (settings != null) {
						String groupID = settings.getValue(Settings.SETTINGS_KEY_BOYS_GROUP_ID);
						Log.d("groups", "Sending groupID == " + groupID);
						if (groupID != null) {
							i.putExtra(GroupsActivity.INTENT_EXTRA_KEY_GROUP_ID, groupID);
						}
					}
				}
				startActivity(i);
			}
		});

		Button btnGirls = (Button) findViewById(R.id.btnGirls);
		btnGirls.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, GroupsActivity.class);
				MemoryDAO dao = MemoryDAO.getInstance();
				if (dao != null) {
					Settings settings = dao.getSettings();
					if (settings != null) {
						String groupID = settings.getValue(Settings.SETTINGS_KEY_GIRLS_GROUP_ID);
						Log.d("groups", "Sending groupID == " + groupID);
						if (groupID != null) {
							i.putExtra(GroupsActivity.INTENT_EXTRA_KEY_GROUP_ID, groupID);
						}
					}
				}
				startActivity(i);
			}
		});

		Button btnTweens = (Button) findViewById(R.id.btnTweens);
		btnTweens.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, GroupsActivity.class);
				MemoryDAO dao = MemoryDAO.getInstance();
				if (dao != null) {
					Settings settings = dao.getSettings();
					if (settings != null) {
						String groupID = settings.getValue(Settings.SETTINGS_KEY_BRANDS_GROUP_ID);
						Log.d("groups", "Sending groupID == " + groupID);
						if (groupID != null) {
							i.putExtra(GroupsActivity.INTENT_EXTRA_KEY_GROUP_ID, groupID);
						}
					}
				}
				startActivity(i);
			}
		});
		initMainJSONLoader();
	}

	private void initMainJSONLoader() {
		final ProgressDialog pd = ProgressDialog.show(this, "Please wait", "");
		Log.d("json", "Starting init... ");
		Thread t = new Thread(new Runnable() {
			public void setMessage(final int resourceId){
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						pd.setMessage(getString(resourceId));
					}
				});
			}
			
			@Override
			public void run() {
				MainJSONLoader jl = new MainJSONLoader();
				try {
					Log.d("json", "Starting to load... ");
					setMessage(R.string.message_fetching_data);
					String jsonData = jl.fetchJSONData(new URI("http://tweenstylekopi.net.dynamicweb.dk/Default.aspx?ID=3725"));

					Log.d("json", "Done fetching... ");
					setMessage(R.string.message_loading_data);
					MemoryDAO dao = jl.loadJSONData(jsonData);
					jsonData = null;
					Log.d("json", "Done loading... ");
					setMessage(R.string.message_hooking_up);
					dao.hookupGroups();
					Log.d("json", "Done hooking up groups... ");
					dao.hookupProducts();
					Log.d("json", "Done hooking up products... ");
					dao.hookupVariants();
					Log.d("json", "Done hooking up variants... ");

					Log.d("json", "Total products: " + dao.getTotalProducts());

				} catch (URISyntaxException e) {
					Log.d("json", "Malformed uri: ", e);
				}
				pd.dismiss();
			}
		});

		t.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
