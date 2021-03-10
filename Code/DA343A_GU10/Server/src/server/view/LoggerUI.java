package server.view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LoggerUI {

    public void printRawLogFile(String filepath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String logRow = br.readLine();

            while (logRow != null) {
                System.out.println(logRow);
                logRow = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void readLogFile(String filepath) {
        String logMessage = null;
        String millis = null;
        ArrayList<String> logs = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String logRow = br.readLine();

            while (logRow != null) {
                if (logRow.contains("<millis>")) {
                    millis = logRow.substring(10, 23);
                }

                if (logRow.contains("<message>")) {
                    String message = logRow.substring(11);
                    String[] messageSplit = message.split("<");
                    logMessage = millis + "," + messageSplit[0];

                    logs.add(logMessage);
                }

                logRow = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Date in format YYYY/MM/DD-HH:MM
    public void readLogFile(String filepath, String inputStartDate, String inputEndDate) {
        String logMessage = null;
        String millis = null;
        ArrayList<String> logs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String logRow = br.readLine();

            while (logRow != null) {
                if (logRow.contains("<millis>")) {
                    millis = logRow.substring(10, 23);
                }

                if (logRow.contains("<message>")) {
                    String message = logRow.substring(11);
                    String[] messageSplit = message.split("<");
                    logMessage = millis + "," + messageSplit[0];

                    logs.add(logMessage);
                }

                logRow = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        long startDateMillis = 0;
        long endDateMillis = 0;
        Date startDate = null;
        Date endDate = null;

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd-HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd-HH:mm");
        try {
            startDate = sdf1.parse(inputStartDate);
            startDateMillis = startDate.getTime();

            endDate = sdf2.parse(inputEndDate);
            endDateMillis = endDate.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }


        for (String event : logs) {
            String[] eventArr = event.split(",");
            Date eventDate = new Date(Long.parseLong(eventArr[0]));

            if (eventDate.after(startDate) && eventDate.before(endDate)) {
                System.out.println(eventDate + " - " + eventArr[1]);
            }
        }
    }



    public static void main(String[] args) {
        LoggerUI loggerUI = new LoggerUI();
        loggerUI.readLogFile("Code/DA343A_GU10/files/TrafficLog.log", "2021/03/09-00:00", "2021/03/09-23:00");

    }
}
