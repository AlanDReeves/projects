/****************************************************************
//
//  NAME:        Alan Reeves
//
//  HOMEWORK:    Project 2
//
//  Class:       ICS 212
//
//  INSTRUCTOR:  Ravi Narayan
//
//  DATE:        November 30, 2024
//
//  FILE:        user_interface.cpp
//
//  DESCRIPTION: 
//    This file contains the user interface methods for the database software and 
//    holds the main method.
//
*********************************************************************/

#include"record.h"
#include"llist.h"
#include<string.h>
#include<iostream>

using namespace std;

void displayMenu();
int getValidAcctNum();
char* getAddress(char[], int);

/***********************************************************************
//
//  Function name: displayMenu
//
//  DESCRIPTION:   This function prints a menu for the user.
//
//  Parameters:    none
//
//  Return Values: none
//
*************************************************************************/
void displayMenu()
{
    #ifdef DEBUGMODE
    cout << "called displayMenu()\n";
    #endif

    cout << "Please select one of the following options or press";
    cout << " enter to quit.\n\n";
    cout << "add:      add a new account record\n";
    cout << "printall: show all existing records\n";
    cout << "find:     find an account by its account number\n";
    cout << "delete:   delete an existing record by its account number\n";
    cout << "quit:     exit this program\n\n";

}

/************************************************************************
//
//  Function name: getAddress
//
//  Description:   Gets the address input by the user and works over 
//                 multiple lines.
//  
//  Parameters:    address (char*) : an array to hold the address
//                 arrySize (int) : the size of the array
//
//  Return values: address (char*) : a char array containing the user's
//                 multi-line address.
//
************************************************************************/
char* getAddress(char* address, int arrySize)
{
    int placeInString;
    char newChar;

    #ifdef DEBUGMODE
    cout << "called getAddress(char* address, int ";
    cout << arrySize;
    cout << "\n";
    #endif 

    cout << "Type as many lines as you like, and input a percent symbol";
    cout << " when done.\n";

    placeInString = 0;

    do
    {
        cin.get(newChar);
        if (newChar != '%' && placeInString < arrySize - 2)
        {
            address[placeInString] = newChar;
            address[placeInString + 1] = '\0';
            placeInString = placeInString + 1;
        }
    }
    while (newChar != '%');

    cin.ignore(999, '\n');

    return address;
}

/**********************************************************************
//
//  Function name: getValidAcctNum
//
//  Description:   asks for positive integer acct num until it receives one
//
//  Parameters:    none
//
//  Return values: acctNum (int) : the valid acct num once found
//
**********************************************************************/
int getValidAcctNum()
{
    int acctNum = -1;
    bool cinState = false;

    #ifdef DEBUGMODE
    cout << "called getValidAcctNum()\n";
    #endif

    cout << "Please enter an account number\n";
    cin >> acctNum;

    while (acctNum < 1)
    {
        cinState = cin;
        if (cinState == 0)
        {
            cin.clear();
            cin.ignore(999, '\n');
        }

        cout << "The account number must be an integer greater than 0\n";
        cout << "Please enter an account number.\n";
        cin >> acctNum;
    }
    return acctNum;
}
/************************************************************************
//
//  Function name: << operator
//
//  DESCRIPTION:   Overloads the << operator for the llist class so that it
//                 prints all records in the llist.
//
//  Parameters:    stream (ostream&) : the stream to print to
//                 list (const llist&) : the llist instance to print
//
//  Return Values: stream (ostream&) : the same stream given
//
*************************************************************************/
std::ostream& operator<<(std::ostream& stream, const llist& list)
{
    struct record* cursor = list.start;

    if (cursor == NULL)
    {
        cout << "No records found" << endl;
    }

    while (cursor != NULL)
    {
        stream << cursor->accountno << endl;
        stream << cursor->name;
        stream << cursor->address << endl;

        cursor = cursor->next;
    }

    return stream;
}


/*************************************************************************
//
//  Function name: main
//
//  DESCRIPTION:   This is the main method. It greets the user, calls displayMenu
//                 and executes menu options.
//
//  Parameters:    argc (int) : not used
//                 argv (char*[]) : not used
//
//  Return Values : 0 : completed without issue.
//
*************************************************************************/
int main(int argc, char* argv[])
{
    llist database;
    char userInput[30], newName[45], newAddress[80];
    int inputLength = 2, findAcctNum, deleteAcctNum, newAcctNum, result;    

    cout << "Welcome to the database. This program stores ";
    cout << "and retrieves customer data.\n";

    //main loop of program

    do
    {
        displayMenu();
        cin >> userInput;

        if (strncmp(userInput, "quit", inputLength - 1) == 0)
        {
            //Do nothing
        }

        else if (strncmp(userInput, "add", inputLength - 1) == 0)
        {
            cout << "Creating a new account record.\n";
            newAcctNum = getValidAcctNum();

            cout << "Enter the account holder's name.\n";
            //cin.get(newName, 25);
            cin >> newName;

            cout << "Enter the account holder's address.\n";
            getAddress(newAddress, 80);

            result = database.addRecord(newAcctNum, newName, newAddress);
            if (result == 0)
            {
                cout << "Added account.\n";
            }
            else 
            {
                cout << "Unable to add account. May be duplicate.\n";
            }
        }

        else if (strncmp(userInput, "printall", inputLength - 1) == 0)
        {
            //database.printAllRecords();
            cout << database;
        }

        else if (strncmp(userInput, "find", inputLength - 1) == 0)
        {
            findAcctNum = getValidAcctNum();
            if (database.findRecord(findAcctNum) == -1)
            {
                cout << "Record not found.\n";
            }
        }

        else if (strncmp(userInput, "delete", inputLength - 1) == 0)
        {
            deleteAcctNum = getValidAcctNum();
            result = database.deleteRecord(deleteAcctNum);
            if (result == -1)
            {
                cout << "Record not found.\n";
            }
            else if (result == 0)
            {
                cout << "Record deleted.\n";
            }
        }

        else
        {
            cout << "Invalid input.\n";
        }
    }
    while (strncmp(userInput, "quit", inputLength - 1) != 0);
    
    return 0;       
}
