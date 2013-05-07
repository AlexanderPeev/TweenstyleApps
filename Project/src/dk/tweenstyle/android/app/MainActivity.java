package dk.tweenstyle.android.app;

import java.net.URI;
import java.net.URISyntaxException;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import dk.tweenstyle.android.app.dao.MainJSONLoader;

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
		initMainJSONLoader();// Piotr Suski
	}

	private void initMainJSONLoader() {// Piotr Suski
		ProgressDialog pd = ProgressDialog.show(this, "", "Please Wait...", true, false);
		MainJSONLoader jl = new MainJSONLoader(pd);
		try {
			jl.loadJSONData(jl.fetchJSONData(new URI("http://tweenstylekopi.net.dynamicweb.dk/Default.aspx?ID=3725")));
		} catch (URISyntaxException e) {
			Log.e("MainActivity", "Unable to fetch data from URL. " + e);
		} catch (InterruptedException e) {
			Log.e("MainActivity", "Coœ nie tak z w¹tkiem. " + e);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
