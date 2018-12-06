package com.example.user_pc.project_trtpo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user_pc.project_trtpo.SupportClass.DateHelper;
import com.example.user_pc.project_trtpo.SupportClass.SqlHelper;
import com.example.user_pc.project_trtpo.SupportClass.StatsActivity;

import java.util.Calendar;
import java.util.Date;

public class TableActivity extends AppCompatActivity {
    SimpleCursorAdapter cursorAdapter;
    ImageButton Yesterday;
    ListView listView;
    DateHelper dateHelper;
    TextView  dateView;
    TextView groupNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        groupNumber=(TextView)findViewById(R.id.NumGroupTableActivity);
        SQLiteOpenHelper sqlHelper = new SqlHelper(this);
        SQLiteDatabase database = sqlHelper.getReadableDatabase();
        Cursor cursor=database.query(SqlHelper.TableInfo,new String[]{"Numgroup"},null,null,null,null,null);

        cursor.moveToLast();
        String string=        cursor.getString(0);
        groupNumber.setText("гр."+String .valueOf(string));
        dateView=findViewById(R.id.DateTableActivity);
        dateHelper=new DateHelper();
        Yesterday=findViewById(R.id.Yesterday);
        Yesterday.setVisibility(View.GONE);
        listView=(ListView) findViewById(R.id.ListSubjectInfo);
        setSchedule();
        }



    public void GotoStatistics(View view) {
        Intent intent =new Intent(this,StatsActivity.class);
        startActivity(intent);

    }
    @Override
    public void onBackPressed() {
    }

    public void toPreviosDay(View view) {
        dateHelper.deincrementDate();
        if(dateHelper.isCuttentDate())
            Yesterday.setVisibility(View.GONE);
        setSchedule();
    }

    public void toNextDay(View view) {
        dateHelper.incrementDate();
        if(!dateHelper.isCuttentDate())
            Yesterday.setVisibility(View.VISIBLE);
       setSchedule();
    }
    private void setSchedule() {
        try {
            dateView.setText(String.valueOf(dateHelper.getDayWeek()));
            SqlHelper sqlHelper = new SqlHelper(this);
            String[] from = new String[]{SqlHelper.lessonTime, SqlHelper.Subject,
                    SqlHelper.Auditory, SqlHelper.lessonType, SqlHelper.SubGroup};
            int[] to = new int[]{R.id.time, R.id.subject, R.id.Auditory, R.id.LType, R.id.SubGroup};
            SQLiteDatabase sqLiteDatabase = sqlHelper.getReadableDatabase();
            String[] TableSearchParam = new String[]{"_id", SqlHelper.lessonTime, SqlHelper.Subject,
                    SqlHelper.Auditory, SqlHelper.lessonType, SqlHelper.SubGroup, SqlHelper.DayOftheWeek};
            if(dateHelper.getDayWeek()==null) {
                listView.setAdapter(null);
                return;
            }
            Cursor cursor = sqLiteDatabase.query(SqlHelper.TableSchedule, TableSearchParam, SqlHelper.DayOftheWeek + " = ? And " + SqlHelper.Week + "= ?", new String[]{dateHelper.getDayWeek(), String.valueOf(dateHelper.getNumWeek())}, null, null, null);
            cursorAdapter = new SimpleCursorAdapter(TableActivity.this, R.layout.activity_schedule__view, cursor, from, to, 0);

            listView.setAdapter(cursorAdapter);
        }
        catch (SQLiteException e)
        {
            Toast toast=Toast.makeText(this,"База данных недоступна",Toast.LENGTH_LONG);
        }
    }
}
