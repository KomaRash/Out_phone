package com.example.user_pc.project_trtpo.SupportClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.user_pc.project_trtpo.Schedules.Employee;
import com.example.user_pc.project_trtpo.Schedules.Schedule;
import com.example.user_pc.project_trtpo.StartActivity;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class JsonParserSchedule {

    public static  void getSchedule(File file,Context context)
    {
        SqlHelper sqlHelper=new SqlHelper(context);
        SQLiteDatabase database=sqlHelper.getWritableDatabase();

        JSONParser parser = new JSONParser();
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(file.getAbsolutePath()));

            JSONArray Schedules = (JSONArray) object.get("schedules");
             for(Object obj: Schedules)
            {
                JSONObject WeekDay =(JSONObject)obj;
                String Day= (String) WeekDay.get("weekDay");
                JSONArray schedule=(JSONArray)WeekDay.get("schedule");
                for(Object iter_schedule: schedule) {

                    Schedule templ = new Schedule();
                    JSONObject subject = (JSONObject) iter_schedule;
                    Object[] ar = ((JSONArray) subject.get("auditory")).toArray();
                    if (ar.length > 0){
                        String buffer = (String) ((JSONArray) subject.get("auditory")).toArray()[0];
                        templ.setAuditory(buffer);
                    }
                    else {
                        templ.setAuditory("");
                    }
                    templ.setSubject((String) subject.get("subject"));
                    templ.setLessonTime((String) subject.get("lessonTime"));
                    templ.setLessonType((String) subject.get("lessonType"));
                    int weeks=1;
                    templ.setSubGroup(subject.get("numSubgroup").toString());
                    JSONArray JsonEmployee=(JSONArray) subject.get("employee");
                    Employee employee=new Employee();
                    if(JsonEmployee.toArray().length==0)
                    {
                        employee.setFio("");
                    }
                    else {
                        employee.setFio((String) ((JSONObject) JsonEmployee.get(0)).get("fio"));
                    }
                    templ.setEmployee(employee);
                    templ.setDay(Day);

                    Object[] ArrayObjWeek=((JSONArray) subject.get("weekNumber")).toArray();
                    for(Object ObjWeek:ArrayObjWeek) {
                        templ.setWeekNumber(ObjWeek.toString());
                        ContentValues contentValues=(templ).getContentValues();
                        SqlHelper.InsertINDB(database,SqlHelper.TableSchedule,contentValues);
                    }

                    }
            }
    database.close();
        } catch (IOException | ParseException e) {

            e.printStackTrace();
        }

    }

}
