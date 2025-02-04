/*****************************************************************
//  NAME:        Alan Reeves 
//
//  HOMEWORK:    3b, project1
// 
//  CLASS:       ICS 212
//  
//  INSTRUCTOR:  Ravi Narayan
//
//  DATE:        31 Oct, 2024
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


