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

/**
 * Reverted manually by Aleksandar.
 * 
 */
public class MainActivity extends FragmentActivity {
	// thomas
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btnBoys = (Button) findViewById(R.id.btnBoys);
		btnBoys.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, CategoriesActivity.class);
				startActivity(i);
			}
		});

		Button btnGirls = (Button) findViewById(R.id.btnGirls);
		btnGirls.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, CategoriesActivity.class);
				startActivity(i);
			}
		});

		Button btnTweens = (Button) findViewById(R.id.btnTweens);
		btnTweens.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, CategoriesActivity.class);
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

					MemoryDAO dao = jl.loadJSONData(jl
							.fetchJSONData(new URI("http://tweenstylekopi.net.dynamicweb.dk/Default.aspx?ID=3725")));

					Log.d("json", "Done loading... ");

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
