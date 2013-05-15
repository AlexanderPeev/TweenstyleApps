package dk.tweenstyle.android.app;

public interface ViewTransferDelegate<T> {
	void onViewTransferRequest(Object source, T target); 
}
