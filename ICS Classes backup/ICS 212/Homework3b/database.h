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
//  FILE:        database.h
//
//  DESCRIPTION:
//  This file contains method prototypes for database.c
//
****************************************************************/

int addRecord (struct record **, int, char [ ],char [ ]);
void printAllRecords(struct record *);
int findRecord (struct record *, int);
int deleteRecord(struct record **, int);


