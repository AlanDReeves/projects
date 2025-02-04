/*****************************************************************
//
//  NAME:        Alan Reeves
//
//  HOMEWORK:    2
//
//  CLASS:       ICS 212
//
//  INSTRUCTOR:  Ravi Narayan
//
//  DATE:        September 13, 2024
//
//  FILE:        homework2.c
//
//  DESCRIPTION:
//   This file contains code to ask the user for a number and
//   then prints every number before that,and if the number is
//   a multiple of 3.
//
*****************************************************************/

#include<stdio.h>

int user_interface();
int is_multiple3(int);
void print_table(int);


/*****************************************************************
//
//  Function name: user_interface
//
//  DESCRIPTION:   interfaces with the user to get their
//                 desired number
//
//  Parameters:    none
//
//  Return values: int user_input: some positive integer 
//                 taken from the user.
//
****************************************************************/

int user_interface()
{
    int user_input, scan_result;
    char trash[100];

    user_input = -1;

    printf("This program accepts a positive integer and prints ");
    printf("a table showing every prior integer and if it is ");
    printf("divisble by 3.\n");
    printf("\nEnter maximum number to show: " );

    scan_result = scanf("%d", &user_input);
    while (scan_result < 1)
    {
        printf("\nInvalid input. Please enter an integer.\n");
        fgets(trash, 80 ,stdin);
        scan_result = scanf("%d", &user_input);
    }

    while (user_input <= 0)
    {
        printf("\nInvalid input. Please enter a POSITIVE integer. \n");
        fgets(trash, 80, stdin);
        scan_result = scanf("%d", &user_input);
    }

    fgets(trash, 80, stdin);

    return user_input;  

}

/****************************************************************
//  Function name: is_multiple3
//
//  DESCRIPTION:   This function determines if the int given is
//                 divisible by 3. Returns a false possitive for
//                 number 0.
//
//  Parameters:    num (int): number to check for divisibility
//
//  Return values:  1 : the number is divisble by 3.
//                  0 : the number is not divisble by 3.
//
****************************************************************/

int is_multiple3(int num) 
{
     int return_val;

    return_val = 0;
    if (num % 3 == 0)
    {
        return_val = 1;
    }
    
    return return_val;
}

/****************************************************************
//  Function name: print_table
//
//  DESCRIPTION:   This function prints a table with every number
//                 lower than the one entered and states if the
//                 number is divisible by 3.
//
//  Parameters:    int user_num: the maximum number to show
//
//  Return values: none
//
*****************************************************************/

void print_table(int user_num)
{
    int i;

    printf("%8s", "Number");
    printf("%16s\n", "Multiple of 3?");

    for (i = 0; i <= user_num; i++)
    {
        printf("%8d", i);

        if (i == 0)
        {
            printf("%5s\n", " NO");
        }

        else  if (is_multiple3(i) == 0)
        {
            printf("%5s\n", " NO");
        }
        else 
        {
            printf("%5s\n", "YES");
        }
    }
    printf("\n");
}

/*****************************************************************
//
//  Function name: main
//
//  DESCRIPTION:   A function which calls user_interface() and
//                 print_table(), which itself calls
//                 the is_multiple3() function.
//
//  Parameters:    int argc: not used.
//                 char* argv[]: not used.
//
//  Return values:  1 : main concluded successfully.
//
****************************************************************/

int main(int argc, char* argv[])
{
    int user_num;

    user_num = user_interface();
    print_table(user_num);
    
    return 1;
}
