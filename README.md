# Prefix-freeCodingByBinaryTree
Use binary tree to create prefix-free codewords, + decode

Similar with Huffman Coding Tree
	// Prefix-free code like{0,10,110,111} not {0,10,100} since 10 is the prefix of 100

	// Prefix-free code could be used to encode a sequence of symbols

	//c0 0, c1 10, c2 110, c3 111

	//11011110110000 <-> c2c3c1c2c0c0c0 prefix-free code make the conversion unique

	//left node means it stores a symbol with prefix-free code(****0)

	//right node means it stores a symbol with prefix-free code(****1)

Bulid Binary Tree based on input array String[] a={"111","1101","1100","0","10"};

//each string in a[] is a binary sequence (a[0]="0",a[1]="10",a[2]="110") representing a codeword

//a[i] corresponds to c i, so leaf stores String "c"+i

//if a[] contains two identical codewords or a codeword is a prefix of another one, throw Exception

		//check each string, if it contains 1, go right, if it contains 0, go left
		//in every step, check if its data contains ci, if so, there are prefix, throw exception
		//when reach the end, create new Node. 
		//still need to check, if it contains left or right, it maybe the prefix of previous string

Get Codewords, using recusion to find the leaf, use String one and String two to record the path (right 1, left 0)
//return Arraylist representing the set of codewords corresponding to this BinTree

Convert the binary Tree to String a[]
		//String a[0]=null
		//Any array element corresponding to a missing node stores null;
		//Any array element corresponding to an internal node stores "I";
		//Any array element corresponding to a leaf stores "c"+i;
		//level order traverse the BinTree, pretend it is a full, when no node, enqueue two nodes

Decode the input String s
//output the sequence of alphabet symbols obtained by decoding
//Traverse s, follow the char in s, go to the corresponding path, find the ci, add into list
//if go to a no-exsitence node, return no solution
//if stop at non-root, then no solution
