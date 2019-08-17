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

    }

    public void update(View view) {

    }

    public void query(View view) {
        //String sql = "SELECT * FROM cust";
        Cursor cursor = database.query("cust", null, null,null,
                null,null,null);
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
