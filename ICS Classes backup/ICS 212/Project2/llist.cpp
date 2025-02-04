/*****************************************************************
//  NAME:        Alan Reeves
//
//  HOMEWORK:    Project 2
//
//  CLASS:       ICS 212
//
//  INSTRUCTOR:  Ravi Narayan
//
//  DATE:        November  30, 2024
//
//  FILE:        llist.cpp
//
//  DESCRIPTION:
//  This file contains the llist class methods.
//
****************************************************************/

#include"record.h"
#include"llist.h"
#include<iostream>
#include<fstream>
#include<string.h>

using namespace std;

/*****************************************************************
//
//  Function name: llist()
//
//  Description:   Default constructor with no parameters. Calls
//                 readfile and uses a default filename "database.dat"
//
//  Parameters:    none
//
//  Return Values: none
//
******************************************************************/
llist::llist()
{
    #ifdef DEBUGMODE
    cout << "default constructor called" << endl;
    #endif

    start = NULL;

    strcpy(filename, "database.dat");
    readfile();
}

/*****************************************************************
//
//  Function name: llist(char[])
//
//  Description:   Constructor which allows a specific filename to
//                 be used when calling readfile.
//
//  Parameters:    desiredName (char[]) : a string containing the desired
//                 new filename
//
//  Return Values: none
//
********************************************************************/
llist::llist(char desiredName[20])
{
    int readResult;

    #ifdef DEBUGMODE
    cout << "arbitrary filename constructor called" << endl;
    #endif

    strcpy(filename, desiredName);
    readResult = readfile();

    if (readResult == -1)
    {
        start = NULL;
    }
}

/*********************************************************************
//
//  Function name: llist copy constructor
//
//  Description:   Constructor which makes a new llist as a copy of an
//                 existing instance
//
//  Parameters:    baseList (llist) : the list to make a copy of
//
//  Return Values: none
//
*********************************************************************/
llist::llist(llist& baseList)
{
    struct record *cursor;
    int accountno, i = 0;
    char name[25], address[45];

    #ifdef DEBUGMODE
    cout << "copy constructor called" << endl;
    #endif

    start = NULL;
    
    //copy filename
    strcpy(this->filename, baseList.filename);

    //read through baseList
    //copy only record information, not the next field
    cursor = baseList.start;
    while (cursor != NULL)
    {
        if (i == 0)
        {
            //for first record made
            start = new struct record;

            this->start->accountno = baseList.start->accountno;
            strcpy(this->start->name, baseList.start->name);
            strcpy(this->start->address, baseList.start->address);
            this->start->next = NULL;
            i++;
            cursor = (*cursor).next;
        }
        else
        {
            //copy information in cursor
            accountno = cursor->accountno;
            strcpy(name, cursor->name);
            strcpy(address, cursor->address);
            //call add record in this with record info
            this->addRecord(accountno, name, address);
            //loop until cursor reaches end
        
            //assign start to the first record made

            cursor = (*cursor).next;
        }
        
    }

    
}


/*********************************************************************
//
//  Function name: ~llist()
//
//  Description:   default destructor. Frees up memory occupied by the
//                 database records and writes the database to a file.
//
//  Parameters:    none
//
//  Return Values: none
//
**********************************************************************/
llist::~llist()
{
    #ifdef DEBUGMODE
    cout << "destructor called" << endl;
    #endif

    writefile();
    cleanup();
}

/*********************************************************************
//
//  Function name: = operator
//
//  Description:   overloaded to make the list on the left a copy
//                 of the one on the right
//
//  Parameters:    baseList (const llist&) : the list to copy from
//
//  Return Values: none
//
*********************************************************************/
llist& llist::operator=(const llist& baseList)
{
    this->cleanup();
    
    struct record *cursor;
    int accountno, i = 0;
    char name[25], address[45];

    #ifdef DEBUGMODE
    cout << "overloaded equals operator called" << endl;
    #endif

    strcpy(this->filename, baseList.filename);

    cursor = baseList.start;
    while (cursor != NULL)
    {
        if (i == 0)
        {
            start = new struct record;

            this->start->accountno = baseList.start->accountno;
            strcpy(this->start->name, baseList.start->name);
            strcpy(this->start->address, baseList.start->address);
            this->start->next = NULL;
            i++;
            cursor = (*cursor).next;
        }
        else
        {
            accountno = cursor->accountno;
            strcpy(name, cursor->name);
            strcpy(address, cursor->address);
            this->addRecord(accountno, name, address);
            cursor = (*cursor).next;
        }

    }

    return *this;

}
/*********************************************************************
//
//  Function name:  addRecord
//
//  Description:    Adds a record to the database
//
//  Parameters:     acctNum (int) : The account number for the new record
//                  name (char[]) : The account holder's name
//                  address (char[]) : The account holder's address
//
//  Return values:  0 : record added without issue
//                  -1 : failed to add record
//
**********************************************************************/
int llist::addRecord(int acctNum, char name[25], char address[45])
{
    struct record* newRecordP, *cursor, *follower;
    int returnVal = -1;

    newRecordP = new struct record;
    

    #ifdef DEBUGMODE
    printf("called addRecord(int %d, char[] %s, char[] %s)\n", acctNum, name, address);
    #endif 

    (*newRecordP).accountno = acctNum;
    strcpy((*newRecordP).name, name);
    strcpy((*newRecordP).address, address);

    if (start == NULL)
    {
        start = newRecordP;
        (*newRecordP).next = NULL;
        returnVal = 0;
    }
    else
    {
        if ((*newRecordP).accountno < (*start).accountno)
        {
            (*newRecordP).next = (start);
            start = newRecordP;
            returnVal = 0;
        }
        else
        {
            cursor = start;
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

/*************************************************************
//  Function name:  printAllRecords
//
//  Description:    Prints all records currently in the database.
//
//  Parameters:     none
//
//  Return Values:  none
//
**************************************************************/
void llist::printAllRecords()
{
    struct record* cursor = start;

    #ifdef DEBUGMODE
    cout << "called printAllRecords()\n";
    #endif

    if (cursor == NULL)
    {
        cout << "No records found\n";
    }
    
    while (cursor != NULL)
    {
        cout << (*cursor).accountno;
        cout << endl;
        cout << (*cursor).name;
        cout << (*cursor).address;
        cout << endl;

        cursor = (*cursor).next;
    }
    
}

/*********************************************************************
//
//  Function name: findRecord
//
//  Description:   This method finds a record by account number,
//                 and prints its information to the terminal.
//
//  Parameters:    acctNum (int) : the account number to find
//
//  Return values: -1 : failure
//                 0 : success
//
*********************************************************************/
int llist::findRecord(int acctNum)
{
    int returnVal = -1;
    struct record* cursor = start;

    #ifdef DEBUGMODE
    cout << "called findRecord(int " << acctNum << ")" << endl;
    #endif

    if (start == NULL)
    {
        returnVal = -1;
    }
    else
    {
        while ((*cursor).next != NULL && acctNum != (*cursor).accountno)
        {
            #ifdef DEUGMODE
            cout << "executed loop\n";
            #endif
            
            cursor = (*cursor).next;
        }
        if (((*cursor).accountno == acctNum))
        {
            cout << (*cursor).accountno;
            cout << endl;
            cout << (*cursor).name;
            cout << (*cursor).address;
            cout << endl;

            returnVal = 0;
        }
    }
    return returnVal;
}
/**********************************************************************
//
//  Function name: deleteRecord
//
//  Description:   This method deletes a record of a given account number.
//
//  Parameters:    acctNum (int) : the account number to delete
//
//  Return Values: 0 : record deleted without issue
//                 -1: record not found
//
*********************************************************************/
int llist::deleteRecord(int acctNum)
{
    int returnVal = -1;
    struct record *cursor = start, *follower = NULL;
    
    #ifdef DEBUGMODE
    cout << "called deleteRecord( int ";
    cout << acctNum << ")" << endl;
    #endif

    if (cursor == NULL)
    {
        //do nothing
    }
    else
    {
        while ((*cursor).accountno != acctNum && (*cursor).next != NULL)
        {
            follower = cursor;
            cursor = (*cursor).next;
        }

        if ((*cursor).accountno != acctNum)
        {
            //do nothing, record not in list.
        }
        else
        {
            if (follower == NULL)
            {
                if ((*cursor).next == NULL)
                {
                    start = NULL;
                    delete cursor;
                    returnVal = 0;
                }
                else
                {
                    start = (*cursor).next;
                    delete cursor;
                    returnVal = 0;
                }
            }
            else //follower is not NULL
            {
                if ((*cursor).next == NULL)
                {
                    (*follower).next = NULL;
                    delete cursor;
                    returnVal = 0;
                }
                else
                {
                    (*follower).next = (*cursor).next;
                    delete cursor;
                    returnVal = 0;
                }
            }
        }
    }

    return returnVal;
}
/*******************************************************************
//
//  Function Name: readfile
//
//  Description:   Reads a prewritten test file to fill the database
//
//  Parameters:    none
//
//  Return Values: 0 : completed with no issues
//                 -1: failed to complete
//
*******************************************************************/
int llist::readfile()
{
    ifstream file;
    int accountno, returnVal;
    char name[25], address[45];

    #ifdef DEBUGMODE
    cout << "readfile called" << endl;
    #endif

    file.open(filename, ios_base::in);

    if (!file.is_open())
    {
        returnVal = -1;
    }
    else
    {
        //while not at EOF
        //read account number
        while (file.peek() != EOF)
        {
            //read accountno
            file >> accountno;
            //read name
            file.getline(name, 24, '\n');
            //read address
            file.getline(address, 44, '%');
            //add a record with this information
            addRecord(accountno, name, address);
        }
        returnVal = 0;
    }

    if (file.is_open())
    {
        file.close();
    }

    return returnVal;
}
/*****************************************************************
//
//  Fuction Name: writeFile
//
//  Description:  writes the contents of the database to a text file
//
//  Parameters:   none
//
//  Return Values: 0 : completed with no issues
//                 -1: failed to complete
//
*****************************************************************/
int llist::writefile()
{
    ofstream file;
    int returnVal;
    struct record *cursor = start;

    #ifdef DEBUGMODE
    cout << "called writefile" << endl;
    #endif

    file.open(filename);

    if (!file.is_open())
    {
        returnVal = -1;
    }
    else
    {
        while (cursor != NULL)
        {
            file << (*cursor).accountno;
            file << endl;
            file << (*cursor).name;
            
            file << (*cursor).address;
            
            file << "%";

            cursor = (*cursor).next;
        }
        returnVal = 0;
    }

    if (file.is_open())
    {
        file.close();
    }

    return returnVal;
}
/****************************************************************
//
//  Function name: cleanup
//
//  Description:   deletes the linked list and frees up memory
//
//  Parameters:    none
//
//  Return Values: none
//
*****************************************************************/
void llist::cleanup()
{
    struct record *cursor, *follower;

    #ifdef DEBUGMODE
    cout << "called cleanup" << endl;
    #endif
    
    cursor = start;

    while (cursor != NULL)
    {
        follower = cursor;
        cursor = (*cursor).next;
        delete follower;
    }
    start = NULL;
}

