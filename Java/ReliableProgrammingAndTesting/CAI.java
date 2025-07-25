//***************************************************************
//  Developer:    Tiffany Pham
//
//  Program #:    Four
//
//  File Name:    CAI.java
//
//  Course:       ITSE 2317 Intermediate Java Programming
//
///  Due Date:     7/20/25
///
//  Instructor:   Fred Kumi
//
//  Chapter:      8 & 9
//
//  Description:  Manages question flow: question loop, user
//                input, level transitions, tracks scores, and
//                summary.
//
//***************************************************************

import java.util.Scanner;
import java.util.Random;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

//***************************************************************
//  Class:       CAI
//
//  Description: Manges questions loops, tracks results, and 
//               generates final report. 
//
//  Parameters: N/A
//
//  Returns:    N/A
//***************************************************************

public class CAI {
    private Scanner input;
    private GenerateQuestions generator;
    private PrintWriter logWriter;

    //instance variables
    private int currentLevel;
    private int correctFirstTry;
    private int  totalQuestions;

    //index levels
    private int[] correctPerLevel = new int[3];
    private int [] totalPerLevel = new int [3];
    //***************************************************************
    //
    //  Method:       developerInfo
    // 
    //  Description:  The developer information method of the program
    //
    //  Parameters:   None
    //
    //  Returns:      N/A 
    //
    //**************************************************************
    public void developerInfo()
    {
        System.out.println("Name:     Tiffany Pham");
        System.out.println("Course:   COSC 4301 Modern Programming");
        System.out.println("Program:  Four");
        System.out.println("Due Date: 7/20/2025\n");
    } // End of developerInfo

    //***************************************************************
    //
    //  Method:       start()
    // 
    //  Description:  Entry point of CAI interaction. 
    //
    //  Parameters:   None
    //
    //  Returns:      N/A 
    //
    //**************************************************************

    public void start() {
        input = new Scanner(System.in);
        generator = new GenerateQuestions();
        openLog();
    
        currentLevel = 1;
    
        while (currentLevel != -1) {
            runLevel(currentLevel);  // keeps running new levels
        }
    
        printFinalResults();
        closeLog();
    }

    //***************************************************************
    //
    //  Method:       openLog()
    // 
    //  Description:  Opens log file.
    //
    //  Parameters:   None
    //
    //  Returns:      N/A 
    //
    //**************************************************************
    private void openLog() {
        try {
            logWriter = new PrintWriter(new FileWriter("log.txt", true));
        } catch (IOException e) {
            System.out.println("Error opening log file.");
        }
    }

    //***************************************************************
    //
    //  Method:       log()
    // 
    //  Description:  Writes a message to the log.
    //
    //  Parameters:   String message
    //
    //  Returns:      N/A 
    //
    //**************************************************************
    private void log(String message) {
        if (logWriter != null) {
            logWriter.println(message);
            logWriter.flush();
        }
    }

    //***************************************************************
    //
    //  Method:       closeLog()
    // 
    //  Description:  Closes the log file.
    //
    //  Parameters:   None
    //
    //  Returns:      N/A 
    //
    //**************************************************************
    private void closeLog() {
        if (logWriter != null) {
            logWriter.close();
        }
    }

    //***************************************************************
    //
    //  Method:       runLevel()
    // 
    //  Description:  Handles questions and difficulty levels.  
    //
    //  Parameters:   int level
    //
    //  Returns:      N/A 
    //
    //**************************************************************
    public void runLevel(int level) {
        correctFirstTry = 0;
        boolean exitLevel = false;

        while (!exitLevel) {
            askQuestion();

            if (correctFirstTry >= 5) {
                int choice = handleLevel();

                if (choice == 1) {
                    correctFirstTry = 0; 

                } else if (choice == 2 && level < 3) {
                    currentLevel = level + 1;
                    exitLevel = true;
                } else if (choice == 3 || (level == 3 && choice == 2)) {
                    currentLevel = -1;
                    exitLevel = true;
                }
            }
        }
    }

    //***************************************************************
    //
    //  Method:       askQuestion()
    // 
    //  Description:  Generates new question and prompts the user. 
    //
    //  Parameters:   N/A
    //
    //  Returns:      N/A 
    //
    //**************************************************************
    private void askQuestion() {
        generator.generateQuestions(currentLevel);
        String question = generator.getCurrentQuestion();
        int correctAnswer = generator.getCorrectAnswer();

        boolean firstAttempt = true;
        boolean answeredCorrectly = false;

        while (!answeredCorrectly) {
            System.out.println("Evaluate: " + question);
            log("Question: " + question);

            int userAnswer = -1;
            try {
                System.out.println("Your answer: ");
                userAnswer = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter an integer.");
                log("Invalid entry.");
                continue;
            }

            if (userAnswer == correctAnswer) {
                provideFeedback(true);
                log("Student answered correctly: " + userAnswer);
                answeredCorrectly = true;

                if (firstAttempt) {
                    correctFirstTry++;
                }

                correctPerLevel[currentLevel - 1]++;
            } else {
                provideFeedback(false);
                log("Student answered incorrectly: " + userAnswer);
                firstAttempt = false;
            }

            totalQuestions++;
            totalPerLevel[currentLevel - 1]++;
        }
    }

    //***************************************************************
    //
    //  Method:       provideFeedback()
    // 
    //  Description:  Displays feedback based on correctness.
    //
    //  Parameters:   boolean correct
    //
    //  Returns:      isCorrect: True or False 
    //
    //**************************************************************
    private void provideFeedback(boolean correct) {
        String[] positiveFeedback = {
            "Excellent!",
            "Very good!",
            "Nice work!",
            "Way to go!",
            "Keep up the good work!"
        };

        String[] negativeFeedback = {
            "That is incorrect!",
            "No. Please try again!",
            "Wrong. Try once more!",
            "No. Don't give up!",
            "Incorrect. Keep trying!"
        };

        Random rand = new Random();
        String message;

        if (correct) {
            message = positiveFeedback[rand.nextInt(positiveFeedback.length)];
        } else {
            message = negativeFeedback[rand.nextInt(negativeFeedback.length)];
        }

        System.out.println(message);
        log("Feedback: " + message);
    }

    //***************************************************************
    //
    //  Method:       handleLevel()
    // 
    //  Description:  User can stay, move up level, or exit.
    //
    //  Parameters:   N/A
    //
    //  Returns:      User choice. 
    //
    //**************************************************************
    private int handleLevel() {
        int choice = 0;

        while (choice < 1 || choice > 3) {
            System.out.println("\nYou've answered 5 questions correctly on the first try!");
            System.out.println("What would you like to do next?");
            System.out.println("1. Stay at current level");
            if (currentLevel < 3) {
                System.out.println("2. Try a more difficult level");
            } else {
                System.out.println("2. Stay at current level");
            }
            System.out.println("3. Exit the program");

            log("Prompting level decision at level " + currentLevel);

            try {
                System.out.print("Enter your choice (1-3): ");
                choice = Integer.parseInt(input.nextLine());

                if (choice == 2 && currentLevel == 3) {
                    choice = 3;
                }

                if (choice < 1 || choice > 3) {
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                    log("Invalid level selection input: " + choice);
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (1–3).");
                log("Invalid input (non-integer) at level prompt.");
            }
        }

        log("Student selected choice: " + choice);
        return choice;
    }

    //***************************************************************
    //
    //  Method:       printFinalResults()
    // 
    //  Description:  Calculates and displays user summary.
    //
    //  Parameters:   N/A
    //
    //  Returns:      Final results.
    //
    //**************************************************************
    private void printFinalResults() {
        System.out.println("\n--- Final Performance Summary ---");
        log("\n--- Final Performance Summary ---");

        String[] levelNames = {"Basic", "Intermediate", "Advanced"};

        for (int i = 0; i < 3; i++) {
            int total = totalPerLevel[i];
            int correct = correctPerLevel[i];

            if (total > 0) {
                double percent = (correct * 100.0) / total;

                String summary = levelNames[i] + " Level: " + correct + "/" + total + 
                                 " correct (" + String.format("%.2f", percent) + "%)";
                System.out.println(summary);
                log(summary);

                if (i == 0 && percent < 80.0) {
                    String warning = "Please ask your teacher for extra help.";
                    System.out.println(warning);
                    log(warning);
                }
            } else {
                String skipped = levelNames[i] + " Level: No questions attempted.";
                System.out.println(skipped);
                log(skipped);
            }
        }

        String overall = "Total Questions Answered: " + totalQuestions;
        System.out.println(overall);
        log(overall);
    }
}
