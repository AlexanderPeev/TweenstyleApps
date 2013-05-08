package dk.tweenstyle.android.app.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Here we creates the database, and its tables.
 * @author Christopher
 *
 */
public class MySQLiteHelper extends SQLiteOpenHelper{

	public static final String COLUMN_ID="_id";
	//PRODUCT TABLE
	public static final String TABLE_Products ="product",
			COLUMN_PRODUCT_ID="id",
			COLUMN_GENDER = "gender",
			COLUMN_VARIANTID="variantId",
			COLUMN_BACEPRICE = "bacePrice",
			COLUMN_NUMBER = "number",
			COLUMN_PRODUCTNAME = "name",
			COLUMN_ISACTIVE = "isActive",
			COLUMN_STOCK = "stock",
			COLUMN_SHORTDESCRIPTION = "shortDescription",
			COLUMN_LONGDESCRIPTION = "longDescription",
			COLUMN_TIMECREATED = "timeCreated",
			COLUMN_TIMEUPDATED = "timeUpdated",
			COLUMN_DEFAULTVARIANTCOMBINATION = "defaultVariantCombination",
			COLUMN_ISNEW= "isNew",
			COLUMN_MINAGE="minAge",
			COLUMN_MAXAGE="maxAge",
			COLUMN_MINSHOESIZE="minShoeSize",
			COLUMN_MAXSHOESIZE="maxShoeSize",
			COLUMN_WEBSITE="website",
			COLUMN_LOGO="logo",
			COLUMN_DESCRIPTION="description",
			COLUMN_PRODUCT_DISCOUNT_ID ="_productdiscountID",
			COLUMN_PRODUCT_GROUP_ID = "_productgroupID",
			COLUMN_PRODUCT_PRODUCT_ID="_productproductID";
	//DISCOUNT TABLE
	public static final String TABLE_Discount = "discount",
			COLUMN_DISCOUNT_ID ="id",
			COLUMN_TYPE = "type",
			COLUMN_DISCOUNTNAME = "name",
			COLUMN_PRICEFIXED = "priceFixed",
			COLUMN_PRICEPERCENTAGE = "pricePercentage",
			COLUMN_DISCOUNT_PRODUCT_ID="discountproductID";
	//GROUP TABLE
	public static final String TABLE_GROUP =" group",
			COLUMN_GROUP_ID ="id",
			COLUMN_GROUPNAME ="name",
			COLUMN_GROUP_GRPUP_ID="groupgroupID",
			COLUMN_GROUP_PRODUCT_ID="groupproductID",
			COLUMN_GROUP_DISCOUNT_ID="groupdiscountID";
	//SETTINGS TABLE
	public static final String TABLE_SETTINGS ="settings",
			COLUMN_SETTINGS_ID ="id",
			COLUMN_STRINGS ="strings";


	private static final String DATABASE_NAME = "tweenstyle.db";

	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATEPRODUCT = "create table "
			+ TABLE_Products + "(" 
			+ COLUMN_ID +" integer primary key autoincrement, "
			+ COLUMN_PRODUCT_ID +" text not null, "
			+ COLUMN_GENDER +" text not null, " 
			+ COLUMN_VARIANTID +" integer, "
			+ COLUMN_BACEPRICE +" integer, "
			+ COLUMN_NUMBER + " integer, "
			+ COLUMN_PRODUCTNAME +" text not null, "
			+ COLUMN_ISACTIVE + " boolean not null, "
			+ COLUMN_STOCK + " text not null, "
			+ COLUMN_SHORTDESCRIPTION +" text not null, "
			+ COLUMN_LONGDESCRIPTION +" text not null, "
			+ COLUMN_TIMECREATED + " text not null, "
			+ COLUMN_TIMEUPDATED + " text not null, "
			+ COLUMN_DEFAULTVARIANTCOMBINATION + " text not null, "
			+ COLUMN_ISNEW + " boolean not null, "
			+ COLUMN_MINAGE + " integer, "
			+ COLUMN_MAXAGE + " integer, "
			+ COLUMN_MINSHOESIZE +" integer, "
			+ COLUMN_MAXSHOESIZE +" integer, "
			+ COLUMN_WEBSITE + " text not null, "
			+ COLUMN_LOGO + " text not null," 
			+ COLUMN_DESCRIPTION +" text not null, "
			+ COLUMN_PRODUCT_DISCOUNT_ID + " integer, "
			+ COLUMN_PRODUCT_GROUP_ID +" integer, "
			+ COLUMN_PRODUCT_PRODUCT_ID +" integer );";

	private static final String DATABASE_CREATEDISCOUNT = "create table"
			+ TABLE_Discount + "("
			+ COLUMN_ID + " integer primary key autoincrement, "
			+ COLUMN_DISCOUNT_ID +" text not null, "
			+ COLUMN_TYPE +" text not null, "
			+ COLUMN_DISCOUNTNAME +" text not null, "
			+ COLUMN_PRICEFIXED + " integer, "
			+ COLUMN_PRICEPERCENTAGE + " integer, "
			+ COLUMN_DISCOUNT_PRODUCT_ID +" integer );";

	private static final String DATABASE_CREATEGROUP="create table"
			+ TABLE_GROUP +"("
			+ COLUMN_ID +" integer primary key autoincrement, "
			+ COLUMN_GROUP_GRPUP_ID +" text not null, "
			+ COLUMN_GROUPNAME + " text not null, "
			+ COLUMN_GROUP_GRPUP_ID + " integer, "
			+ COLUMN_GROUP_PRODUCT_ID + " integer, "
			+ COLUMN_GROUP_DISCOUNT_ID+ " integer );";

	private static final String DATABASE_CREATESETTNGS ="create table"
			+ TABLE_SETTINGS +"("
			+ COLUMN_ID +" integer primary key autoincrement, "
			+ COLUMN_SETTINGS_ID+ " text not null, "
			+ COLUMN_STRINGS +" text not null );";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATEPRODUCT);
		database.execSQL(DATABASE_CREATEDISCOUNT);
		database.execSQL(DATABASE_CREATEGROUP);
		database.execSQL(DATABASE_CREATESETTNGS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_Products);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_Discount);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUP);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTINGS);
		onCreate(db);
	}

}
