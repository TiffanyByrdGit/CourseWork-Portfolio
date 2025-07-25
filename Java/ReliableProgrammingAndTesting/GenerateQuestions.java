//***************************************************************
//
//  Developer:    Tiffany Pham
//
//  Program #:    Four
//
//  File Name:    GenerateQuestions.java
//
//  Course:       ITSE 2317 Intermediate Java Programming
//
//  Instructor:   Fred Kumi
//
//  Description:  Generates math questions for different levels
//
//***************************************************************

import java.util.Random;

//***************************************************************
//
//  Class:        GenerateQuestions
// 
//  Description:  Generates questions.
//
//  Parameters:   N/A
//
//  Returns:      N/A 
//
//**************************************************************
public class GenerateQuestions {

    private String question;
    private int correctAnswer;

    //***************************************************************
    //
    //  Method:       generateQuestions()
    // 
    //  Description:  Generates questions with random integers. Includes
    //                three cases (levels).
    //
    //  Parameters:   int level 
    //
    //  Returns:      N/A 
    //
    //**************************************************************

    public void generateQuestions(int level) {
        Random rand = new Random();
        int num1 = rand.nextInt(9) + 1;  // 1 to 9
        int num2 = rand.nextInt(9) + 1;  // 1 to 9

        switch (level) {
            case 1:
                question = num1 + " + " + num2;
                correctAnswer = num1 + num2;
                break;
            case 2:
                int op = rand.nextInt(2); // 0 for -, 1 for *
                if (op == 0) {
                    question = num1 + " - " + num2;
                    correctAnswer = num1 - num2;
                } else {
                    question = num1 + " * " + num2;
                    correctAnswer = num1 * num2;
                }
                break;
            case 3:
                int a = rand.nextInt(9) + 1;
                int b = rand.nextInt(9) + 1;
                int c = rand.nextInt(9) + 1;
                question = a + " + " + b + " * " + c;
                correctAnswer = a + b * c;
                break;
            default:
                question = "Invalid level";
                correctAnswer = 0;
        }
    }

    //***************************************************************
    //
    //  Method:       getCurrentQuestion()
    // 
    //  Description:  Used by jUnit for testing
    //
    //  Parameters:   N/A
    //
    //  Returns:      question
    //
    //**************************************************************
    // Getter methods used for JUnit tests
    public String getCurrentQuestion() {
        return question;
    }

    //***************************************************************
    //
    //  Method:       getCorrectAnswer()
    // 
    //  Description:  Used by jUnit for testing
    //
    //  Parameters:   N/A
    //
    //  Returns:      correctAnswer
    //
    //**************************************************************

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
