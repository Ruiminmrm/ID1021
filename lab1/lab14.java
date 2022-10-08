//Code for iterator from the book from Algorithms,4th ed.by Robert Sedgewick, Kevin Wanye.
package lab1;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.princeton.cs.algs4.StdOut;
public class lab14<Item> implements Iterable<Item>
{
    public Iterator<Item> iterator()
    { 
        return new LinkedIterator(); 
    }
    private class LinkedIterator implements Iterator<Item> 
    {
        private Node current = first;
        public boolean hasNext()
        { 
            return current != null; 
        }
        public void remove()
        {
            throw new UnsupportedOperationException();  
        }
        public Item next()
        {
            if (!hasNext()) 
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
    private Node first;
    private Node last ; 

    private class Node 
    {
        private Item item;
        private Node next;
        private Node prev;
    }
    public boolean isEmpty()
    {
        return first == null;
    }
    
    public void putlast(Item item)//lab1.3 enqueue
    {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        if(isEmpty())
        {
            first = last;
            first.prev = first;
            first.next = first;
            printout();
        }
        else
        {
            oldlast.next = last;
            last.prev = oldlast;
            last.next = first;
            first.prev = last;
            printout();
        } 
    }
    public void removefirst()//lab1.3 dequeue
    {
        if (isEmpty())
            throw new NoSuchElementException();
        else if(first.next == first)
            first=null;
        else
        {
            first.prev.next=first.next ;
			first.next.prev = first.prev ;
			first = first.next ;
            printout();
        }
        
    }
    public void printout()//lab1.3 
    {
        if(isEmpty())
            StdOut.print("Empty");
        else
        {
            Node n = first;
            while(n.next!= first)//när n.next = first ----> 1. first = last  2. n = last
            {                    // print alla n != last 
               StdOut.print("[" + n.item + "]"+", ");
               n = n.next;//till sista n = last och n inte uppfyll krav för while loop
            } 
            System.out.println("[" + n.item + "]"+", ");//för att print last
        }
    }

//ny

    public void putfirst(Item item) 
    {
        Node newfirst = first;//skapa en new node som ta över gamla info i first
        first = new Node() ;//spara item i first
        first.item = item;
        if( newfirst == null )
        {
            last = first;
            first.prev = first;
            first.next = first;
            printout();
        }
        else
        {   
            newfirst.prev = first;
            first.next = newfirst;
            first.prev = last;
            last.next = first;
            printout();
        }
    }

    public void removelast()//ta bort sist
    {
       if (isEmpty())
            throw new NoSuchElementException("Empty!!!!");
        Node f = first;
        if(f.next == first)// first == last
        {               
            StdOut.println("Empty ");
        }   
        else
        {
            last = last.prev;
            last .next = first;
            first.prev = last;
            printout();
        }
    }
    public static void main (String[] args)
    {
        lab14<String> q = new lab14<String>();
        
        Scanner in = new Scanner(System.in);
        
        StdOut.println("Give a string  : ");
        q.putfirst(in.next());
        
        StdOut.println("Give string and put it at last :"); 
        q.putlast(in.next());
       
        StdOut.println("Give string and put it at first :"); 
        q.putfirst(in.next());
        
        StdOut.println("Give string and put it at last :");
        q.putlast(in.next());
        
        StdOut.println("remove the last string : "); 
        q.removelast();
        
        StdOut.println("remove the last string : "); 
        q.removelast();

        StdOut.println("remove the first string : "); 
        q.removefirst();

        StdOut.println("remove the first string : "); 
        q.removefirst();

        in.close();
    
        /*q.putlast("2");
        q.putfirst("1 ");
        q.putlast("3");
        q.putfirst("4");
        q.removelast();
        q.removelast();
        q.removefirst();
        q.removelast();*/
        
    }
}
