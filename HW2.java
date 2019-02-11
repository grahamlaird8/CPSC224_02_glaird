package hw2;

import javax.swing.JOptionPane;
import java.lang.String;
import java.util.Random;

public class HW2 {

    
    public static void main(String[] args) {
       	String userChoice;
		String correctWord;
		private char[] guesses;
		
		do{
			userChoice = menu();
		} while(!(userChoice.equals("c")));
    }
	
	public static String menu()
	{
		return JOptionPane.showInputDialog("Please enter your choice:\na) Play game from a randomly chosen word in a list\nb) Play game from a word entered by another user\nc) Exit Game");
	}
	
	public static String getWordFromList()
	{
		private String[] wordList = {"Gonzaga", "Hachimura", "Bulldogs", "Perkins", "Crandall", "Hemmingson", "Computer", "Programming", "Basketball", "Soccer", "Videogame", "Engineer", "Population", "Approach", "Language"};
		Random rand = new Random();
		return wordList(rand.nextInt(15));
	}
	
	public static String getWordFromUser()
	{
		//Finn
	}
	
	public static char getLetterFromUser()
	{
		//Finn
	}
	
	public static void printWordInProgress(String& inProgressWord, char guess, int sizeOfWord)
	{
		//Graham
	}
	
	public static boolean checkGuess(String word, char guess, int sizeOfWord)
	{
		//Graham
	}
    
	public static void updateGuessList(char[] guesses, char guess)
	{
		//Graham
	}
	
	public static void printBody(int numWrongGuesses)
	{
		//Finn
	}
	
	public static boolean gameOver(int numWrongGuesses, String wordInProgress, String correctWord)
	{
		//Graham
	}
	
	
	
}