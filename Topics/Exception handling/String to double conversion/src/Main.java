import java.util.Scanner;
class Converter {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.next();

    /**
     * It returns a double value or 0 if an exception occurred
     */
    public static double convertStringToDouble(String input) {
      try {
          return Double.parseDouble(input);
      } catch(Exception e) {
          return 0;
        }
    }
}