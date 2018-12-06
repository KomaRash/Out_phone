package com.example.user_pc.project_trtpo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.user_pc.project_trtpo.SupportClass.HttpResUtility;
import com.example.user_pc.project_trtpo.SupportClass.InternetConnections;
import com.example.user_pc.project_trtpo.SupportClass.JsonParserSchedule;
import com.example.user_pc.project_trtpo.SupportClass.SqlHelper;

import java.io.File;

public class StartActivity extends AppCompatActivity {
    SqlHelper sqlHelper;
    SQLiteDatabase database;
    File directory;
    EditText NumGroup;
    EditText Name;
    RadioGroup SelectPets;
    EditText NamePets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sqlHelper=new SqlHelper(this);
        setContentView(R.layout.activity_start);
        NumGroup=(EditText)findViewById(R.id.NumberGroup);
        Name=(EditText)findViewById(R.id.Name);
        SelectPets=(RadioGroup)findViewById(R.id.Select_Pets);
        NamePets=(EditText)findViewById(R.id.Name_Pets);
        try {
            database= sqlHelper.getReadableDatabase();
            if(DatabaseUtils.queryNumEntries(database,SqlHelper.TableInfo)!=0)
            {
                Intent intent =new Intent(this,TableActivity.class);
                startActivity(intent);
            }
        }
        catch (SQLiteException e)
        {
            directory=this.getCacheDir();
              e.getStackTrace();
            new AlertIncorrectNumGroup(this);
        }
    }

    class newThread  extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {
            HttpResUtility httpResUtility=new HttpResUtility(directory);
           if(httpResUtility.getSchedulesDowload(NumGroup.getText().toString())==HttpResUtility.Dowload_Ok) {
               JsonParserSchedule.getSchedule(httpResUtility.getSchedulePath(),StartActivity.this);
            }
            try {
                this.wait(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent =new Intent(StartActivity.this,TableActivity.class);
            startActivity(intent);

        }
    }
        public void StartWorkWithTableActivity(View view) {
        while (!InternetConnections.getMobileDataEnabled(this))
        {
            new AlertIncorrectNumGroup(this);
        }
        new newThread().execute();


        ContentValues contentValues=new ContentValues();
        contentValues.put("Numgroup", NumGroup.getText().toString());
        contentValues.put("Name",Name.getText().toString());
        contentValues.put("NamePets",NamePets.getText().toString());
        contentValues.put("idPets ",SelectPets.getCheckedRadioButtonId());
        try {
            database=sqlHelper.getWritableDatabase();
            SqlHelper.InsertINDB(database, SqlHelper.TableInfo, contentValues);
            database.close();
        }
        catch (SQLiteException e)
        {e.getStackTrace();}




    }
}