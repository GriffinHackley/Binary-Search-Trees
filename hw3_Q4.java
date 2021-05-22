import jdk.nashorn.api.tree.Tree;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.io.*;

class TreeNode
{
    public int key;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int key_input, TreeNode left_input, TreeNode right_input)
    {
        key = key_input;
        left = left_input;
        right = right_input;
    }
}


class BST
{
    private TreeNode root;

    public BST()
    {
        root = null;
    }

    //the pre-order traversal of the tree
    public void preOrder()
    {
        preOrder(root);//call the overloaded function
        System.out.println();
    }

    //the pre-order traversal overloaded function
    private void preOrder(TreeNode v)
    {
        if(v != null)
        {
            System.out.print(v.key + " ");
            preOrder(v.left);
            preOrder(v.right);
        }
    }

    //the in-order traversal of the tree
    public void inOrder()
    {
        inOrder(root);//call the overloaded function
        System.out.println();
    }

    //the in-order traversal overloaded function
    private void inOrder(TreeNode v)
    {
        if(v == null)
            return;
        inOrder(v.left);
        System.out.print(v.key + " ");
        inOrder(v.right);
    }

    //please complete the following function; you can overload it if you want
    public boolean search(int x)
    {
        TreeNode current = root;
        while(x != current.key){
            if(x < current.key && current.left != null){
                current = current.left;
            }
            else if(x < current.key && current.right != null){
                current = current.right;
            }
            else{
                return false;
            }
        }
        if(current.key == x){
            return true;
        }
        return false;
    }

    //please complete the following function; you can overload it if you want
    public void insert(int x)
    {
        if(root == null){
            root = new TreeNode(x,null, null);
        }
        else{
            TreeNode current = root;
            while (x != current.key) {
                if(x < current.key){
                    if(current.left == null){
                        current.left = new TreeNode(x,null,null);
                    }
                    else{
                        current = current.left;
                    }
                }
                if(x > current.key){
                    if(current.right == null){
                        current.right = new TreeNode(x,null,null);
                    }
                    else{
                        current = current.right;
                    }
                }
            }
        }
    }

    //please complete the following function; you can overload it if you want
    public void remove(int x)
    {
        root = delete(root,x);
    }

    public TreeNode delete(TreeNode current, int x){
        if(x < current.key){
            current.left = delete(current.left, x);
        }
        else if(x > current.key){
            current.right = delete(current.right,x);
        }
        else{
            if(current.right == null){
                return current.left;
            }
            else if(current.left == null){
                return current.right;
            }
            current.key = findMin(current.right);
            root.right = delete(current.right, current.key);
        }
        return current;
    }

    //please complete the following function; you can overload it if you want
    public int findMin()
    {
        TreeNode current = root;
        while(current.left != null){
            current = current.left;
        }
        return current.key;
    }

    public int findMin(TreeNode start)
    {
        TreeNode current = start;
        while(current.left != null){
            current = current.left;
        }
        return current.key;
    }
}


public class hw3_Q4
{
    public static void main(String[] args) throws IOException
    {

        BST tree = new BST();

        String inputFile = "hw3_Q4_input.txt"; // input file with operations

        //open the input file
        File myFile = new File(inputFile);
        Scanner input = new Scanner(myFile);

        //read operations from the input file
        String op;
        int x;
        while(input.hasNext())
        {
            Scanner nextLine = new Scanner(input.nextLine());
            op = nextLine.next();

            if (op.equals("insert"))
            {
                x = nextLine.nextInt(); // read the value x for insert
                tree.insert(x);
            }
            if (op.equals("remove"))
            {
                x = nextLine.nextInt(); // read the value x for remove
                tree.remove(x);
            }
            if (op.equals("search"))
            {
                x = nextLine.nextInt();
                if (tree.search(x) == true)
                    System.out.println("The key " + x + " is in the current tree.");
                else// x is not in the tree
                    System.out.println("The key " + x + " is not in the current tree.");
            }
            if (op.equals("findMin"))
                System.out.println("The smallest key in the current tree is " + tree.findMin());
        }

        //print the pre-odrder traversal on the console/screen
        System.out.println("The pre-order traversal list is: ");
        tree.preOrder();

        //print the in-odrder traversal
        System.out.println("The in-order traversal list is: ");
        tree.inOrder();

        input.close();

    }
}
