package lab4;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
public class lab41 {
    public void main(String[] args) throws FileNotFoundException{
        Scanner fil = new Scanner(new File("C:\\Users\\marui\\OneDrive\\桌面\\Lab\\lab4\\text.txt"));
        
        while (fil.hasNextLine()){
            String n = fil.next();
            
            if(!gr)
        }
    }


   /* public static void main(String[] args) throws FileNotFoundException {
        Scanner filAttLasaFran = new Scanner(new File("C:\\Users\\marui\\OneDrive\\桌面\\Lab\\lab4\\text.txt"));

        BSST<String,Integer> graf = new BSST(150);

        String namnPaStat;
        String startPunkt;
        String slutPunkt;

        int finns = 0;

        while(filAttLasaFran.hasNextLine()){
            namnPaStat = filAttLasaFran.next();

            if(!graf.contains(namnPaStat)){
                graf.put(namnPaStat, finns); //Alla stater som vi kan gå igenom
                finns++;
            }
        }

        Graph Graf = new Graph(finns);

        Scanner scanner = new Scanner(new File("C:\\Users\\marui\\OneDrive\\桌面\\Lab\\lab4\\text.txt"));
        while(scanner.hasNextLine()){
            startPunkt = scanner.next();
            slutPunkt = scanner.next();

            Graf.addEdge((graf.get(startPunkt)), graf.get(slutPunkt));
        }

        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        boolean isTrue = true;
        while(isTrue) { //kommer vara sant hela tiden tills vi avslutar för att slippa läsa om filen
            System.out.println("Vill du skriva ut en graf mellan två stater? Skriv 1 för ja och 2 för nej. ");

            int inp = input1.nextInt();
            switch (inp) {
                case 1:
                    System.out.println("Välj en stat att starta i: ");
                    String start = input2.nextLine();
                    System.out.println("Välj en stat du vill åka till från din startpunkt: ");
                    String slut = input2.nextLine();

                    int sp = graf.get(start); //startpunkt
                    int slp = graf.get(slut); //slutpunkt

                    DepthFirstPaths dfp = new DepthFirstPaths(Graf, sp); //skapa en ny dfp
                    Iterator iterator = graf.keys().iterator();
                    String at = graf.min();

                    if(dfp.hasPathTo(slp)) { //finns det en väg sådan att vi kan gå från stat 1 till stat 2?
                        System.out.println(start + " till " + slut + " går denna väg: ");
                        for (int a : dfp.pathTo(slp)) { //Iterera över vägarna
                            if(a == slp) //Om noden vi är på == startpunkten
                                System.out.println(slut);
                            else {
                                while (true) { //går genom nod
                                    if (graf.get(at) == a){
                                        System.out.print(" " + at + " ");
                                        break;
                                    }
                                    at = (String) iterator.next();
                                }
                                at = graf.min();
                                System.out.print(" ---> ");
                                iterator = graf.keys().iterator();
                            }
                        }
                        System.out.println();
                    }
                    else
                        System.out.println("Det finns ingen väg mellan dessa stater!");

                    break; //kommer tillbaka till frågan i början

                case 2: //om input == 2
                    isTrue = false;

                default:
                    System.out.println("Bye bye! :)");
            }
        }
    } */   
}
