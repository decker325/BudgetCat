package com.managment.data;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Mychelle
 * Referenced http://www.vogella.com/tutorials/AndroidSQLite/article.html some code used from site example
 */
public class SQLDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BudgetCat";
    private static final String createTableComment = "Table to store budget information for users";
    private static final String SQLDatabaseStatement = "create table " + createTableComment + "(";
    private static final int DATABASE_VERSION = 1;

    public SQLDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SQLDatabaseStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLDatabase.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS ");
        onCreate(db);
    }

}
