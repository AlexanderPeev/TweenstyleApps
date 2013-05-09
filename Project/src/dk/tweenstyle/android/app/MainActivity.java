package dk.tweenstyle.android.app;

import java.net.URI;
import java.net.URISyntaxException;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
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
		Button btnBoys = (Button) findViewById(R.id.btnBoys);
		btnBoys.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, CategoriesActivity.class);
				MemoryDAO dao = MemoryDAO.getInstance();
				if (dao != null) {
					Settings settings = dao.getSettings();
					if (settings != null) {
						String groupID = settings.getValue(Settings.SETTINGS_KEY_BOYS_GROUP_ID);
						Log.d("groups", "Sending groupID == " + groupID);
						if (groupID != null) {
							i.putExtra(CategoriesActivity.INTENT_EXTRA_KEY_GROUP_ID, groupID);
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
				Intent i = new Intent(MainActivity.this, CategoriesActivity.class);
				MemoryDAO dao = MemoryDAO.getInstance();
				if (dao != null) {
					Settings settings = dao.getSettings();
					if (settings != null) {
						String groupID = settings.getValue(Settings.SETTINGS_KEY_GIRLS_GROUP_ID);
						Log.d("groups", "Sending groupID == " + groupID);
						if (groupID != null) {
							i.putExtra(CategoriesActivity.INTENT_EXTRA_KEY_GROUP_ID, groupID);
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
				Intent i = new Intent(MainActivity.this, CategoriesActivity.class);
				MemoryDAO dao = MemoryDAO.getInstance();
				if (dao != null) {
					Settings settings = dao.getSettings();
					if (settings != null) {
						String groupID = settings.getValue(Settings.SETTINGS_KEY_BRANDS_GROUP_ID);
						Log.d("groups", "Sending groupID == " + groupID);
						if (groupID != null) {
							i.putExtra(CategoriesActivity.INTENT_EXTRA_KEY_GROUP_ID, groupID);
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
			@Override
			public void run() {
				MainJSONLoader jl = new MainJSONLoader();
				try {
					Log.d("json", "Starting to load... ");

					String jsonData = jl.fetchJSONData(new URI("http://tweenstylekopi.net.dynamicweb.dk/Default.aspx?ID=3725"));

					Log.d("json", "Done fetching... ");
					MemoryDAO dao = jl.loadJSONData(jsonData);
					jsonData = null;
					Log.d("json", "Done loading... ");
					dao.hookupGroups();
					Log.d("json", "Done hooking up groups... ");
					dao.hookupProducts();
					Log.d("json", "Done hooking up products... ");

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
