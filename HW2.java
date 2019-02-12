/**
  *@file: HW2.java
  *		Program runs through a game of Hangman, allowing
  *		users to choose a word chosen by the program or
  *		a word entered by a second user. Allows user to 
  *		play as many games until the choose to exit.
  *
  *@due: 02/12/19 11:59 PM
  *@authors Graham Laird & Finnian Allen
  */

package hw2;

import javax.swing.JOptionPane;
import java.lang.String;
import java.util.Random;

public class HW2 {

    
    public static void main(String[] args) {
       	playHangman(); // the primary function for playing the game
    }
	
	/** Runs through the logic of the game of Hangman using other methods
	  *@param: none
	  *@return: none
	  *@usage : playHangman();
	  */
    public static void playHangman()
    {
        String userChoice = null; // stores the men choice for how the use wants to play or to exit
        String correctWord = null; // initialized to nothing currently, will be used later
		String userGuess = null; // stores the user guess
		String inProgressWord = null; // stores the word as the user is guessing it
		String[] guessList = new String[26]; // holds an array of strings as possible words to use
		int numWrongGuesses;
		boolean correctGuess; // stores if the guess matches a character in the word
		boolean gameOver; // stores a value as to if the game has finished
			
		userChoice = menu(); // assigns user choice to the return value of menu
                                     // in order to determine rhe user's selection
		
		while(!(userChoice.equals("c"))){ // will continue to loop until the user chooses to exit		
			numWrongGuesses = 0; // assigns the wrong guesses at 0 initially
			gameOver = false; // assigned to false to insure at least one loop
			
			if(userChoice.equals("a")) // if the user chooses a
				correctWord = getWordFromList(); // get a word from the list at random
			else if(userChoice.equals("b")) // if the user chooses b
				correctWord = getWordFromUser(); // get the word from the user
			inProgressWord = initializeWordInProgress(correctWord); // assign the word in progress to the return value
			printWordInProgress(inProgressWord); // print the word in progress initially to show how many letters the word has
			//printGuessList(guessList, numWrongGuesses);
			//printBody(numWrongGuesses); // call to print the hangman's body given wrong guesses
			
			while(!gameOver)
			{
				userGuess = getLetterFromUser();
				correctGuess = checkGuess(correctWord, userGuess);
				if(correctGuess) // if they guessed correctly
				{
					inProgressWord = updateWordInProgress(inProgressWord, correctWord, userGuess); // add the character to the wordin progress
				}
				else
				{
					numWrongGuesses++; // increment wrong guesses
					guessList = updateGuessList(guessList, userGuess, numWrongGuesses); // add the charaacter to the list of incorrect guesses
				}
				printWordInProgress(inProgressWord); // print the word in progress given what has been guessed
				printGuessList(guessList, numWrongGuesses); // print the list of current guesses
				printBody(numWrongGuesses); // orint hangman's body
				gameOver = gameOver(numWrongGuesses, inProgressWord, correctWord); // run function game over to end game if gameover is true
			}
			userChoice = menu(); // reprompt for menu
		}
    }

	/** shows the user 3 choices and returns the user's choice
	  *@param: none
	  *@return: users choice in form of a single char string
	  *@usage : String userChoice = menu();
	  */
    public static String menu()
    {
        String choice = JOptionPane.showInputDialog("Please enter your choice:\na) Play game from a randomly chosen word in a list\nb) Play game from a word entered by another user\nc) Exit Game"); // gets a user menu selection
		
		while(choice.isEmpty()) // as long as the input is empty display an invalid input prompt
		{
			choice = JOptionPane.showInputDialog("Please enter your choice:\na) Play game from a randomly chosen word in a list\nb) Play game from a word entered by another user\nc) Exit Game"); // gets a user menu selection
		}
		
		while(choice.charAt(0) != 'a' && choice.charAt(0) != 'b' && choice.charAt(0) != 'c' ) // invalid input loop/prompt if they enter two letters or a upper case letter
		{
			choice = JOptionPane.showInputDialog("Please enter your choice:\na) Play game from a randomly chosen word in a list\nb) Play game from a word entered by another user\nc) Exit Game"); // gets a user menu selection
		}
		
		return choice;
    }

	/** randomly selects a word from 15 possible words
	  *@param: none
	  *@return: returns one of 15 words for the user to play hangman with
	  *@usage : String correctWord = getWordFromList();
	  */
    public static String getWordFromList(){
		String[] wordList = {"gonzaga", "hachimura", "bulldogs", "perkins", "crandall", "hemmingson", "computer", "programming", "basketball", "soccer", "videogame", "engineer", "population", "approach", "language"}; // all possible non user made words in an array to be randomly selected
		Random rand = new Random(); // initialize random object
		return wordList[rand.nextInt(15)]; // gets a random word from the word list array
    }

	/** gets a word from user to play hangman with
	  *@param: none
	  *@return: returns a user entered word for the user to play hangman with
	  *@usage : String correctWord = getWordFromUser();
	  */
    public static String getWordFromUser()
	{
        String userWord;
        userWord = JOptionPane.showInputDialog("What word would you like the other user to play for?"); // prompt for and get a custom user word to guess
        return userWord; // return the custom word
	}

	/** gets the user's guess for a letter
	  *@param: none
	  *@return: returns a user entered letter that the user thinks is in the word
	  *@usage : String correctWord = getWordFromUser();
	  */
    public static String getLetterFromUser()
    {	
        String userGuess = JOptionPane.showInputDialog("Please enter the (lowercase) letter you would like to guess next"); // prompt for input
		
		while(userGuess.isEmpty()) // as long as the input is empty display an invalid input prompt
		{
			userGuess = JOptionPane.showInputDialog("Please enter the (lowercase) letter you would like to guess next.\nMake sure you enter a single, lowercase letter.");
		}
		
		while(Character.isUpperCase(userGuess.charAt(0)) || userGuess.length() > 1) // invalid input loop/prompt if they enter two letters or a upper case letter
		{
			userGuess = JOptionPane.showInputDialog("Please enter the (lowercase) letter you would like to guess next.\nMake sure you enter a single, lowercase letter.");
		}
		
		return userGuess;
    }

	/** updates the word in progress that the user can see with the user's correct guess
	  *@param: inProgressWord is the current word that's in progress that the user can see
	  *@param: word is the word that the user is trying to guess
	  *@param: guess is the user's letter guess
	  *@return: returns a the updated version of the word that the user can see
	  *@usage : String inProgressWord = updateWordInProgress(inProgressWord, word, guess);
	  */
    public static String updateWordInProgress(String inProgressWord, String word, String guess){
		String inProgressWordNew = "";
		for(int i = 0; i < word.length(); i++) // loop through the word
		{
			if(inProgressWord.charAt(i) == '_') // if the in progress word at that location is blank
			{
				if(word.charAt(i) == guess.charAt(0)) // and if the guess and actual word at that location are equal
					inProgressWordNew += guess; // append the guess to in progress word
				else
					inProgressWordNew += "_"; // otherwise append a blank
			}
			else
				inProgressWordNew += inProgressWord.charAt(i); // otherwise append the old word in progress to the new one at the location
        }
		return inProgressWordNew; // return the updated word in progress
    }
	
	/** initializes the version of the word that the user can see to the correct amount of blanks
	  *@param: word is the word that the user is trying to guess
	  *@return: returns the version of the word that the user can see, starting with the correct amount of blanks
	  *@usage : String inProgressWord = initializeWordInProgress(word);
	  */
	public static String initializeWordInProgress(String word)
    {
        String inProgressWord = "";
		for(int i = 0; i < word.length(); i++) // loop given the word length
		{
			inProgressWord += "_"; // place blanks
		}
		
		return inProgressWord; // return the blanks of the same length as the word
    }

	/** prints the version of the word the user can see in a readable format
	  *@param: inProgressWord is the current word that's in progress that the user can see
	  *@return: None
	  *@usage : printWordInProgress(inProgressWord);
	  */
    public static void printWordInProgress(String inProgressWord)
    {
      
		JOptionPane.showMessageDialog(null, ("Word in progress: \n " + inProgressWord.replaceAll("_", "_ ")));// show the word in progress, and add a space between all _ to make it more readable
    }
    
	/** checks if the users letter guess was correct
	  *@param: word is the word that the user is trying to guess
	  *@param: guess is the user's letter guess
	  *@return: true if the letter the user guessed is in the word
	  *@usage : boolean correct = checkGuess(word, guess);
	  */
    public static boolean checkGuess(String word, String guess)
    {
		boolean correctGuess = false;
		for(int i = 0; i < word.length(); i++) // check the entire word for the letter
		{
			if(word.charAt(i) == guess.charAt(0)) // if at position the letter is found
				correctGuess = true; // set to true
		}
		
		if(correctGuess)
			JOptionPane.showMessageDialog(null, ("Yes, " + guess + " is in the word!")); // print the success message
		else
			JOptionPane.showMessageDialog(null, ("No, " + guess + " is not in the word!")); // print the guess failure message
		return correctGuess;
    }

	/** updates the list of letters that the user has already guessed
	  *@param: numGuesses is the number of wrong guessed the user has made
	  *@param: guessList is the incorrect guesses the user has already made
	  *@param: guess is the user's letter guess
	  *@return: updated list of incorrect letters
	  *@usage : guessList = updateGuessList(guessList, guess, numGuesses)
	  */
    public static String[] updateGuessList(String[] guessList, String guess, int numGuesses)
    {
        guessList[numGuesses - 1] = guess; // places a guess in the list
		return guessList;
    }
	
	/** prints the letters that the user has incorrectly guessed
	  *@param: numGuesses is the number of wrong guessed the user has made
	  *@param: guessList is the incorrect guesses the user has already made
	  *@return: None
	  *@usage : printGuessList(guessList, numGuesses)
	  */
	public static void printGuessList(String[] guessList, int numGuesses)
    {
        String allGuesses = ""; // initialize all guesses
		allGuesses += guessList[0]; // add to the total guesses given a guess
		for(int i = 1; i < numGuesses; i++) // seperates the guesses in the list given how many guesses and prints the guesses
		{
			allGuesses = allGuesses + ", " + guessList[i];
		}
		
		if(numGuesses == 0) // if no guesses wrong
			JOptionPane.showMessageDialog(null, ("No incorrect guesses yet"));
		else
			JOptionPane.showMessageDialog(null, ("Incorrect Guesses: " + allGuesses));
    }

	/** prints the body of the hangman based on how many incorrect guesses the user has made
	  *@param: numWrongGuesses is the number of wrong guessed the user has made
	  *@return: None
	  *@usage : printBody(numWrongGuesses)
	  */
    public static void printBody(int numWrongGuesses)
	{
		
        switch (numWrongGuesses) // prints the bodt given incorrect guesses
        {
			case 0: JOptionPane.showMessageDialog(null, "No body drawn yet");
			break;
			
			case 1: JOptionPane.showMessageDialog(null, "Hangman:\n    O");
			break;

			case 2: JOptionPane.showMessageDialog(null, "Hangman:\n  O\n /");
			break;

			case 3: JOptionPane.showMessageDialog(null, "Hangman:\n  O\n / |");
			break;

			case 4: JOptionPane.showMessageDialog(null, "Hangman:\n  O\n / | \\");
			break;

			case 5: JOptionPane.showMessageDialog(null, "Hangman:\n  O\n / | \\\n /");
			break;
		
			case 6: JOptionPane.showMessageDialog(null, "Hangman:\n  O\n / | \\\n /  \\");
			break;
			
			default : JOptionPane.showMessageDialog(null, "No body parts gained");
		}

	}

	/** determines if the current game of hangman has finished
	  *@param: numWrongGuesses is the number of wrong guessed the user has made
	  *@param: wordInProgress is the version of the word the user has currently guessed
	  *@param: correctWord is the word the user is trying to guess
	  *@return: True if the game is over, else false
	  *@usage : boolean gameIsOver = gameOver(numWrongGuesses, wordInProgress, correctWord);
	  */
    public static boolean gameOver(int numWrongGuesses, String wordInProgress, String correctWord)
    {
        if(numWrongGuesses == 6)
		{
			JOptionPane.showMessageDialog(null, ("You guessed incorrectly 6 times, your hangman died!\nThe correct word was: " + correctWord));
			return true; // incorrect 6 times reutrns value true will end the game loop
		}
		else if(wordInProgress.equals(correctWord))
		{
			JOptionPane.showMessageDialog(null, "You guessed the word, congratulations!");
			return true; // enough correct guess will return to also end the game loop
		}
		else
		{
			return false; // no win or lose
		}
    }

    
	
}
