package com.example.user_pc.project_trtpo.SupportClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpResUtility {
    private String ScheduleUrl;
    private File SchedulePath;
    public HttpResUtility(File directory)
    {
        ScheduleUrl="https://journal.bsuir.by/api/v1/studentGroup/schedule?studentGroup=";
        SchedulePath=directory;
    }

    public String getScheduleUrl() {
        return ScheduleUrl;
    }

    public int getSchedulesDowload(String NumGroup) {

        URL url = null;
        try {
            url = new URL(ScheduleUrl+NumGroup);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection httpConn = null;
        try {
            httpConn = (HttpURLConnection)url.openConnection();
            InputStream inputScheduleStream = httpConn.getInputStream();
            File filepath = File.createTempFile("schedule"+NumGroup, ".json", SchedulePath);
            FileOutputStream fileWriter = new FileOutputStream(filepath);
            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = inputScheduleStream.read(buffer)) > 0) {
                fileWriter.write(buffer, 0, len1);
            }
            SchedulePath=filepath;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            httpConn.disconnect();
        }
    return Dowload_Ok;
    }
        public File getSchedulePath() {
        return SchedulePath;
    }
    public static final int Dowload_Ok=202;
    public static final int Dowload_ERRROR_NUMGROUP=228;
}
