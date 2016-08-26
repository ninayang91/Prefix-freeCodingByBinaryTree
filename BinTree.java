import java.util.ArrayList;

public class BinTree {
	// Prefix-free code like{0,10,110,111} not {0,10,100} since 10 is the prefix of 100
	// Prefix-free code could be used to encode a sequence of symbols
	//c0 0, c1 10, c2 110, c3 111
	//11011110110000 <-> c2c3c1c2c0c0c0 prefix-free code make the conversion unique
	//left node means it stores a symbol with prefix-free code(****0)
	//right node means it stores a symbol with prefix-free code(****1)
	TNode root;
	
	public BinTree(){
		root=new TNode(null,null,null);
	}
	
	public BinTree(BinTree leftTree, BinTree rightTree){
		this();
		if(leftTree!=null) root.left=leftTree.root;
		if(rightTree!=null) root.right=rightTree.root;
	}
	
	//each string in a[] is a binary sequence (a[0]="0",a[1]="10",a[2]="110") representing a codeword
	//a[i] corresponds to c i, so leaf stores String "c"+i
	//if a[] contains two identical codewords or a codeword is a prefix of another one, throw Exception
	public BinTree(String[] a) throws IllegalArgumentException{
		//check each string, if it contains 1, go right, if it contains 0, go left
		//in every step, check if its data contains ci, if so, there are prefix, throw exception
		//when reach the end, create new Node. 
		//still need to check, if it contains left or right, it maybe the prefix of previous string
		root=new TNode(null,null,null);
		for(int i=0;i<a.length;i++){//loop through all strings in String[] a
			TNode temp=root;
			for(int j=0;j<a[i].length();j++){//loop through all "1" and "0" in String a[i]
				char ch=a[i].charAt(j);
				if(ch=='0'){//go to left
					if(temp.left!=null){
						if(temp.left.data!=null){
							throw new IllegalArgumentException("Prefix conflict");
						}else{//temp.left.data==null
							//go ahead
							//if it is the last char, outside j loop, store "c"+i
						}
						
					}else{//temp.left==null
						temp.left=new TNode(null,null,null);
						
					}
					temp=temp.left;

					
				}else{//ch=='1'//go to right
					if(temp.right!=null){
						if(temp.right.data!=null){
							throw new IllegalArgumentException("Prefix conflict");
						}else{//temp.left.data==null
							//go ahead
							//if it is the last char, outside j loop, store "c"+i
						}
						
					}else{//temp.left==null
						temp.right=new TNode(null,null,null);
						
					}
					temp=temp.right;
				}
				//check a[i].charAt[j], update pointer temp
				
			}
			if(temp.right!=null || temp.left!=null){
				throw new IllegalArgumentException("Prefix conflict");
			}

			temp.data="c"+i;
		}
	}
	
	public ArrayList<String> getCodewords(){
		ArrayList<String> l=new ArrayList<>();
		return getCodewords(l,root,"","");
	}
	//return Arraylist representing the set of codewords corresponding to this BinTree
    //codeword is "110"
	public ArrayList<String> getCodewords(ArrayList<String> l, TNode t, String one, String two){
		if(t.data!=null){//reach the leaf
			l.add(one+two);
		}else{
			if(t.left!=null){//Do we need to add this if?
				getCodewords(l,t.left,one+two,"0");//keep adding to l
			}
			if(t.right!=null){//Do we need to add this if?
				getCodewords(l,t.right,one+two,"1");
			}
		}
		return l;
	}
	
	public String[] convert(){ 
		//String a[0]=null
		//Any array element corresponding to a missing node stores null;
		//Any array element corresponding to an internal node stores "I";
		//Any array element corresponding to a leaf stores "c"+i;
		//level order traverse the BinTree, pretend it is a full, when no node, enqueue two nodes
		//uses a counter(Math.pow(2, (height()+1)) to stop enqueue 
		int length = (int)Math.pow(2, (height()+1));
		String[] a=new String[length];
		a[0]=null;
		int i=1;
		MyQueue<TNode> queue=new MyQueue<>();
		TNode reference =new TNode("reference",null,null);
		queue.enqueue(root);int counter=1;
		
		//while(counter<(int)Math.pow(2, height()+1)){//!queue.isEmpty()
		while(i<length){
			TNode temp=queue.dequeue();
			if(temp.data=="reference"){
				a[i++]=null;
			}else if(temp.data==null){
				a[i++]="I";
			}else if(temp.data!=null){
				a[i++]=temp.data;
			}
			if(counter<(int)Math.pow(2, height()+1)){//Avoid unnecessary enqueue
			if(temp.left!=null){
				queue.enqueue(temp.left);counter++;
			}else{
				queue.enqueue(reference);counter++;
			}
			if(temp.right!=null){
				queue.enqueue(temp.right);counter++;
			}else{
				queue.enqueue(reference);counter++;
			}
			}

		}
		System.out.println("Counter="+counter);
		for(int j=0;j<a.length;j++){
        	System.out.print(a[j]+" ");
        }
        System.out.println();
		return a;
	}	
	
	public ArrayList<String> decode(String s){
		//output the sequence of alphabet symbols obtained by decoding
		ArrayList<String> l=new ArrayList<>();
		TNode temp=root;
		for(int i=0;i<s.length();i++){
			
			if(s.charAt(i)=='0'){//go to left
				temp=temp.left;
			}else{//s.charAt(i)=='1'
				temp=temp.right;
			}
			
			if(temp==null){
				System.out.println("No solution");
				return null;
			}else if(temp.data!=null){
				l.add(temp.data);
				temp=root;
			}

		}
		if(temp!=root){//when the last string "1" cannot solve at leaf
			System.out.println("No solution");
			return null;
		}
		return l;
		
	}
		
/*		int length = (int)Math.pow(2, (height()+1));
		ArrayList<String> l = getCodewords();
        String[] a = new String[length];
        a[0] = null; //"dummy header"  

        for(int i=0;i<l.size();i++){
        	String s=l.get(i);//s="0"//s="10"
        	//System.out.println(s);
        	int index=1;
        	for(int j=0;j<s.length();j++){
        		a[index]="I";
        		if(s.charAt(j)=='0'){
        			index=index*2;
        		}else{//s.charAt(j)=='1'
        			index=index*2+1;
        		}
        	}
        	a[index]=s;
        }
        for(int i=0;i<a.length;i++){
        	System.out.print(a[i]+" ");
        }
        System.out.println();
        return a;
	
	}*/
/*	public ArrayList<String> decode(String s){//output the sequence of alphabet symbols obtained by decoding
        ArrayList<String> l = new ArrayList<String>();
        String[] a = convert();
        while (s.length() !=0){
            for (int i =0; i < a.length; i++){//check alphabets one by one
          
                if ((a[i] != null) && (s.startsWith(a[i].toString()))){//if alphabet is valid
                    l.add(a[i]);
                    s = s.substring(a[i].length()); 
                    break; 
                }
            }
        }
        return l;
    }*/

/*	public ArrayList<String> decode(String s){//s=01010...//return c1,c2,c2..
		
	}*/
	
	public void printTree(){//Inorder traversal//print "I" for each internal node
		printTree(root);
	}
	
	public void printTree(TNode root){
		if(root!=null){
			printTree(root.left);
			if(root.data==null){
				System.out.print("I ");
			}else{
				System.out.print(root.data+" ");
			}
			printTree(root.right);
		}
	}
	
	public int height(){
		return height(root);
	}
	
	private int height(TNode t){
		if(t!=null){
			return 1+Math.max(height(t.left), height(t.right));
		}else{
			return -1;
		}
	}

	public void printLevelOrder() throws IllegalArgumentException{
		//throw exception when attempting to dequeue an empty queue
		//using MyQueue<E> to print the tree nodes in level order
		//if data==null, print "I"
		MyQueue<TNode> queue=new MyQueue<>();
		queue.enqueue(root);
		if(queue.isEmpty()) throw new IllegalArgumentException("Tree is empty");
		while(!queue.isEmpty()){
			TNode temp=queue.dequeue();
			if(temp.data==null){
				System.out.print("I ");
			}else{
				System.out.print(temp.data+" ");
			}
			if(temp.left!=null)queue.enqueue(temp.left);
			if(temp.right!=null)queue.enqueue(temp.right);
			
		}
		
	}
}
