/*****************************************************************
//
//  NAME:        Alan Reeves
//
//  HOMEWORK:    project1
//
//  CLASS:       ICS 212
//
//  INSTRUCTOR:  Ravi Narayan
//
//  DATE:        31 Oct, 2024
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
int readFile(struct record **, char[]);
int writeFile(struct record *, char[]);
void cleanup(struct record**);


