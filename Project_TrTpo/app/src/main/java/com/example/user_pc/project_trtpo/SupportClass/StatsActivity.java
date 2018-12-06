package com.example.user_pc.project_trtpo.SupportClass;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.user_pc.project_trtpo.R;

public class StatsActivity extends AppCompatActivity {
    TextView name;
    TextView numGroup;
    TextView namePets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_stats);
        name=(TextView)findViewById(R.id.InfoName);
        numGroup=(TextView)findViewById(R.id.InfoGroup);
        namePets=(TextView)findViewById(R.id.InfoPets);
        SqlHelper sqlHelper=new SqlHelper(this);
        try {
            SQLiteDatabase database=sqlHelper.getReadableDatabase();
            Cursor cursor=database.query(SqlHelper.TableInfo,new String[]{"Name","Numgroup","idPets","NamePets"},null,null,null,null,null);        super.onCreate(savedInstanceState);
            cursor.moveToLast();
            String string=cursor.getString(0);
            name.setText(string);
            int cursorGroup=cursor.getInt(1);
            numGroup.setText(String.valueOf(cursorGroup));
            string=cursor.getString(3);
            namePets.setText(string);

        }
        catch (SQLiteException e) {
        e.getStackTrace();
        }
    }
}
