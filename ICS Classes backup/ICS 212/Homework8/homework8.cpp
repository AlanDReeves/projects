/*****************************************************************
//
//  NAME:        Alan Reeves
//
//  HOMEWORK:    8
//
//  CLASS:       ICS 212
//
//  INSTRUCTOR:  Ravi Narayan
//
//  DATE:        November 11, 2024
//
//  FILE:        homework8.cpp
//
//  DESCRIPTION:
//   This file contains code to ask the user for a number and
//   then prints every number before that,and if the number is
//   a multiple of 3.
//
*****************************************************************/

#include<iostream>
#include<iomanip>
#include<limits>

using namespace std;

int user_interface();
int is_multiple3(int, int&);
void print_table(int);


/*****************************************************************
//
//  Function name: user_interface
//
//  DESCRIPTION:   interfaces with the user to get their
//                 desired number
//
//  Parameters:    none
//
//  Return values: int user_input: some positive integer 
//                 taken from the user.
//
****************************************************************/

int user_interface()
{
    int user_input;
    bool cinState = false;

    user_input = -1;

    cout << "This program accepts a positive integer and prints ";
    cout << "a table showing every prior integer and if it is ";
    cout << "divisble by 3.\n";
    cout << "\nEnter maximum number to show: ";
    cin >> user_input;

    while (user_input <= 0)
    {
        cinState = cin;
        if (cinState == 0)
        {
            std::cin.clear();
            std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
        }
        cout << "\nInvalid input. Please enter a POSITIVE integer. \n";
        cin >> user_input;
        cinState = cin;
    }

    return user_input;  

}

/****************************************************************
//  Function name: is_multiple3
//
//  DESCRIPTION:   This function determines if the int given is
//                 divisible by 3. Returns a false possitive for
//                 number 0.
//
//  Parameters:    num (int&): a reference to a number to check for divisibility
//                 dummy (int) : a regular int was required by the
//                     assignment page, but I could not find a purpose for it.
//
//  Return values:  1 : the number is divisble by 3.
//                  0 : the number is not divisble by 3.
//
****************************************************************/

int is_multiple3(int dummy, int& num) 
{
    int return_val;

    return_val = 0;
    if (num % 3 == 0)
    {
        return_val = 1;
    }
    
    return return_val;
}

/****************************************************************
//  Function name: print_table
//
//  DESCRIPTION:   This function prints a table with every number
//                 lower than the one entered and states if the
//                 number is divisible by 3.
//
//  Parameters:    user_num (int): the maximum number to show
//
//  Return values: none
//
*****************************************************************/

void print_table(int user_num)
{
    int i;

    cout << setw(8) << "Number";
    cout << setw(16) << "Multiple of 3\n";

    for (i = 0; i <= user_num; i++)
    {
        cout << setw(8) << i;

        if (i == 0)
        {
            cout << setw(5) << " NO\n";
        }

        else  if (is_multiple3(i, i) == 0)
        {
            cout << setw(5) << " NO\n";
        }
        else 
        {
            cout << setw(5) << "YES\n";
        }
    }
    cout << endl;
}

/*****************************************************************
//
//  Function name: main
//
//  DESCRIPTION:   A function which calls user_interface() and
//                 print_table(), which itself calls
//                 the is_multiple3() function.
//
//  Parameters:    int argc: not used.
//                 char* argv[]: not used.
//
//  Return values:  1 : main concluded successfully.
//
****************************************************************/

int main(int argc, char* argv[])
{
    int user_num;

    user_num = user_interface();
    print_table(user_num);
    
    return 1;
}
