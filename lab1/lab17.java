//Code for stack implementation from the book from Algorithms, 4th ed. Sedgewick & Wayne
package lab1;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import edu.princeton.cs.algs4.StdOut;
public class lab17 <Item> implements Iterable<Item>  {

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
    private class Node //singel linked stack
    {
        Item item;
        Node next;
    }
    public boolean isEmpty() 
    {
        return first == null;
    }
    public void push(Item item)
    {
        Node oldfirst = first;
        first = new Node(); 
        first.item = item; 
        first.next = oldfirst; 
    }
    public void pop()
    { 
        if (isEmpty())
            throw new NoSuchElementException("Tomt stack!");
        first = first.next;
    }
    public Item returnFirst()
    {
        return first.item;
    }
  
    //ny
    public static boolean pair ( char ch1,char ch2 )//balancerade parantes 
    {
        return((ch2 == '(' && ch1 == ')') || (ch2 == '{' && ch1 == '}') || (ch2 == '[' && ch1 == ']'));
    }

    public static boolean check ( String st )
    {

        lab17<Character> stack = new lab17 <>();
        char[] chaa = new char[st.length()];//gör string till char array och push eller pop varje element i array
        for (int i = 0; i < st.length() ; i++ )
        {
            chaa[i] = st.charAt(i);
            if( chaa[i] == '(' || chaa[i] == '{' || chaa[i] == '[' )//om char är vänster dela av parantesen , push in den först 
                stack.push(chaa[i]);
            else if( chaa[i] == ')' || chaa[i] == '}' || chaa[i] == ']' )//om char är höger del av parantes , dela till olika situationer
            {
                if( stack.isEmpty() )//1. stack är empty --> ],),} räknas som unbalanced
                    return false;
                else if ( !stack.isEmpty() )//2. stack är inte empty , tänker om att det är par eller inte .
                    if( pair(chaa[i] , stack.returnFirst())) // använder par metod och mata in first.item och parantes
                        stack.pop();//on boolean är true , pop out . 
            }
        }
        return stack.isEmpty(); // om stack är empty, det betyder att alla character innan är balancerade
    }

    public static void main(String [] arg)
    {
        Scanner in = new Scanner (System.in);
        String st = in.next();
        boolean boo = check(st);
        if(boo)
            StdOut.println("balanced");
        else
            StdOut.println("unbalanced");
        in.close();
    }
}
