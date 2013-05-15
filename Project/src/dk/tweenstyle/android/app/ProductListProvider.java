package dk.tweenstyle.android.app;

import java.util.List;

import dk.tweenstyle.android.app.model.Product;

public interface ProductListProvider {
	List<Product> fetchProductList();
}
