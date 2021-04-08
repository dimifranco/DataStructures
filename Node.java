import java.util.Scanner;
import java.lang.StringBuilder;
import java.util.ArrayList;

public class Node {

    int key;
    Node left, right;
    int h;

    public Node(int k) {
        key = k;
        left = null;
        right = null;
        h = -1;
     }

    public static void main(String[] args) {

        System.out.print("Input? > ");
        ArrayList<String> list = new ArrayList<String>();
        int res = 0;
        list = read();

        Node n = toTree(list);
        
        res = verificaBST(n, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if(res == 0) {
            System.out.println(res);
        } else {
            res += verificaAVL(n);
            System.out.println(res);
        }
    }

    public static ArrayList<String> read() {//eventualmente per non usare la memoization in height potevo inserire qui il calcolo di h
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[] split = s.split(" ");
        ArrayList<String> list = new ArrayList<String>();

        for(String string : split) {
            list.add(string);
        }
        
        return list;

    }

    public static int verificaBST(Node n, int min, int max) {

        if(n==null) {
            return 1;
        }
        if( (n.key <= min) || (n.key >= max) ) {
            return 0;
        }
        if( (verificaBST(n.left, min, n.key) == 1) && (verificaBST(n.right, n.key, max) == 1) ) {
            return 1;
        } else {
            return 0;
        }
    } 

    /*
    algoritmo che nel caso pessimo e' quadratico O(n^2) --> causato dal ricalcolo ogni volta dell'altezza dell'albero
    per risolverlo  uso la memoization, mi salvo un passo theta(n) ad ogni chiamata ricorsiva
    */
    public static int verificaAVL(Node n) {  
    
        if(n==null) {
            return 1;
        }

        int h1 = height(n.left);
        int h2 = height(n.right);

        if(Math.abs(h1-h2) > 1) {
            return 0;
        } 

        if( (verificaAVL(n.left) == 1) && (verificaAVL(n.right) == 1) ) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int height(Node n) { //con memoization, dopo la prima chiamata della funzione con nodo radice, tutti i nodi
                                       //dell'albero avranno l'h corretta, calcolo gia' per tutto l'albero
        if(n==null) {
            return 0;
        }
        if(n.h == -1) {
            n.h = 1 + Math.max(height(n.left), height(n.right));
        }

        return n.h;        
    }

    public static Node toTree(ArrayList<String> list) {//eventualmente per non usare la memoization in height potevo inserire qui il calcolo di h

        if(list.get(0).equals("NULL")) {
            list.remove(0);
            return null;
        }

        int k = Integer.parseInt(list.get(0));
        Node n = new Node(k);
        list.remove(0);

        Node l = toTree(list);
        Node r = toTree(list);
        n.left = l;
        n.right = r;

        return n;
    }

    public static void stampa(Node n) {
        if(n == null) {
            System.out.print("NULL ");
        } else {
            System.out.print(n.key + " ");
            stampa(n.left);
            stampa(n.right);
        }
    }
}