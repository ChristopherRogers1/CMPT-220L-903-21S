import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BinarySearchTree
{
    //Creates a completely empty node which serves as the root; this will make things easier for other methods as having a
    //root which can be referenced at any time in any location has huge value; however, only works properly after
    // insertNode is called
    public TreeNode root = null;

    //This is the constructor of a single node object
    public static class TreeNode
    {
        //num = value of node, distance = distance from top of tree (root = 0), left/right = child nodes
        int num=0, distance=0;
        TreeNode left = null, right = null;

        //This is called to create a node and assign its value
        public TreeNode(int num)
        {
            this.num = num;
        }
    }


    //Made insert return an int so I can easily track a node's distance from the root
    //Creates tree if no root exists; adds new branch if root exists
    public int insertNode(int value)
    {
        int distance = 1;
        TreeNode node = new TreeNode(value);
        boolean done = false;
        //if no nodes, first node is set with no children
        if (root == null)
        {
            root = node;
            return 0;
        }
        //if there is nodes, compare value to first node; if less than, go to left, if greater right; if the value is null,
        //place it there, if not continue going left or right until value is null
        else
        {
            int currentval = value;
            TreeNode compareval = root;

            while (done == false)
            {
                if (value <= compareval.num)
                {
                    if (compareval.left == null)
                    {
                        compareval.left = node;
                        compareval.distance = distance;
                        return distance;
                    }
                    else {
                        distance += 1;
                        compareval = compareval.left;
                    }
                }
                else
                    if (compareval.right == null)
                    {
                        compareval.right = node;
                        compareval.distance = distance;
                        return distance;
                    }
                    else {
                        distance += 1;
                        compareval = compareval.right;
                    }
            }
            return 0;
        }
    }

    //This will run left until it finds a node with a left value of null; it then prints the value, and checks to see if it can
    //move to the right and repeat the process; if not, it continues up
    //This is very similar (if not exactly the same) to the recursion that we went over in class
    public void printInOrder(TreeNode node)
    {
        if(node.left!=null) {
            printInOrder(node.left);
        }

        System.out.print(node.num+ " ");

        if(node.right!=null){
            printInOrder(node.right);
        }
    }

    //Pre and post order work similarly to in order, only changing when the value is pulled to before or after the movement
    public void printPreOrder(TreeNode node)
    {
        System.out.print(node.num+ " ");

        if(node.left!=null) {
            printPreOrder(node.left);
        }

        if(node.right!=null){
            printPreOrder(node.right);
        }
    }

    //Pre and post order work similarly to in order, only changing when the value is pulled to before or after the movement
    public void printPostOrder(TreeNode node)
    {
        if(node.left!=null) {
            printPostOrder(node.left);
        }

        if(node.right!=null){
            printPostOrder(node.right);
        }

        System.out.print(node.num+ " ");
    }

    //Runs continues down left children until end is reached, as this will always be the smallest object
    public int findSmallest(TreeNode node)
    {
        while (true)
            if (node.left==null)
            {
                return node.num;
            }
            else
                node = node.left;
    }

    //Same as smallest but with the right
    public int findLargest(TreeNode node)
    {
        while (true)
            if (node.right==null)
            {
                return node.num;
            }
            else
                node = node.right;
    }

    public boolean found = false;

    //Here I used the print method and changed it to try to match the values to the inputted one rather than just print them;
    //it worked well as both methods must go through every value in the tree
    public boolean elementSearch(int value, TreeNode node)
    {
        int find = value;
        if (node.num == find)
        {
            found = true;
        }
        if(node.left!=null) {
            elementSearch(find, node.left);
        }

        if(node.right!=null){
            elementSearch(find, node.right);
        }
        return found;
    }

    ArrayList<Integer> findNthList = new ArrayList<Integer>();

    //This is used below in findNth
    public void createArrayList(TreeNode node)
    {
        if(node.left!=null) {
            createArrayList(node.left);
        }

        findNthList.add(node.num);

        if(node.right!=null){
            createArrayList(node.right);
        }
    }

    //Here I create an arrayList with every value, placed in order using a sort method similar to inorder, and find the
    //nth value in the arraylist.
    public int findNth(int value, TreeNode node)
    {
        int newVal = value-1;
        createArrayList(node);
        return findNthList.get(newVal);
    }

    static TreeNode holder = null;

    //This is used below in deleteNode
    public static void findPreviousNode(int value, TreeNode node)
    {
        if(node.left!=null) {
            if ((node.left).num==value)
                holder = node;
            findPreviousNode(value, node.left);
        }

        if(node.right!=null){
            if ((node.right).num==value)
                holder = node;
            findPreviousNode(value, node.right);
        }
    }

    //Here I find the location of the value before the deleted node, replace the left/right (whichever is appropriate) with
    //null, and re-add the children of the removed node.
    public void removeNode(int value, TreeNode node)
    {
        TreeNode placeholder1 = null, placeholder2 = null;
        findPreviousNode(value, node);
        if (node.left!=null)
            placeholder1=node.left;
        if (node.right!=null)
            placeholder1=node.right;
        if (holder.num>value)
            holder.left=null;
        else
            holder.right=null;
        if (placeholder1!=null)
            insertNode(placeholder1.num);
        if (placeholder2!=null)
            insertNode(placeholder2.num);
        System.out.println(value + "has been removed from the tree.");
    }

    //This is basically the preorder search, as depth first and preorder are very similar, with finding a specific value
    // rather than printing out every value.
    public static void depthFirstSearch(int value, TreeNode node)
    {
        if (node.num == value)
        {
            System.out.println(value + " is in the tree.");
            return;
        }

        if(node.left!=null) {
            depthFirstSearch(value, node.left);
        }

        if(node.right!=null){
            depthFirstSearch(value, node.right);
        }
    }

    //This method was very difficult, as I couldn't come up with an easy way to move left->right across more than 2 nodes
    //that have a similar height on the tree; still not entirely sure if this is a proper breadth first search

    //I tried to add all values with similar heights into an arraylist (which acts as a stack) and continue to add/remove
    //layers from the stack as I move further down the tree's height all while checking to see if they match the value.
    public static void breadthFirstSearch(int value, TreeNode node)
    {
        int counter = 0;
        ArrayList<TreeNode> nodeList = new ArrayList<TreeNode>();
        TreeNode newNode = node;
        nodeList.add(node);

        while (nodeList.isEmpty()==false) {
            newNode = nodeList.get(0);
            nodeList.remove(0);

            if (newNode.num==value) {
                System.out.println(value + " is in the tree according to breadth search.");
                return;
            }

            if (newNode.left != null)
                nodeList.add(node.left);

            if (newNode.right != null)
                nodeList.add(node.right);

        }
    }

    public static void main(String[] args)
    {
        ArrayList<Integer> sizeList = new ArrayList<Integer>();

        //Using an arraylist of distances from the root seemed like the easiest way to compare values and
        // find the maximum depth of the tree
        BinarySearchTree tree = new BinarySearchTree();
        sizeList.add(tree.insertNode(5));
        sizeList.add(tree.insertNode(5));
        sizeList.add(tree.insertNode(15));
        sizeList.add(tree.insertNode(2));
        sizeList.add(tree.insertNode(12));
        sizeList.add(tree.insertNode(4));

        tree.removeNode(15, tree.root);

        tree.removeNode(5, tree.root);

        int max = Collections.max(sizeList) + 1;

        System.out.println("Height of tree: " + max);

        System.out.println("Smallest: " + tree.findSmallest(tree.root));

        System.out.println("Largest: " + tree.findLargest(tree.root));

        System.out.print("Inorder:");
        tree.printInOrder(tree.root);

        System.out.println();

        System.out.print("Preorder:");
        tree.printPreOrder(tree.root);

        System.out.println();

        System.out.print("Postorder:");
        tree.printPostOrder(tree.root);

        System.out.println();

        if (tree.elementSearch(3, tree.root))
        {
            System.out.println("The value 3 is in the tree.");
        }
        else
            System.out.println("The value 3 is not in the tree.");

        if (tree.elementSearch(2, tree.root))
        {
            System.out.println("The value 2 is in the tree.");
        }
        else
            System.out.println("The value 2 is not in the tree.");

        System.out.println("The fifth value is " + tree.findNth(5, tree.root));

        depthFirstSearch(12, tree.root);

        breadthFirstSearch(5, tree.root);
    }
}
