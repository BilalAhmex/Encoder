# Encoder

## Project Overview
This project implements a Huffman coding system for efficient text compression and encoding. The implementation includes three core classes:

- `HuffmanCodeBook`: Manages the character-to-binary sequence mapping for encoding
- `HuffmanNode`: Represents nodes in the Huffman coding tree
- `HuffmanCodeTree`: Enables efficient decoding of binary sequences back to text

## Features
- Variable-length prefix encoding of text
- Efficient encoding and decoding mechanisms
- Custom data structure implementation without using built-in Java collections

## Requirements
- Java 8 or higher
- No external libraries required

## Project Structure
- `HuffmanCodeBook.java`: Encoding codebook implementation
- `HuffmanNode.java`: Binary tree node representation
- `HuffmanCodeTree.java`: Decoding tree implementation
- `README.md`: Project documentation

## Performance
- CodeBook methods (contains, getSequence): O(log n) runtime
- CodeTree decoding: O(b) runtime, where b is the number of bits in the binary sequence
