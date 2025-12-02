

public class Main{
    public static void main(String[] args) {
        int parsedArgument;
        

        try {
            if (args.length > 1) {
                throw new IllegalArgumentException("\033[31mInput cannot contain more than one argument\033[0m");
            } 
            parsedArgument = Integer.parseInt(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\033[31mCommand argument not found\033[0m");
            return;
        } catch (java.lang.NumberFormatException e) {
            System.out.println("\033[31mCommand argument is not numberic\033[0m");
            return;
        } catch (Exception e) {
            System.out.println("Strange exception occured, exiting.");
            return;
        }
        printTree(parsedArgument);
    }
    public static void printOneLine(int width, int numberOfStars) {
        int spaces = (width - numberOfStars) / 2;
        for (int s = 0; s < spaces; s++) {
            System.out.print(" ");
        }
        for (int i = 0; i < numberOfStars; i++) {
            System.out.print("\033[95m*");
        }
        System.out.println();
    }

    public static void printTree(int parsedArgument) {
        if (parsedArgument < 0) {
            throw new IllegalArgumentException("\033[31mInput cannot be less than 0\033[0m");
        }
        if (parsedArgument == 0 || parsedArgument == 1) {
            System.out.println("*");
            return;
        } 
        int width = 1 + 2 * (parsedArgument - 1);
        int numberOfExtendedLines = parsedArgument - 2;
        printOneLine(width, 1);
        int extendedStars = 3;
        for (int i = 0; i < numberOfExtendedLines; i++) {
            printOneLine(width, extendedStars);
            if (i % 2 == 1) {
                extendedStars += 2;
            }
        }
        printOneLine(width, 1);
    }
}