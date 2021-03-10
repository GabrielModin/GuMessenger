package server.view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

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

        Date startDate = null;
        Date endDate = null;

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd-HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd-HH:mm");
        try {
            startDate = sdf1.parse(inputStartDate);
            endDate = sdf2.parse(inputEndDate);

        } catch (ParseException e) {
            listenForInput();
            return;
        }

        for (String event : logs) {
            String[] eventArr = event.split(",");
            Date eventDate = new Date(Long.parseLong(eventArr[0]));

            if (eventDate.after(startDate) && eventDate.before(endDate)) {
                System.out.println(eventDate + " - " + eventArr[1]);
            }
        }
    }

    public void listenForInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = null;
        String startDate = null;
        String endDate = null;
        int inputNumber = 0;

        while (true) {
            System.out.println("Enter 1 to show the log");
            userInput = scanner.nextLine();

            try {
                inputNumber = Integer.parseInt(userInput);

                if (inputNumber == 1) {
                    System.out.println("Enter the start date of the logs you want to see in the following format");
                    System.out.println("YYYY/MM/DD-HH:MM   -  Where HH is hour and MM is minute");
                    startDate = scanner.nextLine();
                    System.out.println("Enter the end date of the logs you want to see in the following format");
                    System.out.println("YYYY/MM/DD-HH:MM   -  Where HH is hour and MM is minute");
                    endDate = scanner.nextLine();

                    readLogFile("Code/DA343A_GU10/files/TrafficLog.log", startDate, endDate);

                } else {
                    System.out.println("Try again");
                }
            } catch (NumberFormatException e) {
                System.out.println("Try again");
            }

        }
    }



    public static void main(String[] args) {
        LoggerUI loggerUI = new LoggerUI();
        loggerUI.readLogFile("Code/DA343A_GU10/files/TrafficLog.log", "2021/03/09-00:00", "2021/03/09-23:00");

    }
}
