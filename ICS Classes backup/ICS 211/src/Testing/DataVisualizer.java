package Testing;

import java.util.ArrayList;
import java.util.Scanner;

public class DataVisualizer {
   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);

      //prompt user for title of table
      System.out.println("Enter a title for the data:");

      String title = scnr.nextLine();

      System.out.printf("You entered: %s\n\n", title);  

      //ask for column headers
      System.out.println("Enter the column 1 header:");
      String header1 = scnr.nextLine();
      System.out.printf("You entered: %s\n", header1);
      System.out.println();

      System.out.println("Enter the column 2 header:");
      String header2= scnr.nextLine();
      System.out.printf("You entered: %s\n", header2);
      System.out.println();

      //Ask for data points
      ArrayList<String> stringList = new ArrayList<String>();
      ArrayList<Integer> intList = new ArrayList<Integer>();
      
      while (true) {

        System.out.println("Enter a data point (-1 to stop input):");

        //I'm not sure why these two need to be in the while loop.
        //When I put them outside it crashed the program though.
        String userInput = scnr.nextLine();
        if (userInput.equals("-1")) {
            break;
        }
        Scanner stringReader = new Scanner(userInput);

        //check for errors
        if (userInput.indexOf(',') == -1) {
            System.out.println("Error: No comma in string.");
            System.out.println();
            continue;
            
        } 

        if (userInput.indexOf(',') != userInput.lastIndexOf(',')) {
            System.out.println("Error: Too many commas in input.");
            System.out.println();
            continue;
        }


        //add to data set
        String stringHolder = userInput;

        int commaIndex = stringHolder.indexOf(',');
        stringHolder = stringHolder.substring(0 , commaIndex);

        String intString = userInput.substring(commaIndex, userInput.length());

        //BUT FIRST check to see that the integer is good or else stringList will take an input but intList won't


        try 
        {
            String intHolder = userInput.substring(commaIndex + 1, userInput.length());
            int convertedInt = Integer.parseInt(intHolder);
            intList.add(convertedInt);

            //add to stringList here instead, just in case.
            stringList.add(stringHolder);

            System.out.printf("Data string: %s\n", stringHolder);
            System.out.printf("Data integer: %d\n", convertedInt);
            System.out.println();

        } catch (Exception e) { 
            System.out.println("Error: Comma not followed by an integer.");
            System.out.println();
        }

      } //end of while

      //print out the data
      System.out.println();
      System.out.printf("%33s\n", title);
      System.out.printf("%-20s|%23s\n", header1, header2);
      System.out.println("--------------------------------------------");


      if (!stringList.isEmpty() && !intList.isEmpty()) {
        for (int i = 0; i < stringList.size(); i++) {
        System.out.printf("%-20s|%23d\n", stringList.get(i), intList.get(i));
        }
        System.out.println();

        //now print out histogram
        for (int i = 0; i < stringList.size(); i++) {
            System.out.printf("%20s ", stringList.get(i));
            for (int j = 0; j < intList.get(i); j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
      


      scnr.close();
   } //end of main
}
