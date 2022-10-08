package lab1;
import java.util.*;
import edu.princeton.cs.algs4.StdOut;
public class lab15<Item> implements Iterable<Item> {
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
    public int size()
    {
        return n;
    }
    private Node first; 
    private Node last ; 
    private int n = 0;

    private class Node 
    {
        private Item item;
        private Node next;
    }
    public boolean isEmpty()
    {
        return first == null;
    }
    public void printout()
    {
        if(isEmpty())
            StdOut.print("Empty");
        else{
            Iterator <Item> i = iterator();//om det finns next , iterera
            while(i.hasNext())
            {
                Item item = i .next();
                StdOut.print("["+item+"], ");
            }
        }
        System.out.println(" ");
    }
    public void enqueue(Item item)//single linked 
    {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        n++;
        if(isEmpty())
        {
            first = last;
            printout();
        }
        else
        {
            oldlast.next = last;
            printout();
        }   
    }
    
    public void remove (int index)
    {
        if(index > n)
                    throw new IndexOutOfBoundsException("Give a smaller index !!");
        Node f = first;//placera en reference framför den angivna positionen
        if(index == 1)//1.ta bort den sista int 
        {
            while(f.next != null)// f.next == null ---> f.next == last
            {                    //då använder vi loop att hitta den int
                if(f.next.next == null)// xxxxxAx om f ligger på plats'A' ----> A.next.next = null
                {                     //---->f.next == last
                    f.next = null; // ta bort den 
                    break;
                }
                f = f.next;// till next int 
            }
            n--;
            printout();
        }
        else if(index == n)//2.ta bort den första 
        {
            first = first.next;//first ligger på den next plats
            n--;
            printout();
            return;
        }
        else//3. mellan
        {
            for (int i = n-index-1  ; i > 0 ; i--) //-1 för att det inte innhåller last
                f = f.next;
            f.next = f.next.next;//ta bort element från listan
            n--;
            printout();
            return;
        }
    }
    public static void main(String[] args)
    {
        lab15<String> q = new lab15<String>();
        Scanner in = new Scanner(System.in);
        
        StdOut.println("Give string 7 times : ");
        q.enqueue(in.next());
        q.enqueue(in.next());
        q.enqueue(in.next());
        q.enqueue(in.next());
        q.enqueue(in.next());
        q.enqueue(in.next());
        q.enqueue(in.next());

        StdOut.println("Size is "+q.size()+". Give a nummer smaller than size!!");
        q.remove(in.nextInt());
        q.remove(in.nextInt());
        q.remove(in.nextInt());
        q.remove(in.nextInt());
        in.close();        
    } 
}