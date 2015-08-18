package com.filemapper;

import java.io.File;
import java.util.List;

/**
 * MyNodeMapper is tool to map the Directory to n-node Tree
 * <p>
 * The requirements are
 * <ol>
 * <li>Given a path to a directory, create a tree representation of the directory and its content.
 * <li>The function should return the root of the tree.  Nodes represent directories and files, leaves are files and empty directories.
 * <li>The node must have two methods; getChildren, that returns the node's list of children, and getDetail, that returns file size for a file node, and for a directory, the number of contents inside that directory.
 * <li>The program should work for windows and unix based file systems.
 * <li> Please include necessary unit test for your code. See MyNodeMapperTest.java file
 * </ol>
 * <p>
 * All Nodes are of type TreeNode
 * 
 * @author      Nachiket Gadre (nash)
 * @version     1.0
 * @since       2015
 */

public class MyNodeMapper {

	public static void main(String[] args) {
    
	// Check how many arguments were passed in
	    if(args.length == 0 || args.length > 1)
	    {
	        System.out.println("Please Enter a Valid Input");
	        System.exit(0);
	    }
	// Create Our Tree    
	    TreeNode<File> root = create(args[0]);
        System.out.println("Tree Created --");
        System.out.println("Traversing Tree --");
    // Traverse Our Tree
        traverse(root);

	}
	
	/**
	 * This method creates a TreeNode tree for a given PATH
	 * of directory which becomes the path of the root node.
	 * <p>
	 *
	 * @param  dir String
	 * @return TreeNode
	 */
	
	public static TreeNode<File> create(String dir){
		TreeNode<File> root = new TreeNode<File>(new File(dir));
	    build(root);
	    return root;
	}

	/**
	 * This method recursively traverses our TreeNode. 
	 * The argument must specify an non null Generic Node
	 * <p>
	 * This method Prints the Nodes That are traversed on 
	 * on the screen. It Displays File Size for files and Count for 
	 * Directories
	 *
	 * @param  dir  a TreeNode
	 */
	public static void traverse(TreeNode<File> dir){
	    if (dir.getData().isDirectory()) {
            System.out.println(dir.getData().getAbsolutePath() + " Content Count:" + (int)dir.getDetail());
	    	List<TreeNode<File>> children = dir.getChildren();
	        for (int i = 0; children != null && i < dir.getNumberOfChildren(); i++) {
	            traverse(dir.getChildAt(i));
	        }
	    }
	    if (dir.getData().isFile()) {
	            System.out.println(dir.getData().getAbsolutePath() + " File Size:" + dir.getDetail());
	    }
	}
	
	/**
	 * This method recursively builds a Tree of Files and Directories by traversing from root. 
	 * The argument must specify an non-null TreeNode
	 * Note root node must be static or must be retrieved from the Caller functions.
	 * This function does not hold a reference to root, since we use recursion.
	 * <p>
	 *
	 * @param  dir  TreeNode
	 */
	public static void build(TreeNode<File> dir){
	    if (dir.getData().isDirectory()) {
	        String[] children = dir.getData().list();
	        for (int i = 0; children != null && i < children.length; i++) {
	            //System.out.println(children[i]);//change it if needed
	            TreeNode<File> child = new TreeNode<File>(new File(dir.getData(), children[i]));
	            dir.addChild(child);
	            build(child);
	        }
	    }
	    if (dir.getData().isFile()) {
	            //System.out.println(dir.getData().getAbsolutePath());//change it if needed
	    }
	}
}
