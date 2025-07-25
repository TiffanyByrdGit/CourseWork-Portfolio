//*************************************************************
//
//  Developer:    Tiffany Pham
//
//  Program #:    Two
//
//  File Name:    WorldSeries.java
//
//  Course:       ITSE 2317 Java Programming (Intermediate)
//
//  Due Date:     <Due Date>
//
//  Instructor:   Prof. Fred Kumi 
//
//  Chapter:      16
//
//  Description:  Class file that reads a text file with team names. 
//                Hash maps are created to connect key values: year -> team and team -> wins 
//                Checks for two years not played (1904 and 1994)
//                Displays error or results of the team that won and how many times they've won.
//
//***************************************************************

import java.util.HashMap;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

    //***************************************************************
    //
    //  Class:        WorldSeries
    // 
    //  Description:  Read Program2.java. Creates two hash maps to connect 
    //                year -> team and team -> wins.
    //
    //  Parameters:   N/A
    //
    //  Returns:      Error if file is not found. 
    //
    //**************************************************************
public class WorldSeries {
    
    //instance variables 
    //map year to team
    //map team to wins
    private HashMap<Integer, String> yearToTeam;
    private HashMap<String, Integer> teamToWins;
    
    //***************************************************************
    //
    //  Constructor:   WorldSeries
    // 
    //  Description:  Creates hash maps for year -> team and team -> year
    //                Creates read method for Program2.java
    //
    //  Parameters:   N/A
    //
    //  Returns:      N/A 
    //
    //**************************************************************
    public WorldSeries() {
        yearToTeam = new HashMap<>();
        teamToWins = new HashMap<>();
        readDataFromFile();
    }

    //***************************************************************
    //
    //  Method:       readDataFromFile()
    // 
    //  Description:  Accesses Program2.txt, checks for 1904 and 1994
    //                Read file and cleans data with trim()
    //                Maps year -> team | Counts the amount of each listed team
    //                Increments the year as txt file is read
    //                Close file and catches errors if file not found.
    //
    //  Parameters:   N/A
    //
    //  Returns:      N/A unless file is not found, then error message
    //
    //**************************************************************

    public void readDataFromFile() {
        try {
            File file = new File("Program2.txt");
            Scanner input = new Scanner(file);
    
            int year = 1903;
    
            while (year <= 2024) {
                String team;
    
                if (year == 1904 || year == 1994) {   //if 1904 or 1994 comes up, map to message
                    team = "World Series not played";
                } else {
                    team = input.nextLine().trim();  //else map to next line and clean data
                }
    
                yearToTeam.put(year, team);   //create key value pairs 
    
                if (!team.equals("World Series not played")) {       //count team wins except for 1904 and 1994
                    int wins = teamToWins.getOrDefault(team, 0);
                    teamToWins.put(team, wins + 1);
                }
    
                year++;  
            }
    
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Program2.txt not found!");
        }
    } 
    //***************************************************************
    //
    //  Method:       displayWinner
    // 
    //  Description:  If the years 1904 and 1994 are entered -> 
    //                error msg -> enter a valid year
    //                Retrieve team who won said year and how many times
    //                they won
    //                Output above info ^
    //
    //  Parameters:   int year
    //
    //  Returns:      Wrong entry, retry entry or
    //                winner and times won
    //
    //**************************************************************
 
    public void displayWinner(int year) {

        if (year == 1904 || year == 1994) {
            System.out.println("The World Series was not played in " + year + ".");
        }

        //check if year is in the map
        else if (!yearToTeam.containsKey(year)) {
            System.out.println("Invalid entry. Please enter year between 1903 and 2024.");
        }
        else {
        //get team and amount of wins
        String team = yearToTeam.get(year);
        int wins = teamToWins.getOrDefault(team, 0);

        //print results
        System.out.println("The winner of the World Series in " + year + " was " + team + ".");
        System.out.println(team + " won the World Series " + wins + " time(s).");
        }
    }
}