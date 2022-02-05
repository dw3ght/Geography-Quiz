package edu.uga.cs1302.quiz;

import java.io.IOException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Country1 //class to read country_continent.csv
{
    static ArrayList<String> country = new ArrayList<String>(); //creating list to store only countries
    static ArrayList<String> continent = new ArrayList<String>(); //creating list to store only continents
    static ArrayList<String> contAnswer = new ArrayList<String>(); //creating list to store both countries and continents in order

    {
            try {
                Reader in = new FileReader("/home/myid/dsc83098/cs1302/project5/src/main/java/edu/uga/cs1302/quiz/country_continent.csv"); //reading the country_continent.csv
                Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in); //creating iterable records to iterate through csv
                for (CSVRecord record : records) {
                    country.add(record.get(0)); //adding country data to the country list
                    if (!(continent.contains(record.get(1)))) {
                        continent.add(record.get(1)); //adding continent data to the continent list
                    }
                    //creating contAnswer list with both countries and continents
                    contAnswer.add(record.get(0));
                    contAnswer.add(record.get(1));
                }
            } catch (IOException e) { //catching IOException
                System.out.println(e);
            }
    }

    public ArrayList<String> getCountry() { //Country list getter
        return country;
    }

    public void setCountry(ArrayList<String> country) //Country list setter
    {
        this.country = country;
    }

    public ArrayList<String> getContinent() { //Continent list getter
        return continent;
    }

    public void setContinent(ArrayList<String> continent) //continent list setter
    {
        this.continent = continent;
    }

    public ArrayList<String> getContinentAnswer() { //contAnswer list getter
        return contAnswer;
    }

    public void setContinentAnswer(ArrayList<String> contAnswer) //contAnswer list setter
    {
        this.contAnswer = contAnswer;
    }
}

