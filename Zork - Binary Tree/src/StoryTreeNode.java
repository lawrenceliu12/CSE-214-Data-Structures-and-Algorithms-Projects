/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #5
 * CSE 214
 * R04 - James Finn / Taylor Ngo
 */
public class StoryTreeNode {
    static final String WIN_MESSAGE = "YOU WIN"; //Win message string
    static final String LOSE_MESSAGE = "YOU LOSE"; //Lose message string
    private String position; //Position string
    private String option; //Option of string
    private String message; //Message

    private StoryTreeNode leftChild; //Left Child
    private StoryTreeNode rightChild; //Right Child
    private StoryTreeNode middleChild; //Middle Child

    /**
     *
     * @param position
     * @param option
     * @param message
     * Constructor for StoryTreeNode
     */
    public StoryTreeNode(String position, String option, String message){ //Can add custom parameters
        this.position = position;
        this.option = option;
        this.message = message;
    }

    /**
     *
     * @return
     * returns position string
     */
    public String getPosition(){ //Getter for position String
        return position;
    }

    /**
     *
     * @return
     * Returns option string
     */
    public String getOption(){ //Getter for option String
        return option;
    }

    /**
     *
     * @return
     * Returns message string
     */
    public String getMessage(){ //Getter for message String
        return message;
    }

    /**
     *
     * @return
     * Returns Left Child Node
     */
    public StoryTreeNode getLeftChild(){ //Getter method for left child node
        return leftChild;
    }

    /**
     *
     * @return
     * Returns Middle Child Node
     */
    public StoryTreeNode getMiddleChild(){ //Getter method for middle child node
        return middleChild;
    }

    /**
     *
     * @return
     * Returns Right Child Node
     */
    public StoryTreeNode getRightChild(){ //Getter method for right child node
        return rightChild;
    }

    /**
     *
     * @param position
     */
    public void setPosition(String position){ //Setter method for position
        this.position = position;
    }

    /**
     *
     * @param option
     */
    public void setOption(String option){ //Setter method for option
        this.option = option;
    }

    /**
     *
     * @param message
     */
    public void setMessage(String message){ //Setter method for message
        this.message = message;
    }

    /**
     *
     * @param leftChild
     */
    public void setLeftChild(StoryTreeNode leftChild){ //Setter for left child node
        this.leftChild = leftChild;
    }

    /**
     *
     * @param middleChild
     */
    public void setMiddleChild(StoryTreeNode middleChild){ //Setter for middle child node
        this.middleChild = middleChild;
    }

    /**
     *
     * @param rightChild
     */
    public void setRightChild(StoryTreeNode rightChild){ //Setter for right child node
        this.rightChild = rightChild;
    }

    /**
     *
     * @return
     */
    public boolean isLeaf(){ //Checks if all child nodes are a leaf
        return leftChild == null && rightChild == null && middleChild == null;
    }

    /**
     *
     * @return
     */
    public boolean isWinningNode(){ //Checks if it is a winning node
        if (isLeaf() && message.contains(WIN_MESSAGE)){
            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    public boolean isLosingNode(){ //Checks if it is a losing node
        if (isLeaf() && message.contains(LOSE_MESSAGE)){
            return true;
        }
        return false;
    }

}
