package com.niuxin.util;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public DBHelper(Context context) {
		super(context, Constants.DBNAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE table IF NOT EXISTS user"
				+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT, id TEXT, userName TEXT, img TEXT, isOnline TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("ALTER TABLE user ADD COLUMN other TEXT");
	}

}
