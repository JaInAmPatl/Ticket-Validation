import java.util.Scanner;

/**
 * This table test program takes the table and accesses its rows
 * and columns to test the Table class.
 *
 * @author Jainam Patel
 * @version 08/03/2024
 */
public class TableTestProgram {
    public static void main(String[] args) throws Exception {

        Scanner inputScanner = new Scanner(System.in);
        String tableNameInput = "";
        String userChoice = "";
        String userKey = "";

        System.out.print("Enter the name of the tab delimited text file you wish to manage (e.g. codes.txt) > ");
        tableNameInput = inputScanner.nextLine();
        Table userTable = new Table(tableNameInput);
        System.out.println("Successfully loaded: " + userTable);

        while (true) {
            System.out.println("\n\nTable Testing Menu\n");

            System.out.println("1. Display all data");
            System.out.println("2. Lookup");
            System.out.println("3. Search");
            System.out.println("4. Change");
            System.out.println("5. Save data to " + tableNameInput);
            System.out.println("6. Quit");
            System.out.print("Select > ");
            userChoice = inputScanner.nextLine();

            if (userChoice.equals("1")) userTable.display();

            if (userChoice.equals("2")) {
                System.out.println("Enter the key >");
                userKey = inputScanner.nextLine();
                int rowNumber = userTable.lookup(userKey);
                if (rowNumber >= 0) {
                    System.out.println("Found " + userKey + " at row = " + String.format("%d", rowNumber));
                } else
                    System.out.println(userKey + " not found.");
            }

            if (userChoice.equals("3")) {
                System.out.println("Enter the key >");
                userKey = inputScanner.nextLine();

                String[] matches;
                matches = userTable.getMatches(userKey);

                if (matches != null)
                    for (String match : matches) {
                        System.out.print(match + "\t");
                    }
            }

            if (userChoice.equals("4")) {
                System.out.print("Type 1 to change it using row or Type 2 to change using key value ");
                int subChoice = inputScanner.nextInt();
                inputScanner.nextLine();
                if (subChoice == 1) {
                    System.out.println("Enter row number");
                    int rowNum = inputScanner.nextInt();
                    inputScanner.nextLine();
                    System.out.println("Enter Col number");
                    int colNum = inputScanner.nextInt();
                    inputScanner.nextLine();
                    System.out.println("Enter the new Value");
                    String newValue = inputScanner.nextLine();
                    userTable.change(rowNum, colNum, newValue);
                    System.out.println("Changes are done");

                } else if (subChoice == 2) {
                    System.out.println("Enter The key value");
                    String keyInputValue = inputScanner.nextLine();
                    System.out.println("Enter Col number");
                    int colNum = inputScanner.nextInt();
                    inputScanner.nextLine();
                    System.out.println("Enter the new Value");
                    String newValue = inputScanner.nextLine();
                    userTable.change(keyInputValue, colNum, newValue);
                    System.out.println("Changes are done");

                } else {
                    System.out.println("Enter a valid value");
                }

            }

            if (userChoice.equals("5")) {
                System.out.println("The file with changes are saved");
                userTable.save();
            }

            if (userChoice.equals("6")) break;
        }

        System.out.print("Thank-you, good bye!");
    }
}
