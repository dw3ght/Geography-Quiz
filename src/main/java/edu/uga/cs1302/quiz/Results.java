package edu.uga.cs1302.quiz;

//importing classes
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class Results { //Creatign Results class

    public static String results_file_location = "/home/myid/dsc83098/cs1302/project5/src/main/java/edu/uga/cs1302/quiz/results.csv"; //creating a String with the csv file path
    public static LinkedList<String> resultList = new LinkedList<String>(); //creating a LinkedList to store csv info
    public static String timeStamp; //creating a string to store the current time and date
    public static int score; //creating in to keep up with score

    // Results Class with two fields
    // - timeStamp
    // - score
    public static class ResultsSetGet { //results setters and getters

        public String getTimeStamp() { //timeStamp getter
            return timeStamp;
        }

        public void setTimeStamp(String timeStamp1) { //timeStamp setter
            timeStamp = timeStamp1;
        }

        public int getScore() { //score getter
            return score;
        }

        public void setScore(int score1) { //score setter
            score = score1;
        }
    }

    // Save to file Utility
    public static void resultsWriteToFile(String myData, int score) { //method to write in the file
        File resultsFile = new File(results_file_location); //creating file
        if (!resultsFile.exists()) { //checking if the file doesnt exist
            try {
                File directory = new File(resultsFile.getParent()); //creating directory
                if (!directory.exists()) {
                    directory.mkdirs(); //making directory
                }
                resultsFile.createNewFile(); //initalizing
            } catch (IOException e) { //catch IOException
                log("Excepton Occured: " + e.toString());
            }
        }

        try {
            // Convenience class for writing character files
            FileWriter resultsWriter;
            resultsWriter = new FileWriter(resultsFile.getAbsoluteFile(), true);

            // Writes text to a character-output stream
            BufferedWriter bufferWriter = new BufferedWriter(resultsWriter);
            timeStamp = new SimpleDateFormat("MM/dd/yyyy     HH:mm:ss" ).format(Calendar.getInstance().getTime()); //creating a current timeStamp
            myData = timeStamp;
            bufferWriter.write("!" +myData  + "!Score: " + score + " out of 6" +"!\n"); //writing to csv using formatting and parameters
            bufferWriter.close(); //close

        } catch (IOException e) { //catch IOException
            log("error while saving Company data to file " + e.toString());
        }
    }

    // Read From File Utility
    public static void resultsReadFromFile() {
        File resultsFile = new File(results_file_location); //creating file
        if (!resultsFile.exists()) //checking if the file doesnt exist
            log("File doesn't exist"); //print doesnt exist statement

        try { //try

            try {
                Reader in = new FileReader( "/home/myid/dsc83098/cs1302/project5/src/main/java/edu/uga/cs1302/quiz/results.csv"); //reader for results.csv
                Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in); //creating an iterable records
                for (CSVRecord record : records) { //loop through thr file's data
                    resultList.add(record.get(0)); //adding data to the list
                }

            }
            catch( IOException e ) { //catch IOException
                System.out.println( e );
            }

        } catch (Exception e) { //catch IOException
            System.out.println( e );
        }
    }

    private static void log(String string) { //method to print
        System.out.println(string);
    }

}
