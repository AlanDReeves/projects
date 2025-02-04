#include <iostream>
#include <iomanip>
#include <limits>

using namespace std;

int main()
{
    int i;
    bool cinState = false;

    cout << "Enter an integer\n";
    cin >> i;

    cinState = cin;

    while (cinState != 1)
    {
        std::cin.clear();
        // Ignore the invalid input in the buffer
        std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
        cout << "bad input\n";
        cin >> i;
        cinState = cin;
    }

    //cout << setw(8) << "Number";
    //cout << setw(16) << "Divisible by 3\n";

    //while (i > 0)
    //{
        //cout << setw(8) << i;
        //if (i % 3 == 0)
        //{
            //cout << setw(16) << "YES\n";
        //}
        //else
        //{
            //cout << setw(16) << "NO\n";
        //}
        //i = i - 1;
    //}

}
