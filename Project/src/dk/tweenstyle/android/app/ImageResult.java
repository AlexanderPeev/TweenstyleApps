package dk.tweenstyle.android.app;

import android.graphics.Bitmap;
import android.net.Uri;

public class ImageResult {
	private Uri uri;
	private Bitmap bitmap;
	
	public ImageResult() {
	}
	
	public ImageResult(Uri uri) {
		this.uri = uri;
	}
	
	public ImageResult(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	public ImageResult(Bitmap bitmap, Uri uri) {
		this.bitmap = bitmap;
		this.uri = uri;
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	public Uri getUri() {
		return uri;
	}
	
	public void setUri(Uri uri) {
		this.uri = uri;
	}
}
