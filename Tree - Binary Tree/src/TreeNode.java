/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #4
 * CSE 214 Summer
 * R30 - Charlie Clark
 */

public class TreeNode {
    private TreeNode left; //Left reference to node
    private TreeNode middle; //Middle reference to node
    private TreeNode right; //Right reference to node

    private String label; //Name of node
    private String message; //Message of node
    private String prompt; //Prompt of node

    /**
     *
     * @param label
     * label
     * @param prompt
     * prompt
     * @param message
     * message
     *
     * Constructor that makes a TreeNode.
     */
    public TreeNode(String label, String prompt, String message) {
        this.label = label;
        this.prompt = prompt;
        this.message = message;
    }

    /**
     *
     * @return
     *
     * returns label
     */
    public String getLabel() {
        return label;
    }

    /**
     *
     * @return
     *
     * returns message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @return
     *
     * returns the prompt
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     *
     * @return
     *
     * returns the left reference
     */
    public TreeNode getLeft() {
        return left;
    }

    /**
     *
     * @return
     *
     * returns the middle reference
     */
    public TreeNode getMiddle() {
        return middle;
    }

    /**
     *
     * @return
     *
     * returns the right reference
     */
    public TreeNode getRight() {
        return right;
    }

    /**
     *
     * @param message
     *
     * sets the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @param label
     *
     * sets the label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     *
     * @param prompt
     *
     * sets the prompt
     */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    /**
     *
     * @param left
     *
     * sets the left reference
     */
    public void setLeft(TreeNode left) {
        this.left = left;
    }

    /**
     *
     * @param middle
     *
     * sets the middle reference
     */
    public void setMiddle(TreeNode middle) {
        this.middle = middle;
    }

    /**
     *
     * @param right
     *
     * sets the right reference
     */
    public void setRight(TreeNode right) {
        this.right = right;
    }

    /**
     *
     * @return
     *
     * returns true/false depending
     * on whether the node is a leaf or not.
     */
    public boolean isLeaf() {
        return left == null && right == null && middle == null;
    }
}