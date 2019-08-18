package tw.org.iii.appps.brad27;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase database;
    private MyDBHelper myDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDBHelper = new MyDBHelper(this, "brad", null, 1);
        database = myDBHelper.getReadableDatabase();

    }

    public void insert(View view) {
        //String sql = "INSERT INTO cust (cname,birthday) VALUES ('Brad', '1999-01-02')";
        //database.execSQL(sql);

        ContentValues values = new ContentValues();
        values.put("cname", "Eric");
        values.put("birthday", "1999-02-03");
        database.insert("cust",null,values);

        query(null);
    }

    public void delete(View view) {
        // delete from cust where id = 3 and cname = 'Eric'
        database.delete("cust", "id = ? and cname = ?",
                new String[]{"3","Eric"});
        query(null);
    }

    public void update(View view) {
        // update cust set cname='Brad', birthday='1999-02-03' where id=4;
        ContentValues values = new ContentValues();
        values.put("cname", "Brad");
        values.put("birthday", "1999-02-03");
        database.update("cust",values, "id=?", new String[]{"4"});
    }

    public void query(View view) {
        //String sql = "SELECT * FROM cust";
        Cursor cursor = database.query("cust", new String[]{"id","cname","birthday"},
                "cname like ?",new String[]{"B%"},
                null,null,
                null);
        if (cursor != null){
            while (cursor.moveToNext()){
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String cname = cursor.getString(cursor.getColumnIndex("cname"));
                String birthday = cursor.getString(cursor.getColumnIndex("birthday"));
                Log.v("brad", id + ":" + cname + ":" + birthday);
            }
        }
        Log.v("brad", "-----");
        //----------------
        cursor = database.rawQuery(
                "select * from cust where id in (select id from cust where cname=?)",
                new String[]{"Eric"});
        if (cursor != null){
            while (cursor.moveToNext()){
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String cname = cursor.getString(cursor.getColumnIndex("cname"));
                String birthday = cursor.getString(cursor.getColumnIndex("birthday"));
                Log.v("brad", id + ":" + cname + ":" + birthday);
            }
        }


    }
}
