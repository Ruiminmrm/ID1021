#include <stdio.h>
//print array
void print(int a[],int size)
{
    for (int i = 0 ; i < size ; i++)
        printf("[ %d ]" , a[i]);
}

void negative (int a[], int size)
{
    int temp ;
    int exchange = 0;
    for (int i = 0 ; i < size ; i++)//gå genom alla element i array 
    {
        if(a[i] < 0)// om a[i] <= 0
        {
            //byte plats med a[exchange] , exchange börjar med index 0
            temp = a[i];
            a[i] = a[exchange];
            a[exchange] = temp;
            exchange++;//a[exchange] blir en negativ tal nu, exchange++ till next index 
        }
    }
    print(a,size);
}
int main()
{
    int size;
    printf("\n Give size of the array :");
    scanf("%d" , &size);
    
    printf("\n Enter the array element :");
    int arr[size];
    for (int i = 0 ; i < size ; i++)
        scanf("%d" , &arr[i]);
    negative(arr , size);
    
}
/*
 Give size of the array :5

 Enter the array element :-1
5
9
-4
-9
[ -1 ][ -4 ][ -9 ][ 5 ][ 9 ]
    1. How you calculate its time complexity?
    - T(n) = O(n) 
    loop through the array once .
*/