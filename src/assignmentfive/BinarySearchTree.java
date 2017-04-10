package assignmentfive;

import java.io.*;
import java.util.*;
/**
 * BinarySearchTree.java
 * Purpose: Implements a binary search tree data structure.
 * @author Nathaniel Sigafoos
 * @version 1.2 4/6/17
 */
public class BinarySearchTree<E extends Comparable<E>> extends AbstractTree<E> {

    protected TreeNode<E> root;
    protected int size = 0;

    /** Create a default binary tree */
    public BinarySearchTree() { }

    /** Create a binary tree from an array of objects */
    public BinarySearchTree(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            insert(objects[i]);
    }

    /** Returns true if the element is in the tree */
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root
        while (current != null) {
            if (e.compareTo(current.element) < 0)
                current = current.left;
            else if (e.compareTo(current.element) > 0)
                current = current.right;
            else // element matches current.element
                return true; // Element is found
        }
        return false;
    }
    /**
     * Returns true if the element is in the tree
     * also count the number of comparisons
     * @param e Tye element being searched for
     * @param count The array whose 0 index will track the number of comparisons 
     */
    public boolean search(E e, int[] count) {
        TreeNode<E> current = root; // Start from the root
        while (current != null) {
            count[0]++;
            if (e.compareTo(current.element) < 0)
                current = current.left;
            else if (e.compareTo(current.element) > 0)
                current = current.right;
            else // element matches current.element
                return true; // Element is found
        }
        return false;
    }

    /** Insert element o into the binary tree
     * Return true if the element is inserted successfully. 
     *  Uses an iterative algorithm
     */
    public boolean insert(E e) {
        if (root == null)
            root = createNewNode(e); // Create a new root
        else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                }
                else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                }
                else
                    return false; // Duplicate node not inserted
            }
            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0)
                parent.left = createNewNode(e);
            else
                parent.right = createNewNode(e);
        }
        size++;
        return true; // Element inserted
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<E>(e);
    }

    /** Inorder traversal from the root*/
    public void inorder() {
        inorder(root);
    }

    /** Inorder traversal from a subtree */
    protected void inorder(TreeNode<E> root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    
    /** Postorder traversal from the root */
    public void postorder() {
        postorder(root);
    }

    /** Postorder traversal from a subtree */
    protected void postorder(TreeNode<E> root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    /** Preorder traversal from the root */
    public void preorder() {
        preorder(root);
    }

    /** Preorder traversal from a subtree */
    protected void preorder(TreeNode<E> root) {
        if (root == null) return;
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    /** Inner class tree node */
    public static class TreeNode<E extends Comparable<E>> {
        E element;
        TreeNode<E> left;
        TreeNode<E> right;
        public TreeNode(E e) {
            element = e;
        }
    }

    /** Get the number of nodes in the tree */
    public int getSize() {
        return size;
    }

    /** Returns the root of the tree */
    public TreeNode getRoot() {
        return root;
    }
    
    /** 
     * Returns an ArrayList containing elements in the path from the root 
     * leading to the specified element, returns an empty ArrayList if no
     *   such element exists.
     * @param e The element that the path leads up to.
     * @return The path to the element given.
     */
    public ArrayList<E> path(E e) {
        ArrayList<E> list = new ArrayList<>();
        TreeNode<E> current = root; // Start from the root
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                //look to the left
                list.add(current.element);
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                //look to the right
                list.add(current.element);
                current = current.right;
            } else {
                //element found
                list.add(current.element);
                return list;
            }
        }
        //the element is not in the tree
        //return an empty ArrayList
        return new ArrayList<>();
    }
    /**
     * Returns the number of leaf nodes in this tree,
     * returns 0 if tree is empty
     * @return The number of leaves this tree has.
     */
    public int getNumberOfLeaves() {
        int numOfLeaves = 0;
        Stack<TreeNode<E>> nodesLeft = new Stack<>();
        if (root != null)
            nodesLeft.add(root);
        while (!nodesLeft.empty()) {
            TreeNode<E> n = nodesLeft.pop();
            if (n.right == null && n.right == null)
                numOfLeaves++;
            if (n.right != null) 
                nodesLeft.add(n.right);
            if (n.left != null)
                nodesLeft.add(n.left);
        }
        return numOfLeaves;
    }
    /**
     * Returns an ArrayList containing all elements in preorder of the
     *  specified element’s left sub-tree, returns an empty ArrayList if no
     *  such element exists. 
     */
    public ArrayList<E> leftSubTree(E e) {
        ArrayList<E> elements = new ArrayList<>();
        TreeNode<E> current = root;
        //find the element
        while (current != null) {
            if (e.compareTo(current.element) < 0)
                current = current.left;
            else if (e.compareTo(current.element) > 0)
                current = current.right;
            else
                break;
        }
        //make sure we found the element
        if (current == null)
            return elements;
        Stack<TreeNode<E>> nodes = new Stack<>();
        if (current.left != null)
            nodes.add(current.left);
        while (!nodes.empty()) {
            TreeNode<E> n = nodes.pop();
            elements.add(n.element);
            if (n.right != null)
                nodes.add(n.right);
            if (n.left != null)
                nodes.add(n.left);
        }
        return elements;
    }
    /**
     * Returns an ArrayList containing all elements in preorder of the 
     *  specified element’s right sub-tree, returns an empty ArrayList if no
     *  such element exists. 
     */
    public ArrayList<E> rightSubTree(E e) {
        ArrayList<E> elements = new ArrayList<>();
        TreeNode<E> current = root;
        //find the element
        while (current != null) {
            if (e.compareTo(current.element) < 0)
                current = current.left;
            else if (e.compareTo(current.element) > 0)
                current = current.right;
            else
                break;
        }
        //make sure we found the element
        if (current == null)
            return elements;
        Stack<TreeNode<E>> nodes = new Stack<>();
        if (current.right != null)
            nodes.add(current.right);
        while (!nodes.empty()) {
            TreeNode<E> n = nodes.pop();
            elements.add(n.element);
            if (n.right != null)
                nodes.add(n.right);
            if (n.left != null)
                nodes.add(n.left);
        }
        return elements;
    }
    /**
     * Returns true if this BinarySearchTree is structurally identical
     * to the one given.
     * @param tree The tree being comapred to this one.
     * @return Whether not this tree is identical to the one given.
     */
    public boolean sameTree(BinarySearchTree<E> tree) {
        return sameTree(this.root, tree.root);
    }
    /**
     * Returns whether or not 2 TreeNodes are identical.
     * Helper method for overloaded sameTree.
     * @param root1 The root of the 1st tree being compared.
     * @param root2 The root of the 2nd tree being compared.
     * @return Whether or not the trees the root are a part of are identical.
     */
    private boolean sameTree(TreeNode<E> root1, TreeNode<E> root2) {
        if (root1 == null && root2 == null)
            return true;
        if (root == null || root2 == null)
            return false;
        if (root1.element.equals(root2.element))
            return sameTree(root1.left, root2.left)
                && sameTree(root1.right, root2.right);
        return false;
    }
        
    /** Delete an element from the binary tree.
     * Return true if the element is deleted successfully
     * Return false if the element is not in the tree */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            }
            else
                break; // Element is in the tree pointed by current
        }
        if (current == null)
            return false; // Element is not in the tree
        // Case 1: current has no left children
        if (current.left == null) {
        // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            }
            else {
                if (e.compareTo(parent.element) < 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
        } else {
            // Case 2 & 3: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }
            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;
            
            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost)
                parentOfRightMost.right = rightMost.left;
            else
                // Special case: parentOfRightMost == current
                parentOfRightMost.left = rightMost.left;
        }
        size--;
        return true; // Element inserted
    }
    
    /** Obtain an iterator. Use inorder. */
    public java.util.Iterator iterator() {
        return inorderIterator();
    }

    /** Obtain an inorder iterator */
    public java.util.Iterator inorderIterator() {
        return new InorderIterator();
    }

    // Inner class InorderIterator
    class InorderIterator implements java.util.Iterator {
        // Store the elements in a list
        private java.util.ArrayList<E> list = new java.util.ArrayList<E>();
        private int current = 0; // Point to the current element in list

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }
        /** Inorder traversal from the root*/
        private void inorder() {
            inorder(root);
        }
        /** Inorder traversal from a subtree */
        private void inorder(TreeNode<E> root) {
            if (root == null) return;
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }
        /** Next element for traversing? */
        public boolean hasNext() {
            if (current < list.size())
                return true;
            return false;
        }
        /** Get the current element and move cursor to the next */
        public Object next() {
            return list.get(current++);
        }

        /** Remove the current element and refresh the list */
        public void remove() {
            delete(list.get(current)); // Delete the current element
            list.clear(); // Clear the list
            inorder(); // Rebuild the list
        }
    }

    /** Remove all elements from the tree */
    public void clear() {
        root = null;
        size = 0;
    }
}
