package dk.tweenstyle.android.app;

import java.io.IOException;
import java.io.InputStream;

import modelTest.Product;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

/**
 * This is the ArrayAdapter I made, so it was possible to set pictures and text
 * in the listview.
 * 
 * @author Christopher
 * 
 * @param <T>
 */
public class MyArrayAdapter<T> extends ArrayAdapter<T> {
	
	static Context context;
	static int layoutResourceId;
	T[] data;
	
	public MyArrayAdapter(Context context, int layoutResourceId, T[] objects) {
		super(context, layoutResourceId, objects);
		MyArrayAdapter.layoutResourceId = layoutResourceId;
		MyArrayAdapter.context = context;
		this.data = objects;
	}
	
	public MyArrayAdapter(OnMenuItemClickListener onMenuItemClickListener,
			int listviewItemrow, T[] objects) {
		super(context, layoutResourceId, objects);
		MyArrayAdapter.layoutResourceId = layoutResourceId;
		MyArrayAdapter.context = context;
		this.data = objects;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// return super.getView(position, convertView, parent);
		View row = convertView;
		final ProductHolder holder;
		Object tag = null;
		if (row == null) {
			LayoutInflater inflater = ((BasketActivity) context)
					.getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			// store data, product
			holder = new ProductHolder();
			holder.imgIcon = (ImageView) row.findViewById(R.id.imgIcon);
			holder.txtTitle = (TextView) row.findViewById(R.id.txtTitle);
			holder.txtPrice = (TextView) row.findViewById(R.id.txtPrice);
			row.setTag(holder);
			
		}
		else if ((tag = row.getTag()) != null
				&& tag instanceof MyArrayAdapter.ProductHolder) {
			holder = (MyArrayAdapter<T>.ProductHolder) tag;
		}
		else {
			holder = null;
		}
		
		if (holder != null) {
			Product product = (Product) data[position];
			holder.imgIcon
					.setImageBitmap(product.getImage() == null ? getBitmapFromAsset(
							context, "images/test.bmp") : product.getImage());
			holder.txtTitle.setText(product.getProductName());
			holder.txtPrice.setText(product.getPrice() + "");
		}
		return row;
	}
	
	public static Bitmap getBitmapFromAsset(Context context, String strName) {
		AssetManager assetManager = context.getAssets();
		
		InputStream istr;
		Bitmap bitmap = null;
		try {
			istr = assetManager.open(strName);
			bitmap = BitmapFactory.decodeStream(istr);
		}
		catch (IOException e) {
			return null;
		}
		
		return bitmap;
	}
	
	class ProductHolder {
		ImageView imgIcon;
		TextView txtTitle;
		TextView txtPrice;
		
		// Product product;
		@Override
		public String toString() {
			return imgIcon.toString() + ":" + txtTitle.getText() + ":"
					+ txtPrice.getText();
		}
	}
}