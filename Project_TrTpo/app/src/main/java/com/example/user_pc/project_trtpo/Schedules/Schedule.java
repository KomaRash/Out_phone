package com.example.user_pc.project_trtpo.Schedules;

import android.content.ContentValues;

import com.example.user_pc.project_trtpo.SupportClass.SqlHelper;

public class Schedule {
    private String subject;
    private String weekNumber;
    private String auditory ;
    private String lessonTime;
    private String lessonType;
    private Employee employee;
    private String Day;
    private String SubGroup;
    public Schedule()
    {

    };
    public  ContentValues getContentValues()
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(SqlHelper.Subject,this.getSubject());
        contentValues.put(SqlHelper.Auditory,this.getAuditory());
        contentValues.put(SqlHelper.lessonTime,this.getLessonTime());
        contentValues.put(SqlHelper.lessonType,this.getLessonType());
        contentValues.put(SqlHelper.DayOftheWeek,this.getDay());
        contentValues.put(SqlHelper.Fio,this.getEmployee().Fio());
        contentValues.put(SqlHelper.Week,this.getWeekNumber());
        contentValues.put(SqlHelper.SubGroup,this.getSubGroup());
        return contentValues;

    }
    public static ContentValues getContentValues(Schedule schedule){
    return  schedule.getContentValues();
    }
    public String getAuditory() {
        return auditory;
    }

    public void setAuditory(String auditory) {
        this.auditory = auditory;
    }

   
    public String getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
    }

    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

  

  

    public String getWeekNumber() {
        return weekNumber;
    }

    public String getSubGroup() {
        return SubGroup;
    }

    public void setSubGroup(String subGroup) {
        SubGroup = subGroup;
    }

    public void setWeekNumber(String weekNumber) {
        this.weekNumber = weekNumber;
    }
}
