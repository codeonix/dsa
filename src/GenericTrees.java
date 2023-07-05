import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class TreeNode<T>{
    T data;
    ArrayList<TreeNode<T>> children;

    public TreeNode(T data) {
        this.data = data;
        this.children = new ArrayList<>();
    }
}

public class GenericTrees {

    public static void printDFS(TreeNode<Integer> root) {
        if(root == null) {
            return;
        }

        System.out.print(root.data+ ": ");
        for (int i = 0; i < root.children.size(); i++) {
            System.out.print(root.children.get(i).data+ " ");
        }
        System.out.println();

        for (int i = 0; i < root.children.size(); i++) {
            printDFS(root.children.get(i));
        }
    }


    public static int numberOfNodes(TreeNode<Integer> root) {
        if(root == null) {
            return 0;
        }
        int ans = 0;

        for (int i = 0; i < root.children.size(); i++) {
            ans +=  numberOfNodes(root.children.get(i));
        }

        return ans + 1;
    }

    // DFS : Depth first search
    public static TreeNode<Integer> takeInputRecursive() {
        Scanner s = new Scanner(System.in);
        int rootData = s.nextInt();

        if(rootData == -1) {
            return null;
        }

        TreeNode<Integer> root = new TreeNode<>(rootData);
        System.out.println("Enter number of child for " + rootData);
        int n = s.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter "+ i + "th child of "+ rootData);
            TreeNode<Integer> newChild = takeInputRecursive();
            root.children.add(newChild);
        }


        return root;
    }

   // BFS : breadth first search
    public static TreeNode<Integer> takeInputLevelWise() {
        Scanner s = new Scanner(System.in);
        int rootData = s.nextInt();
        if(rootData == -1) {
            return null;
        }

        Queue<TreeNode<Integer>> pending = new LinkedList<>();
        TreeNode<Integer> root = new TreeNode<>(rootData);
        pending.add(root);

        while (!pending.isEmpty()) {
            TreeNode<Integer> currNode = pending.poll();
            System.out.println("Enter number of child for " + currNode.data);
            int n = s.nextInt();
            for (int i = 0; i < n; i++) {
                System.out.println("Enter "+ i + "th child for " + currNode.data);
                TreeNode<Integer> child = new TreeNode<>(s.nextInt());
                currNode.children.add(child);
                pending.add(child);
            }
        }

        return root;
    }



    public static int sumOfAllNodes(TreeNode<Integer> root) {
        if(root == null) {
            return 0;
        }

        int sum = 0;

        for (int i = 0; i < root.children.size(); i++) {
            sum += sumOfAllNodes(root.children.get(i));
        }

        return sum + root.data;
    }


    public static void printBFS(TreeNode<Integer> root) {
        if(root == null) {
            return;
        }

        Queue<TreeNode<Integer>> pending = new LinkedList<>();
        pending.add(root);

        while (!pending.isEmpty()) {
            TreeNode<Integer> currentNode = pending.poll();
            System.out.print(currentNode.data+ ": ");

            for (int i = 0; i < currentNode.children.size(); i++) {
                System.out.print(currentNode.children.get(i).data+ " ");
            }
            System.out.println();
            for (int i = 0; i < currentNode.children.size(); i++) {
                pending.add(currentNode.children.get(i));
            }
            //pending.addAll(currentNode.children);
        }
    }


    public static int nodesGreaterThanX(TreeNode<Integer> root , int x) {
        if(root == null) {
            return 0;
        }

        int count = 0;

        for (int i = 0; i < root.children.size(); i++) {
            count += nodesGreaterThanX(root.children.get(i),x);
        }
        return count + 1;
    }


    public static int heightDFS(TreeNode<Integer> root) {
        if(root == null) {
            return 0;
        }
        int height = 0;

        for (int i = 0; i < root.children.size(); i++) {
            height = Math.max(heightDFS(root.children.get(i)), height);
        }
        return height + 1;
    }

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        TreeNode<Integer> node2 = new TreeNode<>(2);
        TreeNode<Integer> node3 = new TreeNode<>(3);
        TreeNode<Integer> node4 = new TreeNode<>(4);
        TreeNode<Integer> node5 = new TreeNode<>(5);
        TreeNode<Integer> node6 = new TreeNode<>(6);

        root.children.add(node2);
        root.children.add(node3);
        root.children.add(node4);

        node3.children.add(node5);
        node3.children.add(node6);

        printBFS(root);
        System.out.println("number of nodes is: " + numberOfNodes(root));
        System.out.println("sum of nodes is: " + sumOfAllNodes(root));

        TreeNode<Integer> root1 = takeInputLevelWise();
        printDFS(root1);
    }
}
