/*****************************************************************
//
//  NAME:        Ravi Narayan
//
//  HOMEWORK:    5
//
//  CLASS:       ICS 212
//
//  INSTRUCTOR:  Ravi Narayan
//
//  DATE:        October 7, 2024
//
//  FILE:        driver.c
//
//  DESCRIPTION:
//   This is a driver file to test iofunctions.c
//
****************************************************************/

#include<stdio.h>
#include<string.h>
#include"pokemon.h"
#include"iofunctions.h"

int normalCheck();
int overwriteCheck();
int emptyFileCheck();
int fileTooLongCheck();
int fileTooSmallCheck();

/*****************************************************************
//
//  Function name: main
//
//  DESCRIPTION:   This method tests writefile and readfile by calling
//                 other methods.
//
//  Parameters:    argc (int) : The number of elements in argv (not used)
//                 argv (char*[]) : An array of arguments passed
//                                  to the program. (not used)
//
//  Return values:  0 : no meaning
//
****************************************************************/

int main(int argc, char* argv[])
{
    printf("\n\n\nStarting driver check of readfile and writefile\n\n\n");

    printf("normalCheck()\n");
    normalCheck();

    printf("\noverwriteCheck()\n");
    overwriteCheck();

    printf("\nfileTooLongCheck\n");
    fileTooLongCheck();

    printf("\nfileTooSmallCheck\n");
    fileTooSmallCheck();

    printf("\nemptyFileCheck()\n");
    emptyFileCheck();


    return 0; 
}

/**********************************************************************
//
//  Function name:  normalCheck
//
//  DESCRIPTION:    makes an array containing 3 pokemon, writes a file
//                  for the array, and then reads the results.
//                  filename is pokemon.txt and is used in other functions
//
//  Parameters      none
//
//  return values:  0 : readfile and writefile reported success
//                  -1 : readfile or writefile reported failure
//
**********************************************************************/

int normalCheck()
{
    struct pokemon  pidgeot, butterfree, magmar, pokearray[3];
    struct pokemon emptyArray[3];
    int emptyArraySize = 3, i = 0, readVal, writeVal;

    pidgeot.level = 36;
    strcpy(pidgeot.name, "pidgeot");

    butterfree.level = 30;
    strcpy(butterfree.name, "butterfree");

    magmar.level = 32;
    strcpy(magmar.name, "magmar");

    pokearray[0] = pidgeot;
    pokearray[1] = butterfree;
    pokearray[2] = magmar;

    printf("writing pokemon.txt  with the following data: \n");
    printf("lvl 36 pidgeot, lvl 30 butterfree, lvl 32 magmar \n");
    writeVal = writefile(pokearray, 3, "pokemon.txt");

    printf("reading pokemon.txt\n");
    readVal = readfile(emptyArray, &emptyArraySize, "pokemon.txt");

    for (i = 0; i < emptyArraySize; i++)
    {
        printf("%d\n", emptyArray[i].level);
        printf("%s\n", emptyArray[i].name);
    }
    printf("end of contents\n");

    return (writeVal & readVal);
}

/*********************************************************************
//
//  Function name:  overwriteCheck
//
//  DESCRIPTION:    attempts to overwrite pokemon.txt from normalCheck
//
//  Parameters:     none
//
//  return values:  0: readfile and writefile reported success
//                  -1: readfile or writefile reported failure
//
**********************************************************************/

int overwriteCheck()
{
    struct pokemon  weedle, pokearray[4];
    struct pokemon emptyArray[4];
    int emptyArraySize = 4, i = 0, readVal, writeVal;

    weedle.level = 3;
    strcpy(weedle.name, "weedle");

    pokearray[0] = weedle;
    pokearray[1] = weedle;
    pokearray[2] = weedle;
    pokearray[3] = weedle;

    printf("overwriting pokemon.txt with 4 identical lvl 3 weedle\n");

    writeVal = writefile(pokearray, 4, "pokemon.txt");

    printf("reading contents of pokemon.txt\n");
    readVal = readfile(emptyArray, &emptyArraySize, "pokemon.txt");

    for (i = 0; i < emptyArraySize; i++)
    {
        printf("%d\n", emptyArray[i].level);
        printf("%s\n", emptyArray[i].name);
    }
    printf("end of contents\n");

    return (writeVal & readVal);
}

/*********************************************************************
//
//  Function name:  fileTooLongCheck
//
//  DESCRIPTION:    reads a file which is known to be bigger than 
//                  the struct pokemon array. Uses pokemon.txt again
//
//  Parameters:     none
//
//  return values:  0: readfile reported success
//                  -1: readfile reported failure
//
**********************************************************************/

int fileTooLongCheck()
{
    struct pokemon pokearray[2];
    int i = 0, pokearraySize = 2, readVal;
    
    printf("reading from pokemon.txt to array of size 2\n");
    readVal = readfile(pokearray, &pokearraySize, "pokemon.txt");

    for (i = 0; i < pokearraySize; i++)
    {
        printf("%d\n", pokearray[i].level);
        printf("%s\n", pokearray[i].name);
    }

    printf("end of contents\n");

    printf("%s %d\n", "new array size value is:", pokearraySize);

    return readVal;
}

/**********************************************************************
//
//  Function name:  fileTooSmallCheck
//
//  DESCRIPTION:    reads a file which is known to be smaller than the
//                  size of the array.
//
//  Parameters:     none
//
//  return values:  0: readfile reported success
//                  -1: readfile reported faillure
//
***********************************************************************/

int fileTooSmallCheck()
{
    struct pokemon pokearray[5], caterpie;
    int i = 0, pokearraySize = 5, readval;

    caterpie.level = 6;
    strcpy(caterpie.name, "caterpie");

    for (i = 0; i < pokearraySize; i++)
    {
        pokearray[i] = caterpie; 
    }
    printf("pokearray pre-loaded with 5 level 6 caterpie.\n");

    printf("current contents of array:\n");

    for (i = 0; i < pokearraySize; i++)
    {
        printf("%d\n", pokearray[i].level);
        printf("%s\n", pokearray[i].name);
    }

    readval = readfile(pokearray, &pokearraySize, "pokemon.txt");
    printf("calling readfile with pokearray and pre-existing pokemon.txt\n");
    printf("pokemon.txt contains 4 level 3 weedle.\n\n");

    printf("%s %d\n", "Pokearray size is now:", pokearraySize);

    printf("new contents of array:\n");
    for (i = 0; i < pokearraySize; i++)
    {
        printf("%d\n", pokearray[i].level);
        printf("%s\n", pokearray[i].name);
    }
    printf("Should be 4 level 3 weedle\n");

    return readval;
}

/**********************************************************************
//
//  Function name:  emptyFileCheck
//
//  DESCRIPTION:    calls readfile on 0000.txt, which should not exist.
//
//  Parameters:     none
//
//  return values:  0 : readfile reported success
//                 -1 : readfile reported failure
//
***********************************************************************/

int emptyFileCheck()
{
    int  readVal, dummyArraySize = 10;
    struct pokemon dummyArray[10];
    readVal = readfile(dummyArray, &dummyArraySize, "0000.txt");
    printf("readfile called on 0000.txt.\n");
    printf("readfile returned: ");
    printf("%d\n", readVal);
    printf("should be -1\n");

    printf("%s %d\n", "New array size is:", dummyArraySize);
    printf("10 indicates that it is unchanged (intended)\n");
    
    return readVal;
}
