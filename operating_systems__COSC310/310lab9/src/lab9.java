//TJ Liggett
//COSC 310 Lab 9
//4_15_2019

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class lab9 {

    public static ArrayList<Node> nodes;            /* The master list of nodes */
    public static LinkedList<String> chain;         /* Keeps track of the current chain of nodes to detect deadlock */

    public static void main(String[] args) throws FileNotFoundException {

        nodes = new ArrayList<Node>();
        chain = new LinkedList<String>();
        Scanner input = new Scanner(new File("input.txt"));

        //Read input from file input.txt
        while (input.hasNextLine()) {

            //Each line is pulled out of the file and split into three parts:
            //  0) The process node
            //  1) The symbol denoting which node is outgoing (> or <)
            //  2) The resource node
            String line = input.nextLine();
            String[] inputs = line.split(" ");
            Node process = new Node(inputs[0]);
            Node resource = new Node(inputs[2]);

            //If >, the resource is outgoing node
            if (inputs[1].equals(">"))
            {
                int index = nodes.indexOf(process);
                if(index == -1)                     /*Checks if list already contains process node */
                {
                    process.add(resource);
                    nodes.add(process);
                }
                else nodes.get(index).add(resource);
                if(nodes.indexOf(resource) == -1) nodes.add(resource);  /*If nodes does not contain resource add it */
            }
            else        /* Symbol is <, process is outgoing node */
            {
                int index = nodes.indexOf(resource);
                if(index == -1)                     /*Checks if list already contains resource node */
                {
                    resource.add(process);
                    nodes.add(resource);
                }
                else nodes.get(index).add(process);
                if(nodes.indexOf(process) == -1) nodes.add(process);  /*If nodes does not contain process add it*/
            }

        }

        //For each node, trace through all of its paths to see if deadlock is present
        for (Node n : nodes) {
            chain.clear();
            System.out.printf("Starting node: %s\n", n);
            chain.add(n.name);
            if (trace(n, null))     /*If a deadlock has occurred*/
            {
                System.out.println("***Deadlock Detected! Node List L = " + chain);
                return;
            }
            System.out.println();
        }

    }


    //Return: true if deadlock occurs, false otherwise;
    public static boolean trace(Node n, Node prev) {
        for (Node outgoing : n.outgoingNodes) {
            System.out.printf("%s has an outgoing node -- following to: %s\n", n, outgoing);
            //Checks for deadlock
            if (chain.contains(outgoing.name)){
                chain.add(outgoing.name);
                System.out.printf("***List already contains node %s\n", outgoing);
                return true;
            }
            int index = nodes.indexOf(outgoing);
            chain.add(outgoing.name);                       //Adds outgoing node to chain
            if (trace(nodes.get(index), n)) return true;    //Checks if outgoing node has deadlock
        }

        if (prev == null) System.out.printf("Path starting at %s contains no cycles. Next node...\n", n.name);
        else System.out.printf("%s has no outgoing nodes -- returning to: %s\n", n, prev);
        chain.removeLast();         /*To return to previous node, n must be cleared from chain*/
        return false;               /*No deadlock has occurred*/
    }

}

class Node implements Comparable<Node>{
    public LinkedList<Node> outgoingNodes;          /*Keeps a list of outgoing nodes*/
    String name;                                    /*Name of node*/

    //Post: Name is set to name, outgoing list defined
    public Node(String name){
        this.name = name;
        outgoingNodes = new LinkedList<Node>();
    }

    //Post: Node n added to outgoing nodes
    public void add(Node n){
        outgoingNodes.add(n);
    }

    //Return: true if names of nodes are equal
    public boolean equals(Object o){
        Node n = (Node)o;
        return n.name.equals(this.name);
    }

    //Return: the name of this node
    public String toString(){
        return name;
    }

    //Return: the compareTo value of the nodes' names' string values
    @Override
    public int compareTo(Node o) {
        return this.name.compareTo(o.name);
    }
}