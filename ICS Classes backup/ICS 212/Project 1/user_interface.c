/*****************************************************************
//
//  NAME:        Alan Reeves
//
//  HOMEWORK:    3b
//
//  CLASS:       ICS 212
//
//  INSTRUCTOR:  Ravi Narayan
//
//  DATE:        October 28, 2024
//
//  FILE:        user_interface.c
//
//  DESCRIPTION:
//   This file contains the user interface methods for the
//   bank software.
//
****************************************************************/

#include"record.h"
#include"database.h"
#include<string.h>
#include<stdio.h>

void displayMenu();
char* getAddress(char[], int);
int getValidAcctNum();

int debugMode = 0;



/*****************************************************************
//
//  Function name: main
//
//  DESCRIPTION:   This is the main function. It greets the user
//                 calls displayMenu and executes menu options.
//
//  Parameters:    argc (int) : The number of elements in argv
//                 argv (char*[]) : An array of arguments passed
//                                  to the program.
//
//  Return values: 0 : completed without issue.
//
****************************************************************/

int main(int argc, char* argv[])
{
    char userInput[30], newName[80], newAddress[80];
    int inputLength = 2, findAcctNum, deleteAcctNum, newAcctNum, result;
    struct record * start = NULL;

    if (argc > 2)
    {
        printf("Invalid arguments.\n");
    }
    else if (argc == 2 && strcmp("debug", argv[1]) != 0)
    {
        printf("Invalid arguments.\n");
    }
    else
    {
        if (argc == 2)
        debugMode = 1;
        
        /*Then run the whole program*/ 

        printf("%s", "Welcome to the database. This program stores ");
        printf("and retrieves customer data.\n");

        /*main loop of the program*/

        readFile(&start, "data.txt");
        do
        {
            displayMenu();
            fgets(userInput, 30, stdin);
            inputLength = strlen(userInput);

            if (strncmp(userInput, "quit", inputLength -1) == 0)
            {
                /*Do nothing*/
            }

            else if (strncmp(userInput, "add", inputLength - 1) == 0)
            {
                printf("Creating a new account record.\n");
                newAcctNum = getValidAcctNum();

                printf("Enter the account holder's name.\n");
                fgets(newName, 80, stdin);
                newName[strlen(newName) - 1] = '\0';
                /*This removes the new line char from newName*/

                printf("Enter the account holder's address.\n");
                getAddress(newAddress, 80);

                result = addRecord(&start, newAcctNum, newName, newAddress);
                if (result == 0)
                {
                    printf("Added account.\n");
                }
                else
                {
                    printf("Unable to add account. May be a duplicate.\n");
                }
            
            }

            else  if (strncmp(userInput, "printall", inputLength - 1) == 0)
            {
                if (start != NULL)
                {
                    printAllRecords(start);
                }
                else
                {
                    printf("%s\n", "Database is empty.");
                }
            }    

            else if (strncmp(userInput, "find", inputLength - 1) == 0)
            {
                findAcctNum =  getValidAcctNum();
                if (findRecord(start, findAcctNum) == -1)
                {
                    printf("Record not found.\n");
                }
            }

            else if (strncmp(userInput, "delete", inputLength - 1) ==0)
            {
                deleteAcctNum = getValidAcctNum();
                result = deleteRecord(&start, deleteAcctNum);
                if (result == -1)
                {
                    printf("Record not found.\n");
                }
                else if (result == 0)
                {
                    printf("Record deleted.\n");
                } 
            }
       
            else 
            {
                printf("Invalid input.\n");
            }
        }
        while (strncmp(userInput, "quit", inputLength - 1) != 0);
    if (start != NULL)
    {
    writeFile(start, "data.txt");
    cleanup(&start);
    }    
    }
    return 0; 
}

/*****************************************************************
//
//  Function name: displayMenu
//
//  DESCRIPTION:   This function prints a menu for the user.
//
//  Parameters:    none
//
//  Return values: none
//
****************************************************************/

void displayMenu()
{
    if (debugMode == 1)
    {
        printf("called displayMenu()\n");
    }
    printf("Please select one of the following options or press");
    printf(" enter to quit.\n\n");
    printf("add:      add a new account record\n");
    printf("printall: show all existing records\n");
    printf("find:     find an account by its account number\n");
    printf("delete:   delete an existing record by its account");
    printf(" number\n");
    printf("quit:     exit this program\n\n");
}

/****************************************************************
//  Function name: getAddress
//
//  Description:   Gets the address input by the user and works
//                 over multiple lines.
//
//  Parameters:    address (char*) : an array to hold the address.
//                 arrySize (int) : the size of the array.
//
//  Return values: address (char*) : a char array containing
//                 the user's multi-line address.
//
***************************************************************/

char* getAddress(char* address, int arrySize)
{
    int placeInString;
    char newChar, trashInput[80];

    if (debugMode == 1)
    {
        printf("called getAddress(char address, int %d)\n", arrySize);
        printf("address is an uninitialized string\n");
    }

    printf("Type as many lines as you like, and ");
    printf("input a percent symbol when done.\n");

    placeInString = 0;

    do
    {
        newChar = fgetc(stdin);
        if (newChar != '%' && placeInString < arrySize - 2)
        {
            address[placeInString] = newChar;
            address[placeInString + 1] = '\0';
            placeInString = placeInString + 1;
        }
    }
    while (newChar != '%');

    fgets(trashInput, 80, stdin);
    
    return address;
}

/***************************************************************
//  Function name: getValidAcctNum
//
//  Description:   asks for positive integer acct num until it
//                 receives one.
//
//  Parameters:    none
//
//  Return values: acctNum (int) : the valid acct num once found.
//
****************************************************************/

int getValidAcctNum()
{
    int acctNum = -1;
    char trashInput[80];

    if (debugMode == 1)
    {
        printf("called getValidAcctNum()\n");
    }

    printf("Please enter an account number\n");
    scanf("%d", &acctNum);
    fgets(trashInput, 80, stdin);

    while (acctNum < 1)
    {
        printf("The account number must be an integer greater than 0\n");
        printf("Please enter an account number.\n");
        scanf("%d", &acctNum);
        fgets(trashInput, 80, stdin);
    }
    return acctNum;
}
