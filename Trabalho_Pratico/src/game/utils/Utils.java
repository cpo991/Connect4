package game.utils;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carolina Oliveira - 2017011988
 */
public class Utils {
    static Scanner sc;

    static {
        sc = new Scanner(System.in);
    }

    private Utils() {}

    public static void next() {
        System.out.println("Press \"ENTER\" to continue...");
        sc.nextLine();
    }

    /**
     * Sends a question to the user and reads the command written by him
     *
     * @param question the question to ask to the user
     * @returns the integer that the user inserted
     */
    public static int askInt(String question) {
        System.out.print(question);
        while (!sc.hasNextInt())
            sc.next();
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    /**
     * Sends a question to the user and reads the command written by him
     *
     * @param question the question to ask to the user
     * @returns the double that the user inserted
     */
    public static double askDouble(String question) {
        System.out.print(question);
        while (!sc.hasNextDouble())
            sc.next();
        double value = sc.nextDouble();
        sc.nextLine();
        return value;
    }

    /**
     * Sends a question to the user and reads the command written by him
     *
     * @param question the question to ask to the user
     * @returns the string that the user inserted
     */
    public static String askString(String question) {
        String answer;
        do {
            System.out.print(question);
            answer = sc.nextLine().trim();
        } while (answer.isEmpty());
        return answer;
    }

    /**
     * Shows the options to the user and returns the one he picked
     *
     * @param options the options the user can pick
     * @returns the option that the user inserted
     */
    public static int choseOption(String... options) {
        int option;
        do {
            for (int i = 0; i < options.length-1; i++)
                System.out.printf("%3d - %s\n",i+1,options[i]);
            System.out.printf("\n%3d - %s\n",0,options[options.length-1]);
            option = askInt("\n> ");
        } while (option<0 || option>=options.length);
        return option;
    }

    /**
     * Sorts a value in a range
     *
     * @param min,max the min and maximum value to be sorted
     * @returns the value sorted
     */
    public static int randNum(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static void launchLog(String className, String log){
        Logger logger = Logger.getLogger(className);
        logger.log(Level.INFO, log);
    }
}
