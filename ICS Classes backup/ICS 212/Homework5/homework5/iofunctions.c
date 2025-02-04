/*****************************************************************
//
//  NAME:        Alan Reeves
//
//  HOMEWORK:    5
//
//  CLASS:       ICS 212
//
//  INSTRUCTOR:  Ravi Narayan
//
//  DATE:        October 7, 2024
//
//  FILE:        iofunctions.c
//
//  DESCRIPTION:
//   This file contains the custom readfile and writefile functions
//
****************************************************************/

#include<string.h>
#include<stdio.h>
#include"pokemon.h"
#include"iofunctions.h"

/*****************************************************************
//
//  Function name: writefile
//
//  DESCRIPTION:   This function makes a .txt file representing the
//                 struct pokemon info in an array passed to it.
//
//  Parameters:    struct pokemon pokearray[] : an array containing
//                                  pokemon structures.
//
//                 int num : an integer representing the size of 
//                                  the array above.
//                 
//                 char : filename[] : a string containing the filename
//                                  of the file to write
//
//  Return values:  0 : completed with no issue
//                  -1 : something went wrong
//
****************************************************************/

int writefile(struct pokemon pokearray[], int num, char filename[])
{
    FILE* pointer;
    int i, returnVal;

    pointer = fopen(filename, "w");
    if (pointer == NULL)
    {
        returnVal = -1;
    }

    else
    {
        for (i = 0; i < num; i++)
        {
            fprintf(pointer, "%d\n", pokearray[i].level);
            fprintf(pointer, "%s\n", pokearray[i].name);
        }
        fclose(pointer);
        returnVal = 0;
    }

    return returnVal;
}

/*****************************************************************
//
//  Function name: readfile
//
//  DESCRIPTION:   This function reads pokemon data off a txt file
//                 and adds it to a given array up to the limit of
//                 the array's size.
//
//  Parameters:    struct pokemon pokearray[] : an array containing
//                                  pokemon structures.
//
//                 int*  num : a pointer to an integer representing 
//                                  the size of the array above.
//
//                 char : filename[] : a string containing the filename
//                                  of the file to read
//
//  Return values:  0 : completed with no issue
//                  -1 : something went wrong
// ****************************************************************/

int readfile(struct pokemon pokearray[], int* num, char filename[])
{
    FILE* pointer;
    int i = 0, level = 0, returnVal;
    char name[25], trash[80], c; 


    pointer = fopen(filename, "r");
    if (pointer == NULL)
    {
        returnVal = -1;
    }
    
    else
    {
        c = fgetc(pointer);

        while (i < *num && c != EOF)
        {
            ungetc(c, pointer);
            fscanf(pointer, "%d", &level);
            fgets(trash,80, pointer);
            fgets(name, 25, pointer);
            pokearray[i].level = level;
            strcpy(pokearray[i].name, name);
            i = i + 1;
            c = fgetc(pointer);
        }
    *num = i;
    returnVal = 0;
    fclose(pointer);
    }

    return returnVal;
}
