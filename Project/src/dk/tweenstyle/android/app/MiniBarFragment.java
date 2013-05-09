package dk.tweenstyle.android.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MiniBarFragment extends Fragment {

	public MiniBarFragment() {

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if(container == null){
			container = new RelativeLayout(this.getActivity());
			Log.d("trouble", "Problem inflating MiniBarFragment - container was null. ", new Exception());
			//return null;
		}
		View result = inflater.inflate(R.layout.menu_bar, container, false);
		Button home, basket, wishlist, acc;

		home = (Button) result.findViewById(R.id.btnHome);
		basket = (Button) result.findViewById(R.id.btnBasket);
		wishlist = (Button) result.findViewById(R.id.btnWishlist);
		acc = (Button) result.findViewById(R.id.btnAccount);
		
		home.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(v.getContext(), MainActivity.class);
				startActivity(i);

			}
		});
		basket.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(v.getContext(), BasketActivity.class);
				startActivity(i);

			}
		});
		wishlist.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		acc.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		return result;
	}

}
