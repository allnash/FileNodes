package com.filemapper;

import java.io.File;
import java.io.IOException;

/**
 * MyNodeMapperTests Test the following requirements
 * <ol>
 * <li>Given a path to a directory, create a tree representation of the
 * directory and its content.
 * <li>The function should return the root of the tree. Nodes represent
 * directories and files, leaves are files and empty directories.
 * <li>The node must have two methods; getChildren, that returns the node's list
 * of children, and getDetail, that returns file size for a file node, and for a
 * directory, the number of contents inside that directory.
 * <li>The program should work for windows and unix based file systems.
 * <li>Please include necessary unit test for your code. See
 * MyNodeMapperTest.java file
 * </ol>
 * <p>
 * All Nodes are of type GenericTreeNode
 * 
 * @author Nachiket Gadre (nash)
 * @version 1.0
 * @since 2015
 */

public class MyNodeMapperTests {
	
	private static final String TMP_DIR_NAME = "tmp";
	private static int TEST_COUNT = 0;

	public static void main(String[] args) {
		testDirNode();
		testFileNode();
		testDirectoryWithFile();
		System.out.println("Total Tests Run - " + TEST_COUNT);
	}
	
	private static void error(String error){
		System.out.println("Test Failed : " + Thread.currentThread().getStackTrace()[2].getMethodName() + " - " + error); 
	}

	private static void setup() {
		File dir = new File("tmp");
		dir.mkdirs();
	}

	private static void cleanup() {
		try {
			delete(new File(TMP_DIR_NAME));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testDirNode() {
		setup();
		TreeNode<File> dirNode = new TreeNode<File>(new File("tmp"));
		if((dirNode.children.size() > 0)) error("Wrong number of children");
		if(!dirNode.isLeaf()) error("Node should be a leaf");
		if(!dirNode.isLeaf()) error("Node should be a leaf");
		if(!dirNode.getData().isDirectory()) error("Node is a Dir Node");
		if(!(dirNode.getDetail() > 0)) error("Node a Dir Node");
		TEST_COUNT++;
		cleanup();
	}

	private static void testFileNode() {
		setup();
	    try {
			String tmp_path = TMP_DIR_NAME + File.separator + "test.txt";
			//(use relative path for Unix systems)
			File f = new File(tmp_path);
			//(works for both Windows and Linux)
			f.getParentFile().mkdirs(); 
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    TreeNode<File> root = MyNodeMapper.create(TMP_DIR_NAME);
		TreeNode<File> child = root.getChildAt(0);
		
		if(!(child.children.size() == 0)) error("Wrong number of children" + root.children.size() );
		if(!child.isLeaf()) error("Node should not be a leaf");
		if(child.hasChildren()) error("Child should not have children when its a file");
		if(((File)child.getData()).isDirectory()) error("Node is a Dir Node");
		if(!(root.getDetail() > 0)) error("Node a Dir Node");
		
		TEST_COUNT++;
		cleanup();
		

	}

	private static void testDirectoryWithFile() {
		setup();
		try {
			String tmp_path = TMP_DIR_NAME + File.separator + "test.txt";
			//(use relative path for Unix systems)
			File f = new File(tmp_path);
			//(works for both Windows and Linux)
			f.getParentFile().mkdirs(); 
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TreeNode<File> root = MyNodeMapper.create(TMP_DIR_NAME);
		if(!(root.children.size() == 1)) error("Wrong number of children" + root.children.size() );
		if(root.isLeaf()) error("Node should not be a leaf");
		if(!root.hasChildren()) error("Node should have 1 children");
		if(!((File)root.getData()).isDirectory()) error("Node is a Dir Node");
		if(!(root.getDetail() > 0)) error("Node a Dir Node");
		TEST_COUNT++;
		cleanup();
	}

	private static void delete(File file) throws IOException {

		if (file.isDirectory()) {

			// directory is empty, then delete it
			if (file.list().length == 0) {

				file.delete();

			} else {

				// list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);

					// recursive delete
					delete(fileDelete);
				}

				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
				}
			}

		} else {
			// if file, then delete it
			file.delete();
			}
	}

}
