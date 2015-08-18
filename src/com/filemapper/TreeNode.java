package com.filemapper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * TreeNode Class provides a Generic Type Node definition
 * <p>
 * 
 * @author      Nachiket Gadre (nash)
 * @version     1.0
 * @since       2015
 */

public class TreeNode<T> {

	/** Generic Type field for holding data **/
    public T data;
	/** List of children of the parent node **/
    public List<TreeNode<T>> children;

    public TreeNode() {
        super();
        children = new ArrayList<TreeNode<T>>();
    }

    public TreeNode(T data) {
        this();
        setData(data);
    }

    public List<TreeNode<T>> getChildren() {
        return this.children;
    }
    
    public boolean isLeaf() {
        return (getNumberOfChildren() == 0);
    }

    public int getNumberOfChildren() {
        return getChildren().size();
    }

    public boolean hasChildren() {
        return (getNumberOfChildren() > 0);
    }

    public void setChildren(List<TreeNode<T>> children) {
        this.children = children;
    }

    public void addChild(TreeNode<T> child) {
        children.add(child);
    }

    public void addChildAt(int index, TreeNode<T> child) throws IndexOutOfBoundsException {
        children.add(index, child);
    }

    public void removeChildren() {
        this.children = new ArrayList<TreeNode<T>>();
    }

    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        children.remove(index);
    }

    public TreeNode<T> getChildAt(int index) throws IndexOutOfBoundsException {
        return children.get(index);
    }

    public T getData() {
        return this.data;
    }
    
    public double getDetail() {
        if(hasChildren()) { 
        	return (double)getNumberOfChildren() ;
        } else{ 
        return ((File)getData()).length();
        }
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        return getData().toString();
    }

    public boolean equals(TreeNode<T> node) {
        return node.getData().equals(getData());
    }

}