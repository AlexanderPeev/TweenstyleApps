package dk.tweenstyle.android.app;

import java.net.URI;
import java.net.URISyntaxException;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
		Button btnBoys = (Button) findViewById(R.id.btnBoys);
		btnBoys.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

		Button btnGirls = (Button) findViewById(R.id.btnGirls);
		btnGirls.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

		Button btnTweens = (Button) findViewById(R.id.btnTweens);
		btnTweens.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		initMainJSONLoader();
	}

	private void initMainJSONLoader() {
		ProgressDialog pd = ProgressDialog.show(this, "Please wait", "");
		MainJSONLoader jl = new MainJSONLoader(pd);
		try {
			jl.loadJSONData(jl.fetchJSONData(new URI("http://tweenstylekopi.net.dynamicweb.dk/Default.aspx?ID=3725")));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
