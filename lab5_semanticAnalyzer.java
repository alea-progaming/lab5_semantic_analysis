import java.util.Scanner;

public class lab5_semanticAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Expression: ");
        String expression = scanner.nextLine(); // int num = 3.0;

        if (isAssignmentStatementValid(expression)) {
            System.out.println("Semantically Correct!");
        } else {
            System.out.println("Semantically Incorrect!");
        }

        scanner.close();
    }

    public static boolean isAssignmentStatementValid(String expression) {
        String[] tokens = expression.split("\\s+"); // int  ->  num  ->  =  ->  3.0  ->  ;s
        char lastCharacter = tokens[3].charAt(tokens[3].length() - 1);

        String dataType = tokens[0];
        String variable = tokens[1];
        String delimiter = expression.replace(";", ""); //to not read the semicolon as one with the value
        String[] temp = delimiter.split("\\s+");
        String value = temp[3];
        
        // Check if the expression has the correct structure
        if (tokens.length != 4 || !tokens[2].equals("=") || lastCharacter != ';' || variable.matches("\\d+")) {
            return false;
        }

        String[] reservedKeywords = {"int", "double", "String", "boolean"};
        for (String words : reservedKeywords) {
            if (variable.equals(words)) {
                return false;
            }
        }

        // Define regular expressions for valid data types
        String intPattern = "\\d+";
        String doublePattern = "\\d+(\\.\\d+)?";
        String stringPattern = "\"[^\"]*\\s*\"";

        // Verify data type and value matchS
        if (dataType.equals("int") && value.matches(intPattern)) {
            return true;
        } else if (dataType.equals("double") && value.matches(doublePattern)) {
            return true;
        } else if (dataType.equals("String") && value.matches(stringPattern)) {
            return true;
        } else if (dataType.equals("boolean") && value.equals("true") || value.equals("false")) {
            return true;
        }
        return false;
    }
}