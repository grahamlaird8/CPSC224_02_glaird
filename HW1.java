/********************************
	Homework #1
	Due: 1/31/19
	Graham Laird and Finnian Allen
 ********************************/
package hw1;
import java.util.Random;
import java.util.*;
import java.lang.String;


public class HW1 {

    
    public static void main(String[] args) {

        int DICE_IN_PLAY = 5;
		int hand[];
		char playAgain = 'y';
		Scanner scnr = new Scanner(System.in);
		
		hand = new int[DICE_IN_PLAY];
		
		while(playAgain == 'y')
		{
			String keep = "nnnnn";
			int turn = 1;
			while(turn < 4 && keep != "yyyyy")
			{
				for(int dieNumber = 0; dieNumber < DICE_IN_PLAY; dieNumber++)
				{
					if(keep.charAt(dieNumber) != 'y')
					{
						hand[dieNumber] = rollDie();
					}
				}
				System.out.print("Your roll was: ");
				for(int dieNumber = 0; dieNumber < DICE_IN_PLAY; dieNumber++)
				{
					System.out.print(hand[dieNumber] + " ");
				}
				System.out.println();
				if(turn < 3)
				{
					System.out.print("enter dice to keep (y or n) ");
					keep = scnr.next();
				}
				turn++;
			}
			//start scoring
			//hand need to be sortedto check for straights
			
			sortArray(hand, DICE_IN_PLAY);
			System.out.print("Here is your sorted hand : ");
			for(int dieNumber = 0; dieNumber < DICE_IN_PLAY; dieNumber++)
			{
				System.out.print(hand[dieNumber] + " ");
			}
			System.out.println();
			//upper scorecard
			for(int dieValue = 1; dieValue <= 6; dieValue++)
			{
				int currentCount = 0;
				for(int diePosition = 0; diePosition < 5; diePosition++)
				{
					if(hand[diePosition] == dieValue)
					{
						currentCount++;
					}
				}
					System.out.print("Score " + (dieValue * currentCount) + " on the ");
					System.out.println(dieValue + " line");
			}
			//lower scorecard
			if(maxOfAKindFound(hand) >= 3)
			{
				System.out.print("Score " + totalAllDice(hand) + " on the ");
				System.out.println("3 of a Kind line");
			}
			else
			{
				System.out.println("Score 0 on the 3 of a Kind line");
			}
			
			if(maxOfAKindFound(hand) >= 4)
			{
				System.out.print("Score " + totalAllDice(hand) + " on the ");
				System.out.println("4 of a Kind line");
			}
			else
			{
				System.out.println("Score 0 on the 4 of a Kind line");
			}
			
			if(fullHouseFound(hand))
			{
				System.out.println("Score 25 on the Full House line");
			}
			else
			{
				System.out.println("Score 0 on the Full House line");
			}
			
			if(maxStraightFound(hand) >= 4)
			{
				System.out.println("Score 30 on the Small Straight line");
			}
			else
			{
				System.out.println("Score 0 on the Small Straight line");
			}
			
			if(maxStraightFound(hand) >= 5)
			{
				System.out.println("Score 40 on the Large Straight line");
			}
			else
			{
				System.out.println("Score 0 on the Large Straight line");
			}
			
			if(maxOfAKindFound(hand) >= 5)
			{
				System.out.println("Score of 50 on the Yahtzee line");
			}
			else
			{
				System.out.println("Score 0 on the Yahtzee line");
			}
		System.out.print("Score " + totalAllDice(hand) + " on the ");
		System.out.println("Chance line");
		System.out.print("\nEnter 'y' to play again: ");
		playAgain = scnr.next().charAt(0);
		}
	}
	
    
	
	public static int rollDie()
	{
		Random rand = new Random();
		return rand.nextInt(6) + 1;
	}
	
	public static int maxOfAKindFound(int hand[])
	{
		int maxCount = 0;
		int currentCount;
		for(int dieValue = 1; dieValue <= 6; dieValue++)
		{
			currentCount = 0;
			for(int diePosition = 0; diePosition < 5; diePosition++)
			{
				if(hand[diePosition] == dieValue)
				{
					currentCount++;
				}
			}
			if(currentCount > maxCount)
			{
				maxCount = currentCount;
			}
		}
		return maxCount;
	}
	
	public static int totalAllDice(int hand[])
	{
		int total = 0;
		for(int diePosition = 0; diePosition < 5; diePosition++)
		{
			total += hand[diePosition];
		}
		return total;
	}
	
	public static void sortArray(int array[], int size)
	{
		boolean swap;
		int temp;
		
		do{
			swap = false;
			for(int count = 0; count < (size - 1); count++)
			{
				if(array[count] > array[count + 1])
				{
					temp = array[count];
					array[count] = array[count + 1];
					array[count + 1] = temp;
					swap = true;
				}
			}
		} while(swap);
	}
	
	public static int maxStraightFound(int hand[])
	{
		int maxLength = 1;
		int curLength = 1;
		for(int counter = 0; counter < 4; counter++)
		{
			if(hand[counter] + 1 == hand[counter + 1])
			{
				curLength++;
			}
			else if(hand[counter] + 1 < hand[counter + 1])
			{
				curLength = 1;
			}
			if (curLength > maxLength)
			{
				maxLength = curLength;
			}
		}
		return maxLength;
	}
	
	public static boolean fullHouseFound(int hand[])
	{
		boolean foundFH = false;
		boolean found3k = false;
		boolean found2k = false;
		int currentCount;
		for(int dieValue = 1; dieValue <= 6; dieValue++)
		{
			currentCount = 0;
			for(int diePosition = 0; diePosition < 5; diePosition++)
			{
				if(hand[diePosition] == dieValue)
				{
					currentCount++;
				}
			}
			if(currentCount == 2)
			{
				found2k = true;
			}
			if(currentCount == 3)
			{
				found3k = true;
			}
		}
		if(found2k && found3k)
		{
			foundFH = true;
		}
		return foundFH;
	}
    
}
