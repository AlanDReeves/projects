/*****************************************************************
//
//  NAME:        Alan Reeves
//
//  HOMEWORK:    Project 1
//
//  CLASS:       ICS 212
//
//  INSTRUCTOR:  Ravi Narayan
//
//  DATE:        October 28, 2024
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
#include<stdlib.h>
#include<string.h>

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

int addRecord(struct record** start, int acctNum, char*  name, char* address)
{
    struct record* newRecordP, *cursor, *follower;
    int returnVal = -1;

    newRecordP = (struct record*) malloc(sizeof(struct record));
    

    if (debugMode == 1)
    {
        printf("called addRecord(int %d, char[] %s, char[] %s)\n", acctNum, name, address);
    }

    (*newRecordP).accountno = acctNum;
    strcpy((*newRecordP).name, name);
    strcpy((*newRecordP).address, address);

    if (*start == NULL)
    {
        *start = newRecordP;
        (*newRecordP).next = NULL;
        returnVal = 0;
    }
    else
    {
        if ((*newRecordP).accountno < (**start).accountno)
        {
            (*newRecordP).next = (*start);
            *start = newRecordP;
            returnVal = 0;
        }
        else
        {
            cursor = *start;
            while ((*newRecordP).accountno > (*cursor).accountno && (*cursor).next != NULL)
            {
                follower = cursor;
                cursor = (*cursor).next;
            }
            if ((*newRecordP).accountno == (*cursor).accountno)
            {
                /*do nothing, duplicate found*/
                returnVal = -1;
            }
            else if ((*cursor).next == NULL && (*newRecordP).accountno > (*cursor).accountno)
            {
                (*cursor).next = newRecordP;
                (*newRecordP).next = NULL;
                returnVal = 0;
            }
            else
            {
                (*newRecordP).next = cursor;
                (*follower).next = newRecordP;
                returnVal = 0;
            }
        }
    }


    return returnVal;
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

    while (startRecord != NULL)
    {
        printf("%d\n", (*startRecord).accountno);
        printf("%s\n", (*startRecord).name);
        printf("%s\n", (*startRecord).address);

        startRecord = (*startRecord).next;
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
    int returnVal = -1;
    struct record* cursor = record;

    if (debugMode == 1)
    {
        printf("called findRecord(int %d)\n", accountNumber);
    }

    if (record == NULL)
    {
        returnVal = -1;
    }
    else
    {
        while ((*cursor).next != NULL && accountNumber != (*cursor).accountno)
        {
            if (debugMode == 1)
            {
                printf("executed loop\n");
            }
            cursor = (*cursor).next;
        }
        if ((*cursor).accountno == accountNumber)
        {
        printf("%d\n", (*cursor).accountno);
        printf("%s\n", (*cursor).name);
        printf("%s\n", (*cursor).address);
        returnVal = 0;
        }
        else
        {
            returnVal = -1;
        }

    }

    return returnVal;
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
    int returnVal = -1;
    struct record *cursor, *follower;    

    if (debugMode == 1)
    {
        printf("called deleteRecord(int %d)\n", accountNumber);
    }

    if ((*startRecord) == NULL)
    {
        /*do nothing, empty list.*/
    }
    else
    {
        cursor = (*startRecord);
        follower = NULL;

        while ((*cursor).accountno != accountNumber && (*cursor).next != NULL)
        {
            follower = cursor;
            cursor = (*cursor).next;
        }
        
        if ((*cursor).accountno != accountNumber)
        {
            /*do nothing, record not in list*/
        }
        else
        {
            if (follower == NULL)
            {
                if ((*cursor).next == NULL)
                {
                    (*startRecord) = NULL;
                    free(cursor);
                    returnVal = 0;
                }
                else
                {
                    *startRecord = (*cursor).next;
                    free(cursor);
                    returnVal = 0;
                }
            }
            else /*follower is not NULL*/
            {
                if ((*cursor).next == NULL)
                {
                    (*follower).next = NULL;
                    free(cursor);
                    returnVal = 0;
                }
                else
                {
                    (*follower).next = (*cursor).next;
                    free(cursor);
                    returnVal = 0;
                }
            }
        }

    }

    return returnVal;
}

/*****************************************************************
//  Function name:  writeFile
//
//  Description:    Writes a text file representing the contents
//                  of the database.
//
//  Parameters:     start (struct record*) : a pointer to the first
//                  record in the linked list of records
//
//                  fileName (char[]) : a string containing the 
//                  desired name of the file to write
//
//  Return Values:  0 : completed with no issues
//                  -1 : failed to complete
//
******************************************************************/
int writeFile(struct record* start, char fileName[])
{
    FILE* pointer;
    int returnVal;

    pointer = fopen(fileName, "w");

    if (pointer == NULL)
    {
        returnVal = -1;
    }
    else
    {
        while (start != NULL)
        {
            fprintf(pointer, "%d\n", (*start).accountno);
            fprintf(pointer, "%s\n", (*start).name);
            fprintf(pointer, "%s", (*start).address);
            fprintf(pointer, "%c", '%');

            start = (*start).next;
        }
        returnVal = 0;
    }

    return returnVal;
}

/******************************************************************
//  Function Name:  readFile
//
//  Description:    Reads a prewritten text file to fill the database
//
//  Parameters:     startP (struct record**) : a pointer which points
//                  to start
//
//                  fileName (char[]) : the name of the input file
//
//  Return Values:  0 : completed with no issues
//                  -1 : failed to complete
//
*******************************************************************/
int readFile(struct record** startP, char filename[])
{
    FILE* pointer;
    int accountno, returnVal, placeInString = 0;
    char check, addChar, name[25], dummy[25], address[45], trash[80];

    pointer = fopen(filename, "r");

    if (pointer == NULL)
    {
        returnVal = -1;
    }
    else
    {
        check = fgetc(pointer);
        
        while (check != EOF)
        {
            ungetc(check, pointer);
            fscanf(pointer, "%d", &accountno);
            fgets(trash, 80, pointer);
            fgets(dummy, 25, pointer);

            strncpy(name, dummy, strlen(dummy) - 1);
            /*removes a new line from the end of name*/
            
            addChar = fgetc(pointer);
            while (addChar != '%' && placeInString < 44)
            {
                address[placeInString] = addChar;
                address[placeInString + 1] = '\0';
                placeInString++;
                addChar = fgetc(pointer);
            }
            address[44] = '\0';
            placeInString = 0;
            check = fgetc(pointer);
            addRecord(startP, accountno, name, address);
        }
        returnVal = 0;
    }
    return returnVal;
}

/*******************************************************************
//  Function Name:  cleanup
//
//  Description:    Deletes the linked list and frees up memory 
//
//  Parameters:     startP (struct record**) : a pointer which points
//                  to start
//
//  Return Values:  None
//
*********************************************************************/
void cleanup(struct record** startP)
{
    struct record* cursor, *follower;
    
    cursor = *startP;

    while ((*cursor).next != NULL)
    {
       follower = cursor;
       cursor = (*cursor).next;
       free(follower);
    }
    free(cursor);
    /*startP still points to something, but that will be taken care of
 * when the main function closes*/ 
}  
