package org.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static Logger loggerInstance = null;
    private Logger(){};
    public static Logger getInstance(){
        if(loggerInstance == null){
            loggerInstance = new Logger();
        }
        return loggerInstance;
    }

    private LogLevel logLevel;
    private String dateFormat = "dd-mm-yyyy hh:mm:ss";
    private DestinationInterface destination;

    public Logger setConfiguration(String logLevel, String dateFormat, String targetType, String targetConfigString){
        this.logLevel = LogLevel.valueOf(logLevel);
        this.dateFormat = dateFormat;
        JsonObject json = new JsonParser().parse(targetConfigString).getAsJsonObject();
        switch(targetType){
            case "db":
                this.destination = new DB(json.get("host").getAsString(), json.get("port").getAsInt(), json.get("connector").getAsString());
                break;
            case "url":
                this.destination = new RestApi(json.get("host").getAsString(), json.get("port").getAsInt(), json.get("api").getAsString());
                break;
            case "file":
                this.destination = new File(json.get("filePath").getAsString());
                break;
        }

        return this;
    }

    private String constructMessageString(final String message, final String appName){
        DateFormat dateFormat = new SimpleDateFormat(this.dateFormat);
        String logDate = dateFormat.format(new Date());
        String logString = logDate + " " + message + " "+logLevel+" "+appName;
        return logString;
    }

    public void info(String message, String appName){
        if(logLevel.equals(LogLevel.DEBUG) || logLevel.equals(LogLevel.INFO)){
            final String messageString = constructMessageString(message, appName);
            destination.print(messageString);
        }
    }
    public void error(String message, String appName){
        final String messageString = constructMessageString(message, appName);
        destination.print(messageString);
    }

    public void warn(String message, String appName){
        if(logLevel.equals(LogLevel.DEBUG) || logLevel.equals(LogLevel.INFO) || logLevel.equals(LogLevel.WARN)){
            final String messageString = constructMessageString(message, appName);
            destination.print(messageString);
        }
    }
}
