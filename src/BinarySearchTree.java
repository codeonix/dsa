import java.util.ArrayList;
import java.util.Objects;

class IsBSTReturn {
    int max;
    int min;
    boolean isBST;

    IsBSTReturn(int max , int min , boolean isBST) {
        this.isBST = isBST;
        this.max = max;
        this.min = min;
    }
}

class LinkedListNode<T> {
     T data;
    LinkedListNode<T> next;

     LinkedListNode(T data) {
         this.data = data;
     }
}

class BSTToLLReturn {
    LinkedListNode<Integer> head;
    LinkedListNode<Integer> tail;

    BSTToLLReturn(LinkedListNode<Integer> head , LinkedListNode<Integer> tail) {
        this.tail = tail;
        this.head  = head;
    }
}

public class BinarySearchTree {
  public static boolean searchNodeInBST(BinaryTreeNode<Integer> root , int x) {
      if(root == null) {
          return false;
      }

      if(root.data == x) {
          return true;
      }

      if(x < root.data) {
         return searchNodeInBST(root.left , x);
      } else {
         return searchNodeInBST(root.right , x);
      }
  }


  public static void elementInRangeK1K2(BinaryTreeNode<Integer> root, int k1 , int k2) {
      if(root == null) {
          return;
      }
      if(root.data < k1) {
          elementInRangeK1K2(root.right, k1,k2);
      } else if (root.data > k2) {
          elementInRangeK1K2(root.left, k1,k2);
      } else {
          elementInRangeK1K2(root.left, k1 , k2);
          System.out.print(root.data+ " ");
          elementInRangeK1K2(root.right, k1, k2);
      }
  }


  public static BinaryTreeNode<Integer> sortedArrayToBST(int[] arr, int start, int end ) {
      if(start > end) {
          return null;
      }

      int mid = start + (end - start) / 2;
      BinaryTreeNode<Integer> root = new BinaryTreeNode<>(arr[mid]);
      root.left = sortedArrayToBST(arr, start , mid -1);
      root.right = sortedArrayToBST(arr, mid + 1 , end);
      return root;
  }

  public static boolean isBST(BinaryTreeNode<Integer> root) {
      if(root == null) {
          return true;
      }

      int leftMax = max(root.left);
      if(leftMax > root.data) {
          return false;
      }

      int rightMin = min(root.right);
      if(rightMin < root.data) {
          return false;
      }

      return isBST(root.left) && isBST(root.right);
  }

  public static int  max(BinaryTreeNode<Integer> root) {
      if(root == null) {
          return Integer.MIN_VALUE;
      }

      return Math.max(root.data , Math.max(max(root.right), max(root.left)));
  }

  public static int  min(BinaryTreeNode<Integer> root) {
        if(root == null) {
            return Integer.MAX_VALUE;
        }

        return Math.max(root.data , Math.min(min(root.right), min(root.left)));
  }

  public static IsBSTReturn isBSTBetter(BinaryTreeNode<Integer> root) {
      if(root == null) {
          return new IsBSTReturn(Integer.MIN_VALUE, Integer.MAX_VALUE, true);
      }

      IsBSTReturn leftAns = isBSTBetter(root.left);
      IsBSTReturn rightAns = isBSTBetter(root.right);

      int min = Math.min(root.data, Math.min(leftAns.min, rightAns.min));
      int max = Math.max(root.data, Math.min(leftAns.max, rightAns.max));
      boolean isBST = leftAns.isBST && rightAns.isBST && root.data > leftAns.max &&  root.data <= rightAns.min;

      return new IsBSTReturn(max,min,isBST);
  }


  public static boolean isBST2(BinaryTreeNode<Integer> root, int max , int min ) {
      if(root == null) {
          return true;
      }

      if(root.data < min && root.data > max) {
          return false;
      }

      boolean isLeftWithinRange = isBST2(root.left, root.data -1 , min);
      boolean isRightWithinRange = isBST2(root.right , max , root.data );

      return isLeftWithinRange && isRightWithinRange;
  }


  public static LinkedListNode<Integer> bstToSortedLL(BinaryTreeNode<Integer> root) {
      return buildLLHelper(root).head;
  }

    private static BSTToLLReturn buildLLHelper(BinaryTreeNode<Integer> root) {
      if(root == null) {
          return new BSTToLLReturn(null , null);
      }

      LinkedListNode<Integer> middleNode = new LinkedListNode<>(root.data);

      BSTToLLReturn leftLinkedList = buildLLHelper(root.left);
      BSTToLLReturn rightLinkedList = buildLLHelper(root.right);

      if(leftLinkedList.tail != null) {
          leftLinkedList.tail.next = middleNode;
      }
      middleNode.next = rightLinkedList.head;

      LinkedListNode<Integer> head = leftLinkedList.head != null ? leftLinkedList.head : middleNode;
      LinkedListNode<Integer> tail = rightLinkedList.tail != null ? rightLinkedList.tail : middleNode;

      return new BSTToLLReturn(head, tail);
    }


    public static void printLL(LinkedListNode<Integer> head) {
      if (head == null) {
          return;
      }

      while (head != null) {
          System.out.print(head.data+ " ");
          head = head.next;
      }
    }


    public static void printLLR(LinkedListNode<Integer> head) {
      if(head == null) {
          return;
      }

      System.out.print(head.data+ " ");
      printLLR(head.next);
    }


    public static BinaryTreeNode<Integer> lca(BinaryTreeNode<Integer> root, int n1 , int n2) {
      if(root == null) {
          return  null;
      }

      if(root.data == n1 || root.data == n2) {
          return root;
      }

      BinaryTreeNode<Integer> leftAns = lca(root.left , n1, n2);
      BinaryTreeNode<Integer> rightAns = lca(root.right, n1, n2);

      if(leftAns == null) return rightAns;
      if(rightAns == null) return leftAns;

      return root;
    }


    public static void replaceWithLargerNodesSum(BinaryTreeNode<Integer> root) {
      replaceWithLargerNodesSum(root, 0);
    }

    public static int replaceWithLargerNodesSum(BinaryTreeNode<Integer> root , int sum) {
      if(root == null) {
          return sum;
      }

      sum = replaceWithLargerNodesSum(root.right, sum);
      sum += root.data;
      root.data = sum;
      sum = replaceWithLargerNodesSum(root.left, sum);
      return sum;
    }


    public static ArrayList<Integer> nodeToRootPath(BinaryTreeNode<Integer> root, int node) {
      if(root == null) {
          return null;
      }

      if(root.data == node) {
          ArrayList<Integer> ans = new ArrayList<>();
          ans.add(root.data);
          return ans;
      }

      ArrayList<Integer> leftAns = nodeToRootPath(root.left, node);
      if(leftAns != null) {
          leftAns.add(root.data);
          return leftAns;
      }

      ArrayList<Integer> rightAns = nodeToRootPath(root.right, node);
      if(rightAns != null) {
          rightAns.add(root.data);
          return rightAns;
      }

      return null;
    }

    public static ArrayList<Integer> getPathFromNodeToRootBST(BinaryTreeNode<Integer> root , int node) {
      if(root == null) {
          return null;
      }

      if(root.data == node) {
          ArrayList<Integer> ans = new ArrayList<>();
          ans.add(root.data);
          return ans;
      } else if (root.data > node) {
          ArrayList<Integer> leftAns = getPathFromNodeToRootBST(root.left, node);
          if(leftAns != null) {
              leftAns.add(root.data);
          }
          return leftAns;
      } else {
          ArrayList<Integer> rightAns = getPathFromNodeToRootBST(root.right, node);
          if (rightAns != null) {
              rightAns.add(root.data);
          }
          return rightAns;
      }
    }

    public static void main(String[] args) {
        //10 5 12 2 7 11 15 -1 -1 -1 -1 -1 -1 -1 -1
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        BinaryTreeNode<Integer> root = sortedArrayToBST(arr,0,arr.length -1);
        BinaryTree.printLevelWise(root);
        System.out.println("Is Node present : " + searchNodeInBST(root, 2));
        elementInRangeK1K2(root,2,10);
        System.out.println("\nIs it BST : " + isBST2(root, Integer.MAX_VALUE , Integer.MIN_VALUE));
        LinkedListNode<Integer> head = bstToSortedLL(root);
        printLL(head);
        System.out.println("\n"+(Objects.requireNonNull(lca(root, 6, 9))).data);
        //replaceWithLargerNodesSum(root);
        //BinaryTree.printLevelWise(root);
        ArrayList<Integer> path = getPathFromNodeToRootBST(root,6);

        for(int i : path) {
            System.out.print(i+ " ");
        }
    }
}
