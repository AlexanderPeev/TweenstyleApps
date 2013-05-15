package dk.tweenstyle.android.app.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Here we create the database, and its tables.
 * 
 * @author Christopher
 * 
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	
	public static final String COLUMN_ID = "_id";
	// PRODUCT TABLE
	public static final String TABLE_PRODUCTS = "products",
			COLUMN_PRODUCT_ID = "product_id",
			COLUMN_PRODUCT_GENDER = "product_gender",
			COLUMN_PRODUCT_VARIANT_ID = "product_variant_id",
			COLUMN_PRODUCT_BASE_PRICE = "product_base_price",
			COLUMN_PRODUCT_CURRENT_PRICE = "product_current_price",
			COLUMN_PRODUCT_NUMBER = "product_number",
			COLUMN_PRODUCT_NAME = "product_name",
			COLUMN_PRODUCT_IS_ACTIVE = "product_is_active",
			COLUMN_PRODUCT_STOCK = "product_stock",
			COLUMN_PRODUCT_SHORT_DESCRIPTION = "product_short_description",
			COLUMN_PRODUCT_LONG_DESCRIPTION = "product_long_description",
			COLUMN_PRODUCT_TIME_CREATED = "product_time_created",
			COLUMN_PRODUCT_TIME_UPDATED = "product_time_updated",
			COLUMN_PRODUCT_DEFAULT_VARIANT_COMBINATION = "product_default_variant_combination",
			COLUMN_PRODUCT_IS_NEW = "product_is_new",
			COLUMN_PRODUCT_MIN_AGE = "product_min_age",
			COLUMN_PRODUCT_MAX_AGE = "product_max_age",
			COLUMN_PRODUCT_MIN_SHOE_SIZE = "product_min_shoe_size",
			COLUMN_PRODUCT_MAX_SHOE_SIZE = "product_max_shoe_size",
			COLUMN_MANUFACTURER_ID = "manufacturer_id",
			COLUMN_MANUFACTURER_WEBSITE = "manufacturer_website",
			COLUMN_MANUFACTURER_LOGO = "manufacturer_logo",
			COLUMN_MANUFACTURER_DESCRIPTION = "manufacturer_description", 
			INDEX_PRODUCT_ID = "idx_product_id";
	// DISCOUNT TABLE
	public static final String TABLE_DISCOUNTS = "discounts",
			COLUMN_DISCOUNT_ID = "id", 
			COLUMN_DISCOUNT_TYPE = "type",
			COLUMN_DISCOUNT_NAME = "name", 
			COLUMN_DISCOUNT_PRICE_FIXED = "priceFixed",
			COLUMN_DISCOUNT_PRICE_PERCENTAGE = "pricePercentage",
			COLUMN_DISCOUNT_PRODUCT_ID = "discountproductID";
	// GROUP TABLE
	public static final String TABLE_GROUPS = "groups",
			COLUMN_GROUP_ID = "group_id", COLUMN_GROUP_NAME = "group_name";
	// GROUP EDGES TABLE
	public static final String TABLE_GROUP_EDGES = "group_edges",
			COLUMN_EDGE_GROUP_ID = "edge_group_id",
			COLUMN_EDGE_PARENT_GROUP_ID = "edge_parent_group_id";
	// SETTINGS TABLE
	public static final String TABLE_SETTINGS = "settings",
			COLUMN_SETTINGS_KEY = "settings_key",
			COLUMN_SETTINGS_VALUE = "settings_value";
	
	// PRODUCT GROUP RELATIONS TABLE
	public static final String TABLE_PG_RELATIONS = "pg_relations",
			COLUMN_PG_RELATION_PRODUCT_ID = "pg_relation_product_id",
			COLUMN_PG_RELATION_GROUP_ID = "pg_relation_group_id";
	
	// PRODUCT DISCOUNT RELATIONS TABLE
	public static final String TABLE_PD_RELATIONS = "pd_relations",
			COLUMN_PD_RELATION_PRODUCT_ID = "pg_relation_product_id",
			COLUMN_PD_RELATION_DISCOUNT_ID = "pg_relation_discount_id";
	
	private static final String DATABASE_NAME = "tweenstyle.db";
	
	// Database creation sql statement
	private static final String SQL_CREATE_TABLE_PRODUCT = "CREATE TABLE "
			+ TABLE_PRODUCTS + "(" 
			+ COLUMN_ID	+ " INTEGER PRIMARY KEY AUTOINCREMENT, " 
			+ COLUMN_PRODUCT_ID	+ " VARCHAR(120) NOT NULL, " 
			+ COLUMN_PRODUCT_VARIANT_ID	+ " VARCHAR(255) NOT NULL, " 
			+ COLUMN_PRODUCT_GENDER	+ " VARCHAR(10) NULL, " 
			+ COLUMN_PRODUCT_BASE_PRICE	+ " DECIMAL(10,4) NULL, " 
			+ COLUMN_PRODUCT_CURRENT_PRICE	+ " DECIMAL(10,4) NULL, " 
			+ COLUMN_PRODUCT_NUMBER	+ " VARCHAR(255) NULL, " 
			+ COLUMN_PRODUCT_NAME + " VARCHAR(255) NULL, " 
			+ COLUMN_PRODUCT_IS_ACTIVE + " TINYINT NULL, " 
			+ COLUMN_PRODUCT_STOCK + " INTEGER NULL, "
			+ COLUMN_PRODUCT_SHORT_DESCRIPTION + " TEXT NULL, "
			+ COLUMN_PRODUCT_LONG_DESCRIPTION + " TEXT NULL, "
			+ COLUMN_PRODUCT_TIME_CREATED + " DATETIME NULL, "
			+ COLUMN_PRODUCT_TIME_UPDATED + " DATETIME NULL, "
			+ COLUMN_PRODUCT_DEFAULT_VARIANT_COMBINATION + " VARCHAR(255) NULL, " 
			+ COLUMN_PRODUCT_IS_NEW	+ " TINYINT NULL, " 
			+ COLUMN_PRODUCT_MIN_AGE + " INTEGER NULL, "
			+ COLUMN_PRODUCT_MAX_AGE + " INTEGER NULL, "
			+ COLUMN_PRODUCT_MIN_SHOE_SIZE + " INTEGER NULL, "
			+ COLUMN_PRODUCT_MAX_SHOE_SIZE + " INTEGER NULL, "
			+ COLUMN_MANUFACTURER_ID + " VARCHAR(100) NULL, "
			+ COLUMN_MANUFACTURER_WEBSITE + " VARCHAR(255) NULL, "
			+ COLUMN_MANUFACTURER_LOGO + " VARCHAR(255) NULL,"
			+ COLUMN_MANUFACTURER_DESCRIPTION + " TEXT NULL);";
	
	private static final String SQL_CREATE_INDEX_PRODUCT_ID = "CREATE INDEX IF NOT EXISTS " + INDEX_PRODUCT_ID + " ON " + TABLE_PRODUCTS + "(" + COLUMN_PRODUCT_ID + ")";
	
	private static final String SQL_CREATE_TABLE_DISCOUNT = "CREATE TABLE"
			+ TABLE_DISCOUNTS + "(" 
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
			+ COLUMN_DISCOUNT_ID + " VARCHAR(100) NOT NULL UNIQUE, " 
			+ COLUMN_DISCOUNT_TYPE + " VARCHAR(100) NULL, "
			+ COLUMN_DISCOUNT_NAME + " VARCHAR(255) NULL, " 
			+ COLUMN_DISCOUNT_PRICE_FIXED	+ " DECIMAL(10,4) NULL, " 
			+ COLUMN_DISCOUNT_PRICE_PERCENTAGE + " DECIMAL(10,4) NULL);";
	
	private static final String SQL_CREATE_TABLE_GROUP = "CREATE TABLE "
			+ TABLE_GROUPS + " (" 
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
			+ COLUMN_GROUP_ID + " VARCHAR(255) NOT NULL UNIQUE, " 
			+ COLUMN_GROUP_NAME	+ " VARCHAR(255) NULL);";
	
	private static final String SQL_CREATE_TABLE_SETTNGS = "CREATE TABLE "
			+ TABLE_SETTINGS + " (" 
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
			+ COLUMN_SETTINGS_KEY + " VARCHAR(100) NOT NULL UNIQUE, " 
			+ COLUMN_SETTINGS_VALUE	+ " TEXT NULL );";
	
	private static final String SQL_CREATE_TABLE_GROUP_EDGES = "CREATE TABLE "
			+ TABLE_GROUP_EDGES + " (" 
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
			+ COLUMN_EDGE_GROUP_ID + " VARCHAR(255) NOT NULL, " 
			+ COLUMN_EDGE_PARENT_GROUP_ID	+ " VARCHAR(255) NOT NULL );";
	
	private static final String SQL_CREATE_TABLE_PG_RELATIONS = "CREATE TABLE "
			+ TABLE_PG_RELATIONS + " (" 
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
			+ COLUMN_PG_RELATION_GROUP_ID + " VARCHAR(255) NOT NULL, " 
			+ COLUMN_PG_RELATION_PRODUCT_ID	+ " VARCHAR(255) NOT NULL );";
	
	private static final String SQL_CREATE_TABLE_PD_RELATIONS = "CREATE TABLE "
			+ TABLE_PD_RELATIONS + " (" 
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
			+ COLUMN_PD_RELATION_DISCOUNT_ID + " VARCHAR(255) NOT NULL, " 
			+ COLUMN_PD_RELATION_PRODUCT_ID	+ " VARCHAR(255) NOT NULL );";
	
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(SQL_CREATE_TABLE_PRODUCT);
		database.execSQL(SQL_CREATE_INDEX_PRODUCT_ID);
		database.execSQL(SQL_CREATE_TABLE_DISCOUNT);
		database.execSQL(SQL_CREATE_TABLE_GROUP);
		database.execSQL(SQL_CREATE_TABLE_SETTNGS);
		database.execSQL(SQL_CREATE_TABLE_GROUP_EDGES);
		database.execSQL(SQL_CREATE_TABLE_PG_RELATIONS);
		database.execSQL(SQL_CREATE_TABLE_PD_RELATIONS);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		//db.execSQL("DROP INDEX IF EXISTS " + INDEX_PRODUCT_ID);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DISCOUNTS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTINGS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUP_EDGES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PG_RELATIONS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PD_RELATIONS);
		onCreate(db);
	}
	
}
