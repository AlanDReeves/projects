package Lab20_3;

import java.util.Scanner;

/*
 * This class calculates taxes based on wages, taxable income, etc.
 * @author Alan Reeves.
 */
public class Taxes {

    /*
     * This is the main method. 
     * @param args not used.
     */
    public static void main(String[] args) {
        int wages;
        int taxableInterest;
        int unemploymentCompensation;
        int status;
        int taxesWithheld;

        Scanner scan = new Scanner(System.in);

        wages = scan.nextInt();
        taxableInterest = scan.nextInt();
        unemploymentCompensation = scan.nextInt();
        status = scan.nextInt();
        taxesWithheld = scan.nextInt();

        int AGI = calcAGI(wages, taxableInterest, unemploymentCompensation);
        int deduction = getDeduction(status);
        int taxable = calcTaxable(AGI, deduction);
        int taxInt = calcTax(status, taxable);
        int taxesDue = calcTaxDue(taxInt, taxesWithheld);

        System.out.printf("AGI: $%,d\n", AGI);
        System.out.printf("Deduction: $%,d\n", deduction);
        System.out.printf("Taxable income: $%,d\n", taxable);
        System.out.printf("Federal tax: $%,d\n", taxInt);
        System.out.printf("Tax due: $%,d\n", taxesDue);


        scan.close();
    } //end of main

    public static int calcAGI(int wages, int taxableInterest, int unemploymentCompensation) {
        int AGI = Math.abs(wages) + Math.abs(taxableInterest) + Math.abs(unemploymentCompensation);
        return AGI;
    }

    /*
     * @param status is merital status. 0 for dependend, 1 for single, 2 for married.1000
     */
    public static int getDeduction(int status) {
        switch (status) {
            case 0:
                return 6000;
            case 1:
                return 12000;
            case 2:
                return 24000;
            default:
                return 6000;

            }
    }

    public static int calcTaxable(int AGI, int deduction) {
        int result = AGI - deduction;
        if (0 > result) {
            return 0;
        } else {
            return result;
        }
    }

    public static int calcTax(int status, int taxable) {
        double tax;

        if (status != 2) {
            if (taxable <= 10000) {
                tax = taxable * 0.1;
            } else if (taxable <= 40000) {
                tax = ((taxable - 10000) * 0.12) + 1000;
            } else if (taxable <= 85000) {
                tax = ((taxable - 40000) * 0.22) + 4600;
            } else {
                tax = ((taxable - 85000) * 0.24) + 14500;
            }

        } else {
            if (taxable <= 20000) {
                tax = taxable * 0.1;
            } else if (taxable <= 80000) {
                tax = ((taxable - 20000) * 0.12) + 2000;
            } else {
                tax = ((taxable  - 80000) * 0.22) + 9200;
            }
        }
        int taxInt = (int) Math.round(tax);

        return taxInt;
    }

    public static int calcTaxDue(int tax, int taxesWithheld) {
        int taxesDue = Math.abs(tax) - Math.abs(taxesWithheld);
    
        return taxesDue;
    }


}
