//Code for iterator from the book from Algorithms,4th ed.by Robert Sedgewick, Kevin Wanye.
package lab1;
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
public class lab13<Item> implements Iterable<Item>
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
    private Node first; //skapa två node
    private Node last ; 

    private class Node //node innehåller item och 2st pekare , next och prev
    {
        private Item item;
        private Node next;
        private Node prev;
    }
    public boolean isEmpty()
    {
        return first == null;
    }
    //ny
    public void enqueue(Item item)//lägga item på den sist av listan 
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
    public void dequeue()//ta bort första item samt flytta först till nästa 'först'
    {
        if (isEmpty())
            throw new NoSuchElementException();
        else if(first.next == first)//om det itelerar till sista samt när det är första element 
            first=null;
        else
        {
            first.prev.next=first.next ;
			first.next.prev = last ;
			first = first.next ;
            printout();
        }
        
    }
    public void printout()
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
    public static void main (String[] args)
    {
        lab13<String> q = new lab13<String>();
        q.enqueue("1");
        q.enqueue("2");
        q.enqueue("3");
        q.enqueue("4");
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
    }
}

