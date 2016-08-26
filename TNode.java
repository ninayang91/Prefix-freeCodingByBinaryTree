
public class TNode {
	
	TNode left,right;
	String data;
//internal node's data is null, leaf's data is the corresponding symbol(a string)	
	public TNode(String s, TNode l, TNode r){
		data=s;
		left=l;
		right=r;
	}

}
