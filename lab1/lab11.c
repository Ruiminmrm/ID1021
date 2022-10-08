#include <stdio.h>
void recursive ()
{
    char ch ; 
    if ((ch = getchar())!= '\n')// använda getchar() att get nästa character 
                                // om det char är new line character ('\n'),slutar där 
        recursive();// om det char inte är new line character ('\n') ,försätter get char
    putchar(ch); // visa upp character 
}
void iterative()
{
    int maxlen = 5; // antag att max length av input är 5
    char ch[maxlen];//Skapa ett char array 
    int i = 0;
    while(((ch[i] = getchar()) != '\n'))// index börjar från 0 till maxlen-1 , så vi sätt en kondition till i<maxlen
                                        // samt vi behöver sätta den andra kondition som gör att coden läser alla char innan newline '\n'  
            if( i < maxlen )
                i++;//läser vidare till nästa index som inte är '\n'                                             
    for(int j = i ; j >= 0 ; j--)// i är redan till den sista index som uppfyll alla krav
                                 // vi sätt parameter j = i
        putchar(ch[j]);//a[j] är den sista character och j-- betyder att det kör framemot till index 0
}
int main ()
{
    recursive();
    iterative();
}
/*  Question : 1. Show and explain the code from assignment 1. 
               -    Klart!
               
               2. Is it easier to implement this function recursively or iteratively? 
               -    Iteration är enklare att implemntera.Det kostar mindre minne.
                   
               3. Are there other pros/cons of the two approaches?
               -    Recursion pros :
                        a.minska tidskomplexiteten
                        b.minska tid att skriva coden
                    Recursion cons :
                        a.använda mer minne 
                        b.ta mer tid att köra
                    Iteration pros : 
                        a.använda mindre miinne 
                        b.ta mindre tid 
                    Iteration cons : 
                        a.mer tidkomplexiteten
                        b.mer tid att skriva coden
*/                  

