//Bilal Ahmed
package student;

import provided.BinarySequence;

/**
 * This class builds and maintains a binary tree that represents a collection of Huffman codes for various letters.
 * HuffmanCodeTree is designed to be efficient in computing a char from a binary sequence
 *
 * @author Bilal Ahmed
 */
public class HuffmanCodeTree {
    /**
     * The root of the HuffmanCodeTree.
     */
    private HuffmanNode root;

    /**
     * Constructor that initializes the HuffmanCode tree.
     *
     * @param root HuffmanNode representing the root of the tree.
     */
    public HuffmanCodeTree(HuffmanNode root) {
        this.root = root;
    }

    /**
     * Constructor that initializes the HuffmanCode tree.
     *
     * @param codebook HuffmanCodeBook that will be used to create the HuffmanCode tree.
     */
    public HuffmanCodeTree(HuffmanCodeBook codebook) {
        //Loop through codebook
        root = new HuffmanNode();
        for (DataHolder dataInCodeBook : codebook) {
            this.put(dataInCodeBook.getSequence(), dataInCodeBook.getLetter());
        }

    }

    /**
     * Determines whether the Huffman tree is valid.
     *
     * @return a boolean indicating whether the tree is valid or not.
     */
    public boolean isValid() {
        return root.isValidTree();
    }

    /**
     * This method modifies the huffman tree structure.
     * So that the node addressed by the binary sequence stores the given char
     *
     * @param seq    the BinarySequence of the given character.
     * @param letter the character that will be stored.
     */

    public void put(BinarySequence seq, char letter) {
        //True is 1,
        //False is 0
        HuffmanNode currentNode = this.root;
        for (Boolean bool : seq) {
            if (bool) {
                //True then one
                if (currentNode.getOne() == null) {
                    //Set
                    currentNode.setOne(new HuffmanNode());
                    currentNode = currentNode.getOne();
                    //currentNode = null;
                } else {
                    //Go to the next node
                    currentNode = currentNode.getOne();
                }
            } else {
                //False then 0
                if (currentNode.getZero() == null) {
                    //Set
                    currentNode.setZero(new HuffmanNode());
                    currentNode = currentNode.getZero();
                    //currentNode = null;
                } else {
                    //Go to the next node
                    currentNode = currentNode.getZero();
                }
            }
        }
        currentNode.setData(letter);
    }

    /**
     * This method decodes a BinarySequence into a string.
     *
     * @param s the BinarySequence that will be decoded.
     * @return a String representing the decoded BinarySequence.
     */
    public String decode(BinarySequence s) {
        StringBuilder sb = new StringBuilder();
        HuffmanNode node = this.root;
        for (Boolean bool : s) {
            if (bool) {
                //True thus get one;
                node = node.getOne();
            } else {
                //False thus get zero;
                node = node.getZero();
            }
            if (node.isLeaf()) {

                sb.append(node.getData());

                node = this.root;
            }
        }

        return sb.toString();
    }

    /**
     * Get root of the HuffmanCodeTree.
     * @return the root of the HuffmanCodeTree.
     */
    public HuffmanNode getRoot() {
        return root;
    }
}
