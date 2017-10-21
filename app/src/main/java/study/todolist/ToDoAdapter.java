package study.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ToDoAdapter {

    static private DBOpenHelper helper;
    private SQLiteDatabase db;

    public ToDoAdapter(Context context) {
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }

    public List<ToDoItem> getList() {
        List<ToDoItem> items = new ArrayList<>();
        Cursor c = db.query(DBOpenHelper.TABLE_NAME, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                ToDoItem item = new ToDoItem();
                item.setId(c.getInt(c.getColumnIndex("_id")));
                item.setTitle(c.getString(c.getColumnIndex("title")));
                item.setDeadLine(new Date(c.getLong(c.getColumnIndex("deadLine"))));
                items.add(item);
            } while (c.moveToNext());
        }
        return items;
    }

    public void insert(String title, Date deadLine) {
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("deadLine", deadLine.getTime());
        db.insertOrThrow(DBOpenHelper.TABLE_NAME, null, values);
    }

    public  void remove(int id) {
        db.delete(DBOpenHelper.TABLE_NAME, "_id=?", new String[] { String.valueOf(id) });
    }

}
