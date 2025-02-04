/*****************************************************************
//
//  NAME:        Alan Reeves
//
//  HOMEWORK:    1
//
//  CLASS:       ICS 212
//
//  INSTRUCTOR:  Ravi Narayan
//
//  DATE:        September 3, 2024 
//
//  FILE:        homework1.c
//
//  DESCRIPTION:
//   A version of helloWorld in the C language. 
//
****************************************************************/

#include <stdio.h>

/*****************************************************************
//
//  Function name: main
//
//  DESCRIPTION:   Prints the necessary text for the homework assignment.
//
//  Parameters:    argc (int) : The number of elements in argv (not used)
//                 argv (char*[]) : An array of arguments passed
//                                  to the program.(not used)
//
//  Return values:  0 : program completed successfully
//                 -1 : not used
//
****************************************************************/

int main(int argc, char* argv[])
{
    int i;

    printf("Hello\n");
    printf("World\n");
    printf("!!!\n");


    i = 0;
    while (i < 4) {
        printf("While loop!\n");
        i = i + 1;
    }

    i = 0;
    do {
        printf("Do-while loop!\n");
        i = i + 1;
    }
     while (i < 3);

    return 0;
}

