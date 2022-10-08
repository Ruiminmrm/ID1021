package lab1;
import java.util.Scanner;
import edu.princeton.cs.algs4.StdOut;
public class lab16{
    private Node first; 
    private Node last ; 

    private class Node 
    {
        private int item;
        private Node next;
        private Node prev;
    }
    public boolean isEmpty()
    {
        return first == null;
    }
    public void printout()
    {
        if(isEmpty()){
            StdOut.println();
        }
        else
        {
            Node f = first;
            while(f.next != null)//när n.next = first ----> 1. first = last  2. n = last
            {                    // print alla n != last 
                StdOut.print("[" + f.item + "]" + ",");
                f = f.next;//till sista n = last och n inte uppfyll krav för while loop
            }
            StdOut.println("[" + f.item + "]" + ",");//för att print last 
        }
    }
    public void sort(int item)//vi dealr till 4 olika situationer.
    {
        Node no = new Node();//skapa en node för att placera den item
        no.item = item;
        Node f = first;
        if(isEmpty())//om det är tomt, så first = last = no;
        {
            first= no;
            last = no; 
            printout();
        }
        else if( first.item >= no.item )//om first >= input
        {
            no.next = first;
            first.prev = no;
            first = no;
            printout();
        }
        else if( last.item <= no.item )// om last <= input
        { 
            last.next = no;
            no.prev = last;
            last = no ;
            printout();
        }
        else 
        {
            while( f.item < no.item && f.next != null )//om f < input  samt f inte är den sista node
                f = f.next;//iterera tills att f > no
            no.next = f;
            no.prev = f.prev;
            f.prev.next = no;
            f.prev = no;
            printout();
        }
    }
    public static void main (String[] args)
    {
        lab16 q = new lab16();
        Scanner in = new Scanner (System.in);
        StdOut.println("Give 7 number :");
        q.sort(in.nextInt());
        q.sort(in.nextInt());
        q.sort(in.nextInt());
        q.sort(in.nextInt());
        q.sort(in.nextInt());
        q.sort(in.nextInt());
        q.sort(in.nextInt());
        in.close();
    } 
}
