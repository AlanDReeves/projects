/*****************************************************************
//  NAME:        Alan Reeves 
//
//  HOMEWORK:    project2
// 
//  CLASS:       ICS 212
//  
//  INSTRUCTOR:  Ravi Narayan
//
//  DATE:        November 30, 2024
//
//  FILE:        record.h
//  
//  DESCRIPTION:
//  This header contains statements to create the structure of a 
//  record for a datebase. 
//
//  ****************************************************************/
#ifndef _RECORD_
#define _RECORD_

struct record
{
    int                accountno;
    char               name[25];
    char               address[45];
    struct record*     next;
};

#endif
