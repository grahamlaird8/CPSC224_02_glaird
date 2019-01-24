import java.util.*;


public class ICA1 {

    public static void main(String[] args) {
        double meters;
		int userInput;
		Scanner scnr = new Scanner(System.in);
		
		System.out.print("Please enter a distance in meters: ");
		meters = scnr.nextDouble();
		
		while(meters < 0) {
			System.out.println("Please do not enter a negative number.");
			System.out.print("Enter a (positive) distance in meters: ");
		}
		
		do{
			menu();
			userInput = scnr.nextInt();
		
			System.out.println();
			
			if(userInput == 1)
			{
				showKilometers(meters);
			}
			else if(userInput == 2)
			{
				showInches(meters);
			}
			else if(userInput == 3)
			{
				showFeet(meters);
			}
			else if(userInput == 4)
			{
				System.out.println("Goodbye!");
			}
			else
			{
				System.out.println("Error, that was not one of the options. Please select option 1, 2, 3, or 4.");
			}
		} while(userInput != 4);
    }
	
	public static void menu()
	{
		System.out.println("1. Convert to kilometers");
		System.out.println("2. Convert to inches");
		System.out.println("3. Convert to feet");
		System.out.println("4. Quit the program");
		System.out.println();
		System.out.print("Please enter your choice: ");
	}
	
	public static void showKilometers(double meters){
		double kilometers = meters * .001;
		System.out.println(meters + " meters is equivalent to " + kilometers + " kilometers!");
	}
	
	public static void showInches(double meters){
		double inches = meters * 39.37;
		System.out.println(meters + " meters is equivalent to " + inches + " inches!");
	}
	
	public static void showFeet(double meters){
		double feet = meters * 3.281;
		System.out.println(meters + " meters is equivalent to " + feet + " feet!");
	}
    
}
