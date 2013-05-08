package dk.tweenstyle.android.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MiniBarFragment extends Fragment {

	Button home,basket,wishlist,acc;
	
	public MiniBarFragment() {
	
	}
	
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
//		View view = super.onCreateView(inflater, container, savedInstanceState);
		 
		 home=(Button) container.findViewById(R.id.btnHome);
		basket=(Button) container.findViewById(R.id.btnBasket);
		wishlist=(Button) container.findViewById(R.id.btnWishlist);
		acc=(Button) container.findViewById(R.id.btnAccount);
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
		return inflater.inflate(R.layout.menu_bar, container, false);
	}
	
	
	
}
