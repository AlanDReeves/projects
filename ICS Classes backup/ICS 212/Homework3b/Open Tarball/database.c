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
//  DATE:        September 25, 2024
//
//  FILE:        database.c
//
//  DESCRIPTION:
//  This file contains the database side methods for the bank
//  software homework. Currently none are implemented.
//
****************************************************************/

#include"record.h"
#include"database.h"
#include<stdio.h>

int debugMode;

/*****************************************************************
//
//  Function name: addRecord
//
//  DESCRIPTION:   Adds a record to the database
//
//  Parameters:    record (struct record**) : A pointer the next
//                 record  after the one to be added.
//
//                 acctNum (int) : The account number for the new
//                 record.
//
//                 name (char[]) : The name of the account holder
//                 address (char[]) : account holder's address
//
//  Return values:  0 : record added without issue
//                  -1 : failed to add record
//
****************************************************************/

int addRecord(struct record** record, int acctNum, char*  name, char* address)
{
    if (debugMode == 1)
    {
        printf("called addRecord(int %d, char[] %s, char[] %s)\n", acctNum, name, address);
    }
    return -1;
}

/*****************************************************************
//
//  Function name: printAllRecords
//
//  DESCRIPTION:   This method prints all records in the database.
//
//  Parameters:    startRecord (struct record*) : a pointer to the
//                 first record to print.
//
//  Return values: none
//
****************************************************************/

void printAllRecords(struct record* startRecord)
{
    if (debugMode == 1)
    {
        printf("called printAllRecords()\n");
    }
}

/*****************************************************************
//  Function name: findRecord
//
//  DESCRIPTION:   This method finds a record by account number
//
//  Parameters:    record (struct record*) : a pointer to the
//                 first record in the list.
//
//                 accountNumber (int) : the account number to 
//                 search for.
//
//  Return values: -1 : failure
//
****************************************************************/

int findRecord(struct record* record, int accountNumber)
{
    if (debugMode == 1)
    {
        printf("called findRecord(int %d)\n", accountNumber);
    }
    return -1;
}

/***************************************************************
//  Function name: deleteRecord
//
//  Description:   This method deletes a record given an account
//                 number.
//
//  Parameters:    startRecord (struct record**) : a pointer to 
//                 the first record to start searching at.
//
//                 accountNumber (int) : the account number
//                 to find.
//
//  Return Values: 0 : record deleted without issue.
//                 -1 : record not found
//
***************************************************************/

int deleteRecord(struct record** startRecord, int accountNumber)
{
    if (debugMode == 1)
    {
        printf("called deleteRecord(int %d)\n", accountNumber);
    }
    return -1;
}


