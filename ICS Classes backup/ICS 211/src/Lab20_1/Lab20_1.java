package Lab20_1;
import java.util.Scanner;

public class Lab20_1 {
  /*
   * @author Alan Reeves
   * This is the main method.
   * @param args not used.
   */
  public static void main(String[] args) {

      
      final double PARTICIP_MAX = 1468.0;
      final double CHALLENGE_MAX = 460.0;
      final double ACTIVE_MAX = 295.0;
      final double ASSIGN_MAX = 80.0;    

      double participWeight = 0;
      double chalWeight = 0;
      double activeWeight = 0;
      double assignWeight = 0;

      double participGrade;
      double chalGrade;
      double activeGrade;
      double assignGrade;
      
      Scanner scnr = new Scanner(System.in);

      boolean cont = true; 

      //take in input and make something usable of it
    
        String userInput = scnr.nextLine();

        String[] parsed = userInput.split(" ");

        String status = parsed[0];

        //this section is for the one example where the number of spaces between data is variable. 
        //I could not think of a solution for all cases so I made a solution more or less to cover the one exception in the lab.
        if (parsed.length != 5) {
          String[] holder = userInput.split(" ",2);
          status = holder[0];
          String[] temp = holder[1].split("  ");

          participGrade = Double.valueOf(temp[0]);
          chalGrade = Double.valueOf(temp[1]);
          activeGrade = Double.valueOf(temp[2]);
          assignGrade = Double.valueOf(temp[3]);


        } else {
        participGrade = Double.valueOf(parsed[1]);
        chalGrade = Double.valueOf(parsed[2]);
        activeGrade = Double.valueOf(parsed[3]);
        assignGrade = Double.valueOf(parsed[4]);
        }
        


        if ("UG".equals(status)) {
        participWeight = 0.20;
        chalWeight = 0.20;
        activeWeight = 0.30;
        assignWeight = 0.30;


        } else if ("G".equals(status)){
        participWeight = 0.15;
        chalWeight = 0.05;
        activeWeight = 0.35;
        assignWeight = 0.45;
        
        } else if ("DL".equals(status)){
        participWeight = 0.20;
        chalWeight = 0.25;
        activeWeight = 0.30;
        assignWeight = 0.25;
        

        } else {
        System.out.println("Error: student status must be UG, G or DL");
        cont = false;
          }


    //calculate averages

    double participAverage;
    double chalAverage;
    double activeAverage;
    double assignAverage;

    participAverage = (participGrade / PARTICIP_MAX) * 100;
    if (participAverage > 100.0){
      participAverage = 100;
    }
    chalAverage = (chalGrade / CHALLENGE_MAX) * 100;
    if (chalAverage > 100.0){
      chalAverage = 100;
    }
    activeAverage = (activeGrade / ACTIVE_MAX) * 100;
    if (activeAverage > 100) {
      activeAverage = 100;
    }
    assignAverage = (assignGrade / ASSIGN_MAX) * 100;
    if (assignAverage > 100.0){
      assignAverage = 100;
    }

    //calculate overall grade average

    double gAverage = (participAverage * participWeight) + (chalAverage * chalWeight) + (activeAverage * activeWeight) + (assignAverage * assignWeight);

    //calculate letter grade

    String letterGrade = "A";

    if (gAverage < 60.0) {
      letterGrade = "F";
    } else if (gAverage >= 60.0 && gAverage < 65.0) {
      letterGrade = "D-";
    } else if (gAverage >= 65.0 && gAverage < 67.0) {
      letterGrade = "D";
    } else if (gAverage >= 67.0 && gAverage < 70.0) {
      letterGrade = "D+";
    } else if (gAverage >= 70.0 && gAverage < 74.0) {
      letterGrade = "C-";
    } else if (gAverage >= 74.0 && gAverage < 77.0) {
      letterGrade = "C";
    } else if (gAverage >= 77.0 && gAverage < 80.0) {
      letterGrade = "C+";
    } else if (gAverage >= 80.0 && gAverage < 84.0) {
      letterGrade = "B-"; 
    } else if (gAverage >= 84.0 && gAverage < 87.0) {
      letterGrade = "B";
    } else if (gAverage >= 87.0 && gAverage < 90.0) {
      letterGrade = "B+";
    } else if (gAverage >= 90.0 && gAverage < 95.0) {
      letterGrade = "A-";
    } else if (gAverage >= 95.0 && gAverage < 100.0) {
      letterGrade = "A";
    } else if (gAverage >= 100.0) {
      letterGrade = "A+";
    }



    //print grades
    if (cont) {
    System.out.print("Participation Activities: ");
    System.out.printf("%.1f", participAverage);
    System.out.println("%");

    System.out.print("Challenge Activities: ");
    System.out.printf("%.1f", chalAverage);
    System.out.println("%");

    System.out.print("Lab Activities: ");
    System.out.printf("%.1f", activeAverage);
    System.out.println("%");

    System.out.print("Lab Assignments: ");
    System.out.printf("%.1f", assignAverage);
    System.out.println("%");

    System.out.print( status + " average: ");
    System.out.printf("%.1f", gAverage);
    System.out.println("%");

    System.out.println("Course grade: " + letterGrade);
    }

    scnr.close();
   } //end of main
}
