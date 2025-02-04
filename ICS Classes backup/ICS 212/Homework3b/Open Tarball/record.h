/*****************************************************************
//  NAME:        Alan Reeves 
//
//  HOMEWORK:    3b
// 
//  CLASS:       ICS 212
//  
//  INSTRUCTOR:  Ravi Narayan
//
//  DATE:        Sep 25, 2024
//
//  FILE:        record.h
//  
//  DESCRIPTION:
//  This header contains statements to create the structure of a 
//  record for a datebase. 
//
//  ****************************************************************/
    
struct record
{
    int                accountno;
    char               name[25];
    char               address[45];
    struct record*     next;
};


