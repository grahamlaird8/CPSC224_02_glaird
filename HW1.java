/********************************
	Homework #1
	Due: 1/29/19
	Graham Laird and Finn Allen
 ********************************/
package hw1;
import java.util.Random;


public class HW1 {

    
    public static void main(String[] args) {
        const int DICE_IN_PLAY = 5;
		int hand[DICE_IN_PLAY];
		char playAgain = 'y';
		
		while(playAgain == 'y')
		{
			
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
