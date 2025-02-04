/**********************************************************
// Name:        Alan Reeves
//
// Homework:    project 2
//
// Instructor:  Ravi Narayan
//
// Date:        30 Nov 2024
//
// File:        llist.h
//
// Description:
// This header contains the class outline for the llist class,
// which is a linked list of records for a database.
//
**************************************************************/
#ifndef _LLIST_
#define _LLIST_

#include<iostream>

class llist
{
    private:
        record*        start;
        char           filename[20];
        int            readfile();
        int            writefile();
        void           cleanup();

    public:
        llist();
        llist(char[]);
        llist(llist&);
        ~llist();
        int addRecord(int, char[], char[]);
        int findRecord(int);
        void printAllRecords();
        int deleteRecord(int);
        llist& operator=(const llist&);
        friend std::ostream& operator<<(std::ostream&, const llist&);
};

#endif
