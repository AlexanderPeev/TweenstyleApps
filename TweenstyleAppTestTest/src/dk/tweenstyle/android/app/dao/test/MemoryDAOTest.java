package dk.tweenstyle.android.app.dao.test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import dk.tweenstyle.android.app.dao.MemoryDAO;
import dk.tweenstyle.android.app.model.Discount;
import dk.tweenstyle.android.app.model.Group;
import dk.tweenstyle.android.app.model.Product;

public class MemoryDAOTest extends TestCase {
	
	// @BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	// @AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	// @Before
	public void setUp() throws Exception {
	}
	
	// @After
	public void tearDown() throws Exception {
	}
	
	// @Test
	public void test() {
		MemoryDAO dao = MemoryDAO.getInstance();
		
		Assert.assertNotNull(dao);
		
		Product product1 = new Product();
		product1.setId("testProduct1");
		product1.setName("test product 1");
		
		Product product2 = new Product();
		product2.setId("testProduct2");
		product2.setName("test product 2");
		
		Product product3 = new Product();
		product3.setId("testProduct3");
		product3.setName("test product 3");

		Group group1 = new Group("test1", "test 1", new LinkedList<String>(), new LinkedList<Product>(), new LinkedList<Discount>());
		Group group2 = new Group("test2", "test 2", new LinkedList<String>(), new LinkedList<Product>(), new LinkedList<Discount>());
		Group group3 = new Group("test3", "test 3", new LinkedList<String>(), new LinkedList<Product>(), new LinkedList<Discount>());

		group1.addChild(group2);
		group1.addChild(group3);
		
		group1.addProduct(product1);

		group2.addChild(group1);
		group2.addChild(group2);
		
		group2.addProduct(product2);

		group3.addChild(group1);
		group3.addChild(group2);
		
		group3.addProduct(product3);
		
		group3.addProduct(product1);
		
		dao.addGroup(group1);
		dao.addGroup(group2);
		dao.addGroup(group3);
		
		dao.addProduct(product1);
		dao.addProduct(product2);
		dao.addProduct(product3);
		
		List<Product> products = dao.getFlatProductList(group1);
		
		Assert.assertNotNull(products);
		
		Assert.assertEquals(3, products.size());
		
		HashSet<String> ids = new HashSet<String>();
		ids.add(product1.getId());
		ids.add(product2.getId());
		ids.add(product3.getId());
		
		for(Product p : products){
			Assert.assertNotNull(p);
			Assert.assertTrue(ids.contains(p.getId()));
			ids.remove(p.getId());
		}
		
	}
	
}