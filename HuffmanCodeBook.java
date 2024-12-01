//Bilal Ahmed

package student;

import provided.BinarySequence;

import java.util.Iterator;

/**
 * This interface allows a user to append and access an element in an ordered sequence without worrying about size.
 *
 * @param <T> Any generic element used to make the sequence.
 * @author Bilal Ahmed
 */
interface List<T> {
    /**
     * Appends the specified element to the end of this list.
     *
     * @param data the specific element to be added to the end of the list.
     */
    void append(T data);

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index value of the specified position in the list.
     * @return the element object found at the specified position.
     * @throws IndexOutOfBoundsException when index is greater than the size of the list.
     */
    T get(int index) throws IndexOutOfBoundsException;

    /**
     * Returns the number of elements in this list.
     *
     * @return int representing the size of the list.
     */
    int getSize();

    //For this project, other list methods were not needed:
    //I.E, pop, remove, clear, etc.
}

/**
 * This class allows the user to hold a char and a BinarySequence simultaneously.
 * Main purpose to be used in an ArrayList to hold two connected sets of data at one index.
 *
 * @author Bilal Ahmed
 */
class DataHolder implements Comparable {
    /**
     * A character representing a letter.
     */
    private final char letter;
    /**
     * A binarySequence associated with a letter.
     */
    private final BinarySequence sequence;

    /**
     * Constructor that initializes the DataHolder object.
     *
     * @param c   the given letter
     * @param seq the given sequence associated with the given letter.
     */
    DataHolder(char c, BinarySequence seq) {
        this.letter = c;
        this.sequence = seq;
    }

    /**
     * Get the letter.
     *
     * @return a character representing the letter.
     */
    public char getLetter() {
        return letter;
    }

    /**
     * Get the associated BinarySequence.
     *
     * @return a BinarySequence object.
     */
    public BinarySequence getSequence() {
        return sequence;
    }

    /**
     * Compares this object with the specified object for order.
     *
     * @param o the object to be compared with.
     * @return Returns a negative integer, zero, or a positive integer
     * * if this object is less than, equal to, or greater than the specified object.
     * @throws ClassCastException if the specified object's type prevents it from being compared to this object.
     */
    @Override
    public int compareTo(Object o) {
        if (o instanceof DataHolder param) {
            if (this.letter < param.letter) {
                return 1;
            } else if (this.letter > param.letter) {
                return -1;
            } else {
                return 0;
            }
        }
        //Not an instance of DataHolder
        throw new ClassCastException("Specified object's type prevents it from being compared to DataHolder object.");

    }

}

/**
 * This class represents the “codebook” of the Huffman coding process, that is, it tells us, for each letter –
 * what is the correct binary sequence. When encoding files, the Huffman CodeBook class is
 * used to encode files – transform them from a series of letters, to a compact binary sequence.
 *
 * @author Bilal Ahmed
 */
public class HuffmanCodeBook implements Iterable<DataHolder> {


    /**
     * @param <T> a generic object that extends Comparable
     */
    public static class ArrayList<T extends Comparable> implements List<T>, Iterable<T> {

        /**
         * The size of the ArrayList.
         */

        private int size;
        /**
         * The generic array, representing the arrayList.
         */
        private T[] dataArray;

        /**
         * Constructor that initializes an ArrayList object.
         */
        public ArrayList() {
            this.size = 0;
            this.dataArray = (T[]) new Comparable[10];
        }

        /**
         * Appends the specified element to the end of this list.
         *
         * @param data the specific element to be added to the end of the list.
         */
        @Override
        public void append(T data) {
            if (isFull()) {
                expandArray();
                append(data);
            } else {
                dataArray[size] = data;
                size++;
            }

            int lastIndex = size - 1;
            while (lastIndex > 0 && (this.dataArray[lastIndex] != null)
                    &&
                    (this.dataArray[lastIndex].compareTo(this.dataArray[lastIndex - 1]) > 0)) {

                T temp = this.dataArray[lastIndex];
                this.dataArray[lastIndex] = this.dataArray[lastIndex - 1];
                this.dataArray[lastIndex - 1] = temp;
                lastIndex--;
            }
        }


        /**
         * Returns the element at the specified position in this list.
         *
         * @param index index value of the specified position in the list.
         * @return the element object found at the specified position.
         * @throws IndexOutOfBoundsException when index is greater than the size of the list.
         */
        @Override
        public T get(int index) throws IndexOutOfBoundsException {
            if (index >= size || index < 0) {
                System.out.println(size);
                throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
            } else {
                return dataArray[index];
            }
        }

        /**
         * Returns whether the ArrayList is full.
         *
         * @return a boolean indicating whether the arraylist is full or not.
         */
        private boolean isFull() {
            return this.size == this.dataArray.length;
        }

        /**
         * Function that expands the array to hold more items.
         * Used when the array, representing the ArrayList, is full.
         */
        private void expandArray() {
            T[] newArr = (T[]) new Comparable[dataArray.length * 2];
            for (int i = 0; i < dataArray.length; i++) {
                newArr[i] = dataArray[i];
            }
            this.dataArray = newArr;
        }

        /**
         * Returns the number of elements in this list.
         *
         * @return int representing the size of the list.
         */
        @Override
        public int getSize() {
            return this.size;
        }

        /**
         * Allows the user to iterate over the ArrayList.
         *
         * @return an Iterator object
         * @see Iterator
         */
        @Override
        public Iterator<T> iterator() {
            return new ArrayListIterator();
        }

        /**
         * Private class that implements the iterator functionality.
         *
         * @author Bilal Ahmed
         */
        private class ArrayListIterator implements Iterator<T> {

            /**
             * Index of the ArrayList.
             */
            private int index = 0;

            /**
             * Returns true if the iteration has more elements.
             *
             * @return boolean indicating if iteration has more elements
             */
            @Override
            public boolean hasNext() {
                return (index < size);
            }

            /**
             * Returns the next element in the iteration.
             *
             * @return a generic type object.
             */
            @Override
            public T next() {
                return get(index++);
            }
        }


    }

    /**
     * The ArrayList that will represent the Huffman Code book class.
     */
    private ArrayList<DataHolder> arrayList;

    /**
     * Constructor that initializes the HuffmanCodeBook object.
     */
    public HuffmanCodeBook() {
        arrayList = new ArrayList<>();
    }

    /**
     * Adds a given character and binary sequence into the codeBook.
     *
     * @param c   the given character
     * @param seq the given associated sequence
     */
    public void addSequence(char c, BinarySequence seq) {
        arrayList.append(new DataHolder(c, seq));
    }

    /**
     * This function implements a binary search to search for a given character.
     * @param letter the given letter to be searched for.
     * @return an int representing the index that the letter was found in. If not found, returns -1
     */
    private int binarySearch(char letter) {
        int start = 0;
        int end = this.getLengthOfCodeBook() - 1;
        int mid = 0;

        while (start <= end) {
            mid = (start + end) / 2;
            if (this.getCharAtIndex(mid) < letter) {
                start = mid + 1;
            } else if (this.getCharAtIndex(mid) > letter) {
                end = mid - 1;
            } else {
                //returns where index was found.
                return mid;
            }
        }
        return -1;
    }

    /**
     * Returns whether the codebook contains a given letter.
     *
     * @param letter the character to be searched for.
     * @return a boolean representing whether the codebook was found or not.
     */
    public boolean contains(char letter) {
        return binarySearch(letter) != -1;
    }


    /**
     * Used to see if a given codeBook can handle a given piece of text.
     * Does so by checking if the codebook has all the characters in a given string.
     *
     * @param letters string of letters that will be checked.
     * @return boolean indicating whether the codeBook can handle the given text.
     */
    public boolean containsAll(String letters) {
        for (int i = 0; i < letters.length(); i++) {
            if (!(contains(letters.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the binary sequence associated with the given letter.
     *
     * @param c the letter that will be searched for its corresponding binary sequence.
     * @return the associated binary sequence
     */
    public BinarySequence getSequence(char c) {
        int index = binarySearch(c);
        if (index != -1) {
            return this.getBinarySequenceAtIndex(index);
        } else {
            return null;
        }
    }


    /**
     * Encodes the input string into a binary sequence.
     *
     * @param s the input string.
     * @return am encoded string represented with a BinarySequence.
     */
    public BinarySequence encode(String s) {
        BinarySequence newSeq = new BinarySequence();
        for (int i = 0; i < s.length(); i++) {
            newSeq.append(getSequence(s.charAt(i)));
        }
        return newSeq;
    }

    ///////////////////////////////////////////////////////
    // Iteration Methods below.
    ///////////////////////////////////////////////////////

    /**
     * Get the length of the code book.
     *
     * @return int representing the size of the code book.
     */
    public int getLengthOfCodeBook() {
        return this.arrayList.getSize();
    }

    /**
     * Get the data at a given index.
     *
     * @param index int representing the given index.
     * @return a DataHolder which contains the letter and its associated char.
     * @throws IndexOutOfBoundsException when index is greater than the size of the list.
     * @see DataHolder
     */
    public DataHolder getData(int index) {
        if (index >= getLengthOfCodeBook() || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
        } else {
            return this.arrayList.get(index);
        }
    }

    /**
     * Get the character/letter at a given index.
     *
     * @param index int representing the given index.
     * @return a char representing the character at an index.
     * @throws IndexOutOfBoundsException when index is greater than the size of the list.
     */
    public char getCharAtIndex(int index) {
        if (index >= getLengthOfCodeBook() || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
        } else {
            return this.arrayList.get(index).getLetter();
        }
    }

    /**
     * Get the BinarySequence at a given index.
     *
     * @param index int representing the given index.
     * @return a BinarySequence representing the binarySequence at the given index.
     * @throws IndexOutOfBoundsException when index is greater than the size of the list.
     */
    public BinarySequence getBinarySequenceAtIndex(int index) {
        if (index >= getLengthOfCodeBook() || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
        } else {
            return this.arrayList.get(index).getSequence();
        }
    }


    /**
     * Allows the user to iterate over the codeBook.
     *
     * @return an Iterator object
     * @see Iterator
     */
    @Override
    public Iterator iterator() {
        return new HuffmanCodeBookIterator();
    }

    /**
     * Private class that implements the iterator functionality.
     *
     * @author Bilal Ahmed
     */
    private class HuffmanCodeBookIterator implements Iterator<DataHolder> {

        /**
         * Index of the codebook.
         */
        private int index = 0;

        /**
         * Returns true if the iteration has more elements.
         *
         * @return boolean indicating if iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return (index < arrayList.getSize());
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return DataHolder object.
         * @see DataHolder
         */
        @Override
        public DataHolder next() {
            return arrayList.get(index++);
        }
    }
}
