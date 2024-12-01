//Bilal Ahmed
package student;

/**
 * This class serves as a component of the HuffmanCodeTree class.
 * Its structure is similar to that of a binary tree node, with one small change:
 * The next nodes variables are one and zero.
 *
 * @author Bilal Ahmed
 */
public class HuffmanNode {


    /**
     * Character object representing the data of the node.
     */
    private Character character;
    /**
     * The zero child node.
     */
    private HuffmanNode zero;
    /**
     * The one child node.
     */
    private HuffmanNode one;

    /**
     * Constructor that initializes the HuffmanNode object.
     *
     * @param zero the zero child node.
     * @param one  the one child node.
     */
    public HuffmanNode(HuffmanNode zero, HuffmanNode one) {
        this.character = null;
        this.zero = zero;
        this.one = one;
    }

    /**
     * Constructor that initializes the HuffmanNode object.
     *
     * @param data a char representing the data of the node.
     */
    public HuffmanNode(char data) {
        this.character = data;
        this.zero = null;
        this.one = null;
    }

    /**
     * Constructor that initializes the HuffmanNode object.
     */
    public HuffmanNode() {
        this.character = null;
        this.zero = null;
        this.one = null;
    }

    /**
     * Determines whether the node is a leaf or not.
     *
     * @return a boolean indicating whether the node is a lead.
     */

    public boolean isLeaf() {
        return (this.character != null && (this.zero == null && this.one == null));
    }

    /**
     * Determines whether the node is an internal node or not.
     *
     * @return a boolean indicating whether the node is a internal node.
     */
    public boolean isInternalNode() {
        return (this.character == null && (this.zero != null && this.one != null));
    }

    /**
     * Determines whether the node is valid or not.
     *
     * @return a boolean indicating whether the node is valid.
     */
    public boolean isValidNode() {
        return (this.isLeaf() || this.isInternalNode());
    }

    /**
     * Get the data of the node.
     *
     * @return Character representing the data of the node.
     */
    public Character getData() {
        return character;
    }

    /**
     * Set the data of the node.
     *
     * @param character the Character object that will replace the current Character data.
     */
    public void setData(Character character) {
        this.character = character;
    }

    /**
     * Get the zero child node.
     *
     * @return Huffman node representing the zero child node.
     */
    public HuffmanNode getZero() {
        return zero;
    }

    /**
     * Set the zero child node.
     *
     * @param zero the zero Huffman node that will replace the current zero huffmanNode.
     */
    public void setZero(HuffmanNode zero) {
        this.zero = zero;
    }

    /**
     * Get the one child node.
     *
     * @return Huffman node representing the one child node.
     */
    public HuffmanNode getOne() {
        return one;
    }

    /**
     * Set the one child node.
     *
     * @param one the one Huffman node that will replace the current one huffmanNode.
     */
    public void setOne(HuffmanNode one) {
        this.one = one;
    }

    /**
     * Determines whether the HuffmanTree is valid.
     *
     * @return a boolean indicating whether the three is valid or not.
     */
    public boolean isValidTree() {
        return checkTreeValidity(this);
    }

    /**
     * Recursively checks if this node and all its descendents are valid.
     *
     * @param node the node that will be checked, and its descendents.
     * @return a boolean representing whether the tree is valid or not.
     */
    private boolean checkTreeValidity(HuffmanNode node) {
        //A Huffman code tree should only have two types of nodes: leaf nodes and internal nodes
        if (node == null) {
            return true;
        }
        if (node.isValidNode()) {
            return checkTreeValidity(node.one) && checkTreeValidity(node.zero);
        }
        return false;


    }
}
