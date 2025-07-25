//***************************************************************
//  Developer:    Tiffany Pham
//
//  Program #:    Four
//
//  File Name:    CAITest.java
//
//  Course:       ITSE 2317 Intermediate Java Programming
//
//  Due Date:     7/20/25
//
//  Instructor:   Fred Kumi
//
//  Chapter:      8 & 9
//
//  Description:  JUnit tests for GenerateQuestions class.
//
//***************************************************************

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CAITest {

    @Test
    public void testLevel1QuestionRange() {
        GenerateQuestions gen = new GenerateQuestions();
        gen.generateQuestions(1);
        gen.getCurrentQuestion();
        String question = gen.getCurrentQuestion();
        int answer = gen.getCorrectAnswer();

        assertTrue(answer >= 2 && answer <= 18, "Level 1 answer should be between 2 and 18");
        assertTrue(question.contains("+"), "Level 1 should use addition");
    }

    @Test
    public void testLevel2OperatorAndRange() {
        GenerateQuestions gen = new GenerateQuestions();
        gen.generateQuestions(2);
        String question = gen.getCurrentQuestion();

        assertTrue(question.contains("-") || question.contains("*"), "Level 2 uses - or *");
    }

    @Test
    public void testLevel3CorrectAnswerFormat() {
        GenerateQuestions gen = new GenerateQuestions();
        gen.generateQuestions(3);
        String question = gen.getCurrentQuestion();
        int answer = gen.getCorrectAnswer();

        assertNotNull(question);
        assertTrue(answer >= 0, "Level 3 answer should be valid non-negative integer");
    }
}
