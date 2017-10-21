package study.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "todo.db";
    static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "todo";
    protected SQLiteDatabase db;

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME + "(" +
                        " _id INTEGER PRIMARY KEY NOT NULL, " +
                        " title TEXT NOT NULL, " +
                        " deadLine INTEGER NOT NULL " +
                ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
        onCreate(db);
    }

}
