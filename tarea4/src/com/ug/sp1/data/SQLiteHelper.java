package com.ug.sp1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper{

	public static String KEY_ID = "id";
	public static String KEY_NAME = "name";
	public static String KEY_ADDRESS = "address";
	public static String KEY_PHONE = "phone";
	public static String KEY_HOURS = "hoursOfOperation";
	public static String KEY_WEB = "website";
	public static String KEY_EMAIL = "email";
	public static String KEY_LAT = "lat";
	public static String KEY_LNG = "lng";
	public static String STORE = "STORE";
	
	public static String COMMENT = "COMMENT";
	public static String KEY_COMMENT = "comment";
	public static String DB = "DBSP1";
	public static int VERSION = 6;
	private String CreateStore = "CREATE TABLE "+ STORE +"("+ KEY_ID +" integer primary key autoincrement," +
														      KEY_NAME +" text, " +
					                                          KEY_ADDRESS + " text," +
					                                          KEY_PHONE + " text, "+
					                                          KEY_HOURS + " text," +
					                                          KEY_WEB + " text, " +
					                                          KEY_EMAIL + " text, " +
					                                          KEY_LAT + " text," +
					                                          KEY_LNG + " text)";
	
	private String CreateComentario = "CREATE TABLE "+ COMMENT +"("+ KEY_ID +" integer primary key autoincrement," + 
														             KEY_COMMENT +" text)";
	public SQLiteHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CreateStore);
		db.execSQL(CreateComentario);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
		// TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+STORE);
        db.execSQL("DROP TABLE IF EXISTS "+COMMENT);
        onCreate(db);
	}
	public void dropDB(){
		
	}

}
