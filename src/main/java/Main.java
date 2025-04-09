import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * JavaDoc Instruction
 * This program reads a lists of PC components from  a CSV file,
 * then calculates the total cost based on quantity and unit price,
 * and then print a well formatted report of each component.
 * @author student_name
 */

public class Main {
    static final String INPUTFILE = "components.txt"; // Input file that reference the csv file that we are reading from

    // This is how we want our data to look when we print it.
    // It helps us make everything line up neatly. It prints:
    // - a word (like a name) in 20 spaces
    // - a number (like quantity) in 2 spaces
    // - a decimal number (like price) with 2 numbers after the dot
    static final String PRINTFFORMAT = "%20s %2d    %6.2f\n";

    public static void main(String[] args) {
        // Here we’re starting with 0 total cost. Later, we’ll add the prices to this.
        double total = 0;

        // Here we’re saying, “Let’s open the file called components.txt” so we can read it.
        File infile = new File(INPUTFILE);

        // Here we are printing the heading/title for our table.
        System.out.printf("%s%n","           Component Units Price");

        // TODO - add code that reads the input file, prints a component report,and calculates total cost

        // From here is where we are reading from the file
        try(Scanner sc = new Scanner(infile)){  // We use Scanner to read the file, line by line.
            while (sc.hasNextLine()){  // Keep reading as long as there’s another line in the file.

                String line = sc.nextLine(); // Here we read the next line from the file and save it in a variable called "line".

                // Here we split that line wherever there’s a comma.
                // Now we have 3 parts: the component name, the number of units, and the price.
                String[] parts = line.split(",");

                String component = parts[0].trim(); // get the first column and remove any  space using the trim method
                int units = Integer.parseInt(parts[1].trim()); // get the second column and remove any  space using the trim method and convert it to a number
                double price = Double.parseDouble(parts[2].trim()); // get the third column and remove any  space using the trim method and convert it to a decimal number

                total += units * price; // multiply the units and price and assigned it to total variable

                // Print the data using the format we set earlier.
                System.out.printf(PRINTFFORMAT, component, units, price);
            }
        }catch (FileNotFoundException e){ // If the file isn’t found, show this message.
            System.out.println("ERROR: File not found.");
        }catch (NumberFormatException e){
            System.out.println("ERROR: Invalid number format in file."); // If the number format is wrong (like letters where numbers should be), show this message.
        }

        System.out.println("Total cost: "+total); // Finally, print out the total cost of all the components.
    }
}
