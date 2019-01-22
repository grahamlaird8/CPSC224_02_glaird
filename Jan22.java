
package jan22;
import javax.swing.JOptionPane;
import java.lang.String;

/**
 *
 * @author graha
 */
public class Jan22 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
		String name;
		String ageStr;
		int age;
		int ageIn20;
		//dialog boxes:
		//need to import "import javac.swing.JOptionPane"
		//displaying a message: showMessageDialog
		
		//asking for input: showInputDialog. Always accepting a string value
		//If user hits Cancel, it will return null.
		name = JOptionPane.showInputDialog("Please enter your name:");
		JOptionPane.showMessageDialog(null, "Hello " + name);
		ageStr = JOptionPane.showInputDialog("Please enter your age:");
		age = Integer.parseInt(ageStr);
		//ageIn20 = age + 20;
		//JOptionPane.showMessageDialog(null, name + ", you will be " + ageIn20 + " in 20 years!");
		
		//comparisons are just like C++: <, >, <=, >=, ==, !=
		if(age < 13)
			JOptionPane.showMessageDialog(null, "Wow, you are a child!");
		else if (age < 20)
			JOptionPane.showMessageDialog(null, "You're a teenager!");
		else if (age < 21)
			JOptionPane.showMessageDialog(null, "You aren't a teenager, but also not able to drink. Loser.");
		else if (age < 60)
			JOptionPane.showMessageDialog(null, "You're a fully fledged adult!");
		else{
			JOptionPane.showMessageDialog(null, "You're a senior! Old head.");
		}
		
		
		
		System.exit(0);
    }
    
}
