package lab1;
import java.util.*;
import edu.princeton.cs.algs4.StdOut;
public class lab12 {
    //iteration 
    private class Node {
        char ch;//node som innhålla char del och pekare next del 
        Node next;
    }
    Node first ;//skapa node 
    public void push(char ch){
        Node last = first;//skapa en node last som är lik med first 
        first = new Node();//gör first en ny node 
        first.ch = ch; 
        first.next = last; //first pekar till last. 
    }
    public void pop(){//pop sista char 
        Node cha = first; 
        while( cha != null ){
            StdOut.print(cha.ch); 
            cha = cha.next;
        }
    }   
    
    //recursion
    public static StringBuffer minsk(StringBuffer sb2)//skapa en metod som kan ta bort sista character varje gång
    {
        StringBuffer sb= new StringBuffer(sb2);  
        return sb.deleteCharAt(sb.length()-1);
    }
    public static void printlast (StringBuffer sb)
    {
        if(sb.length() != 0) // om längden inte är 0, print den character
        {
            char ch = sb.charAt(sb.length()-1);
            StdOut.print(ch);
        }
    }
    public static void recursion (StringBuffer sb)                  
    { 
        printlast(sb);//skriva ut den sista char först
        if(sb.length() != 0)//om längden inte är 0 
        {
            sb = minsk(sb);//ta bort den sista character in stringbuffer
            recursion(sb);
        }
    }

    public static void main (String[] args)
    {   
        Scanner in = new Scanner (System.in);
        String st = in.next();// mata in en string
        StringBuffer sb= new StringBuffer(st);// omvandlar string till stringbuffer
        StdOut.print("For iteration : ");
        //iteration 
        lab12 a = new lab12();
        for( int i = 0; i < st.length(); i ++)
            a.push(st.charAt(i));
        a.pop();

        //recursion 
        StdOut.println();
        StdOut.print("For recursion : ");
        recursion(sb); 
        in.close();//slut att mata in 
    }  
}
