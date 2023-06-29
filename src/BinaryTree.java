import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

    class BinaryTreeNode<T> {
    BinaryTreeNode<T> left;
    T data;
    BinaryTreeNode<T> right;

    public BinaryTreeNode(T data) {
        this.data = data;
    }
}

class IsBalancedReturn {
    int height;
    boolean isBalanced;

    IsBalancedReturn(int height , boolean isBalanced) {
        this.height = height;
        this.isBalanced = isBalanced;
    }
}

class DiameterReturn {
    int diameter;
    int height;

    public DiameterReturn(int diameter , int height) {
        this.diameter = diameter;
        this.height = height;
    }
}

class MinAndMaxReturn {
    int minimum;
    int maximum;

    MinAndMaxReturn(int min , int max) {
        this.maximum = max;
        this.minimum = min;
    }
}

public class BinaryTree {
    public static void printNodesAtDistanceKFromANode(BinaryTreeNode<Integer> root, int node, int distance) {

    }
    public static void rootToLeafPathSumToKHelper(BinaryTreeNode<Integer> root , String path , int k , int currentSum) {
        if(root == null) {
            return;
        }

        if(root.left == null && root.right == null) {
            currentSum += root.data;
            if(currentSum == k) {
                System.out.print(path+ root.data+ " ");
            }
            return;
        }

        rootToLeafPathSumToKHelper(root.left, path + root.data + " ", k, currentSum + root.data);
        rootToLeafPathSumToKHelper(root.right, path + root.data + " ", k, currentSum + root.data);
    }

    public static void rootToLeafPathSumToK(BinaryTreeNode<Integer> root, int k) {
       rootToLeafPathSumToKHelper(root,"",k,0);
    }

    public static MinAndMaxReturn minAndMax(BinaryTreeNode<Integer> root ) {
        if(root == null) {
            return new MinAndMaxReturn(Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        MinAndMaxReturn leftAns = minAndMax(root.left);
        MinAndMaxReturn rightAns = minAndMax(root.right);

        int min = Math.min(root.data, Math.min(leftAns.minimum, rightAns.minimum));
        int max = Math.max(root.data, Math.max(leftAns.maximum, rightAns.maximum));

        return new MinAndMaxReturn(min,max);
    }

    public static void insertDuplicateNode(BinaryTreeNode<Integer> root) {
        if(root == null) {
            return;
        }

        BinaryTreeNode<Integer> duplicateNode = new BinaryTreeNode<>(root.data);
        BinaryTreeNode<Integer> temp = root.left;

        root.left = duplicateNode;
        duplicateNode.left = temp;

        insertDuplicateNode(temp);
        insertDuplicateNode(root.right);
    }

    public static BinaryTreeNode<Integer> buildTreeUsingPostIn(int[] postOrder, int[] inOrder) {
        return buildTreeUsingPostInHelper(postOrder ,0 , postOrder.length -1 , inOrder, 0 , inOrder.length -1);
    }

    private static BinaryTreeNode<Integer> buildTreeUsingPostInHelper(int[] postOrder, int postStart, int postEnd, int[] inOrder, int inStart, int inEnd) {
        if(inStart > inEnd || postStart > postEnd) {
            return null;
        }

        int rootData = postOrder[postEnd];
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(rootData);

        //search root position in in-order :
        int k = 0;
        for (int i = inStart ; i <= inEnd ; i++) {
            if(inOrder[i] == rootData) {
                k = i;
                break;
            }
        }

        root.left = buildTreeUsingPostInHelper(postOrder,postStart, postStart + (k - inStart) -1, inOrder, inStart , k - 1);
        root.right = buildTreeUsingPostInHelper(postOrder, postStart + (k - inStart) , postEnd - 1 ,inOrder,k+1, inEnd );
        return root;
    }

    public static BinaryTreeNode<Integer> buildTreeUsingPreInHelper(int[] preOrder , int preStart,int preEnd ,
                                                                    int[] inOrder , int inStart , int inEnd) {
        if(preStart > preEnd || inStart > inEnd) {
            return null;
        }
        int rootData = preOrder[preStart];
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(rootData);

        //search root position in inorder
        int k = 0;
        for(int i = inStart ; i <= inEnd ; i++) {
            if(inOrder[i] == rootData) {
                k = i;
                break;
            }
        }

        root.left = buildTreeUsingPreInHelper(preOrder, preStart + 1,preStart + (k - inStart),inOrder, inStart, k-1);
        root.right = buildTreeUsingPreInHelper(preOrder,preStart + (k - inStart)+ 1,preEnd, inOrder,k+1, inEnd );

        return root;
    }

    public static BinaryTreeNode<Integer> buildTreeUsingPreIn(int[] preOrder , int[] inOrder){
        return buildTreeUsingPreInHelper(preOrder, 0, preOrder.length -1, inOrder,0, inOrder.length -1);
    }

    public static DiameterReturn diameter(BinaryTreeNode<Integer> root) {
        if(root == null) {
            return new DiameterReturn(0,0);
        }

        DiameterReturn leftAns = diameter(root.left);
        DiameterReturn rightAns = diameter(root.right);

        int farthestDistance = 1 + leftAns.height + rightAns.height;
        int diameter = Math.max(farthestDistance , Math.max(leftAns.diameter , rightAns.diameter));

        int height = Math.max(leftAns.height, rightAns.height) + 1;
        return new DiameterReturn(diameter,height);
    }

    public static IsBalancedReturn isBalancedBetter(BinaryTreeNode<Integer> root) {
        if(root == null) {
            return new IsBalancedReturn(0, true);
        }

        IsBalancedReturn leftAns = isBalancedBetter(root.left);
        IsBalancedReturn rightAns = isBalancedBetter(root.right);
        boolean isBalanced = true;

        if(Math.abs(leftAns.height - rightAns.height) > 1) {
            isBalanced = false;
        }

        if(!leftAns.isBalanced || !rightAns.isBalanced) {
            isBalanced = false;
        }

        return new IsBalancedReturn(Math.max(leftAns.height, rightAns.height) + 1 , isBalanced);
    }

    public static boolean isBalanced(BinaryTreeNode<Integer> root) {
        if(root == null) {
            return true;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        if(Math.abs(leftHeight - rightHeight) > 1) {
            return  false;
        }

        return isBalanced(root.right)  && isBalanced(root.left);
    }

    public static BinaryTreeNode<Integer> mirror(BinaryTreeNode<Integer> root) {
        if(root == null) {
            return null;
        }

        if(root.left == null && root.right == null) {
            return root;
        }

        BinaryTreeNode<Integer> temp = root.left;
        root.left = mirror(root.right);
        root.right = mirror(temp);
        return root;
    }

    public static BinaryTreeNode<Integer> removeLeaves(BinaryTreeNode<Integer> root) {
        if(root == null) {
            return null;
        }

        if(root.left == null && root.right == null) {
            return null;
        }

        root.left = removeLeaves(root.left);
        root.right = removeLeaves(root.right);
        return root;
    }

    public static void printNodesWithoutSibling(BinaryTreeNode<Integer> root) {
        if(root == null) {
            return;
        }

        if(root.left == null && root.right != null) {
            System.out.print(root.right.data+ " ");
        }

        if(root.right == null && root.left != null) {
            System.out.print(root.left.data+ " ");
        }

        printNodesWithoutSibling(root.left);
        printNodesWithoutSibling(root.right);
    }

    public static boolean isNodePresent(BinaryTreeNode<Integer> root , int x) {
        if(root == null) {
            return false;
        }

        if(root.data == x) {
            return true;
        }

        return isNodePresent(root.right , x) || isNodePresent(root.left , x);
    }

    private static void replaceNodeDataWithDepthHelper(BinaryTreeNode<Integer> root , int depth) {
        if(root == null) {
            return;
        }

        root.data = depth;
        replaceNodeDataWithDepthHelper(root.left, depth + 1);
        replaceNodeDataWithDepthHelper(root.right, depth + 1);
    }

    public static void replaceNodeDataWithDepth(BinaryTreeNode<Integer> root ) {
        replaceNodeDataWithDepthHelper(root, 0);
    }

    public static void printAllNodesAtDepthK(BinaryTreeNode<Integer> root , int k) {
        if(root == null) {
            return;
        }

        if(k == 0) {
            System.out.print(root.data + " ");
            return;
        }

        printAllNodesAtDepthK(root.left, k-1);
        printAllNodesAtDepthK(root.right, k-1);
    }

    public static int numberOfLeafNodes(BinaryTreeNode<Integer> root) {
        if(root == null) {
            return 0;
        }

        if(root.left == null || root.right == null) {
            return 1;
        }

        return numberOfLeafNodes(root.left) + numberOfLeafNodes(root.right);
    }

    public static int height(BinaryTreeNode<Integer> root ) {
        if(root == null) {
            return 0;
        }

        return 1 + Math.max(height(root.right), height(root.left));
    }

    public static int nodeGreaterThanX(BinaryTreeNode<Integer> root , int x) {
        if(root == null) {
            return 0;
        }

        return root.data > x ? 1 + nodeGreaterThanX(root.left, x) + nodeGreaterThanX(root.right,x) :
                nodeGreaterThanX(root.left,x) + nodeGreaterThanX(root.right,x);
    }

    public static int nodeWithLargestData(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }

        return Math.max(root.data, Math.max(nodeWithLargestData(root.left), nodeWithLargestData(root.right)));
    }

    public static void preOrderTraversal(BinaryTreeNode<Integer> root) {
        if(root == null) {
            return;
        }

        // root --> left --> right
        System.out.print(root.data+ " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    public static void postOrderTraversal(BinaryTreeNode<Integer> root) {
        if(root == null) {
            return;
        }

        // left --> right --> root
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.data+ " ");
    }


    public static void inOrderTraversal(BinaryTreeNode<Integer> root) {
        if(root == null) {
            return;
        }

        // left --> root --> right
        inOrderTraversal(root.left);
        System.out.print(root.data+ " ");
        inOrderTraversal(root.right);
    }



    public static int sumOfNodes(BinaryTreeNode<Integer> root) {
        if(root == null) {
            return 0;
        }

        return root.data + sumOfNodes(root.right) + sumOfNodes(root.left);
    }

    public static int  numberOfNode(BinaryTreeNode<Integer> root) {
        if(root == null) {
            return 0;
        }

        return 1 + numberOfNode(root.left) + numberOfNode(root.right);
    }

    public static BinaryTreeNode<Integer> takeInputBetter(boolean isRoot, int parentData , boolean isLeft) {
        if(isRoot) {
            System.out.println("Enter root data : ");
        } else {
            if(isLeft) {
                System.out.println("Enter left child of "+ parentData);
            } else {
                System.out.println("Enter right child of "+ parentData );
            }
        }

        Scanner  s = new Scanner(System.in);
        int rootData = s.nextInt();
        if(rootData == -1) {
            return null;
        }

        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(rootData);
        root.left = takeInputBetter(false,rootData,true);
        root.right = takeInputBetter(false,rootData,false);
        return root;
    }

    public static BinaryTreeNode<Integer> takeInput() {
        Scanner s = new Scanner(System.in);
        int rootData = s.nextInt();

        if(rootData == -1) {
            return null;
        }

        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(rootData);
        root.left = takeInput();
        root.right = takeInput();
        return root;
    }

    public static BinaryTreeNode<Integer> takeInputLevelWise() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter root data : ");
        int rootData = s.nextInt();
        if(rootData == -1) {
            return null;
        }

        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(rootData);
        Queue<BinaryTreeNode<Integer>> pendingChildren = new LinkedList<BinaryTreeNode<Integer>>();
        pendingChildren.add(root);

        while (!pendingChildren.isEmpty()) {
            BinaryTreeNode<Integer> currentNode = pendingChildren.poll();
            System.out.println("Enter left child of " + currentNode.data);
            int leftData = s.nextInt();
            if(leftData != -1) {
                currentNode.left = new BinaryTreeNode<>(leftData);
                pendingChildren.add(currentNode.left);
            }

            System.out.println("Enter right child of " + currentNode.data);
            int rightData = s.nextInt();
            if(rightData != -1) {
                currentNode.right = new BinaryTreeNode<>(rightData);
                pendingChildren.add(currentNode.right);
            }
        }

        return root;
    }


    public static void printBinaryTree(BinaryTreeNode<Integer> root) {
        if(root == null)  return;
        System.out.print(root.data+ ": ");
        if(root.left != null) System.out.print("L"+root.left.data+ ", ");
        if(root.right != null) System.out.print("R"+root.right.data);
        System.out.println();

        printBinaryTree(root.left);
        printBinaryTree(root.right);
    }


    public static void printLevelWise(BinaryTreeNode<Integer> root) {
        if(root == null) return;
        Queue<BinaryTreeNode<Integer>> pending = new LinkedList<BinaryTreeNode<Integer>>();
        pending.add(root);

        while (!pending.isEmpty()) {
            BinaryTreeNode<Integer> currentNode = pending.poll();
            System.out.print(currentNode.data+ ": ");
            if (currentNode.left != null) {
                System.out.print("L"+currentNode.left.data+ ", ");
                pending.add(currentNode.left);
            }
            if (currentNode.right != null) {
                System.out.print("R"+currentNode.right.data);
                pending.add(currentNode.right);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //BinaryTreeNode<Integer> root = takeInputBetter(true,0,false);
        BinaryTreeNode<Integer> root = takeInputLevelWise();
        printLevelWise(root);
        System.out.println("Number of nodes are : " + numberOfNode(root));
        System.out.println("Sum of nodes are : " + sumOfNodes(root));
        System.out.println("Pre order traversal : ");
        preOrderTraversal(root);
        System.out.println();
        System.out.println("Post order traversal : ");
        postOrderTraversal(root);
        System.out.println();
        System.out.println("In order traversal :");
        inOrderTraversal(root);
        System.out.println();
        System.out.println("Node with largest data is " + nodeWithLargestData(root));
        System.out.println("Number of nodes greater than 1 is " + nodeGreaterThanX(root, 1));
        System.out.println("Height of the tree is " + height(root));
        System.out.println("Number of leaf nodes is "+ numberOfLeafNodes(root));
        System.out.println("All nodes at depth k are : ");
        printAllNodesAtDepthK(root,2);
        //replaceNodeDataWithDepth(root);
        System.out.println("Is node x present : " + isNodePresent(root, 14));
        System.out.println("Nodes without siblings are : ");
        printNodesWithoutSibling(root);
        //BinaryTreeNode<Integer> mirror = mirror(root);
        //printLevelWise(mirror);
        //BinaryTreeNode<Integer> remove = removeLeaves(root);

        System.out.println("\nIs tree balanced : " + isBalancedBetter(root).isBalanced);
        System.out.println("Diameter of the tree is : " + diameter(root).diameter);

        int[] preOrder = {1,2,3};
        int[] postOrder = {2,3,1};
        int[] inOrder = {2,1,3};

        BinaryTreeNode<Integer> root1 = buildTreeUsingPostIn(postOrder,inOrder);
        insertDuplicateNode(root1);
        printLevelWise(root1);

        System.out.println("Minimum and maximum are : " + minAndMax(root1).minimum + " " + minAndMax(root1).maximum);
        rootToLeafPathSumToK(root, 3);
    }
}
