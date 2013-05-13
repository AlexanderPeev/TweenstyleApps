package dk.tweenstyle.android.app;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.View;

public class GUIUtil {
	public static final int REQUEST_CODE_SELECT_PHOTO = 100, REQUEST_CODE_ADD_PRODUCT = 101;
	
	@SuppressWarnings("unchecked")
	public static <T> T getViewAs(Class<T> c, View container, int id) {
		T result = null;
		if (container != null) {
			View v = container.findViewById(id);
			if (v != null && c.isInstance(v)) {
				result = (T) v;
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getViewAs(Class<T> c, Activity container, int id) {
		T result = null;
		if (container != null) {
			View v = container.findViewById(id);
			if (v != null && c.isInstance(v)) {
				result = (T) v;
			}
		}
		return result;
	}
	
	public static void requestImageSelect(Activity activity) {
		if (activity != null) {
			Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
			photoPickerIntent.setType("image/*");
			activity.startActivityForResult(photoPickerIntent,
					REQUEST_CODE_SELECT_PHOTO);
		}
	}
	
	public static ImageResult processImageSelectResult(Activity activity,
			int requestCode, int resultCode, Intent imageReturnedIntent) {
		ImageResult result = new ImageResult();
		if (activity != null && imageReturnedIntent != null) {
			switch (requestCode) {
				case REQUEST_CODE_SELECT_PHOTO:
					if (resultCode == Activity.RESULT_OK) {
						Uri selectedImage = imageReturnedIntent.getData();
						result.setUri(selectedImage);
						if (selectedImage != null) {
							try {
								InputStream imageStream = activity
										.getContentResolver().openInputStream(
												selectedImage);
								result.setBitmap(BitmapFactory
										.decodeStream(imageStream));
							}
							catch (FileNotFoundException e) {
								result.setBitmap(null);
								Log.d("shoppinglist",
										"Problem decoding image. ", e);
							}
						}
					}
			}
		}
		return result;
	}
	
	public static ImageResult processImageSelectResult(Activity activity,
			int requestCode, int resultCode, Intent imageReturnedIntent,
			int requiredSize) {
		ImageResult result = new ImageResult();
		if (activity != null && imageReturnedIntent != null) {
			if (requiredSize <= 0) {
				return processImageSelectResult(activity, requestCode,
						resultCode, imageReturnedIntent);
			}
			switch (requestCode) {
				case REQUEST_CODE_SELECT_PHOTO:
					if (resultCode == Activity.RESULT_OK) {
						Uri selectedImage = imageReturnedIntent.getData();
						result.setUri(selectedImage);
						if (selectedImage != null) {
							try {
								result.setBitmap(decodeImageUri(activity,
										selectedImage, requiredSize));
							}
							catch (FileNotFoundException e) {
								result.setBitmap(null);
								Log.d("shoppinglist",
										"Problem decoding image. ", e);
							}
						}
					}
			}
		}
		return result;
	}
	
	public static Bitmap decodeImageUri(Context context, Uri selectedImage,
			int requiredSize) throws FileNotFoundException {
		if (context != null && selectedImage != null && requiredSize > 0) {
			// Decode image size
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(context.getContentResolver()
					.openInputStream(selectedImage), null, o);
			
			// Find the correct scale value. It should be the power of 2.
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;
			while (true) {
				if (width_tmp / 2 < requiredSize
						|| height_tmp / 2 < requiredSize) {
					break;
				}
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}
			
			// Decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			return BitmapFactory.decodeStream(context.getContentResolver()
					.openInputStream(selectedImage), null, o2);
		}
		return null;
	}
	
	public static void finishOK(Activity activity) {
		finishCustom(activity, Activity.RESULT_OK);
	}
	
	public static void finishCancel(Activity activity) {
		finishCustom(activity, Activity.RESULT_CANCELED);
	}
	
	public static void finishCustom(Activity activity, int resultCode) {
		if (activity != null) {
			activity.setResult(resultCode);
			activity.finish();
		}
	}
}
