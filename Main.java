
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
/*		BinTree leftTree2=new BinTree();
		BinTree leftTree=new BinTree(leftTree2,null);
		BinTree t=new BinTree(null,leftTree);
		System.out.println(leftTree2.height());*/
		
		String[] a={"111","1101","1100","0","10"};
		BinTree t;
		try{
			t=new BinTree(a);
			System.out.println("Print tree:");
			t.printTree();
			System.out.println("\n***************");
			
			System.out.println("Print tree in level order:");
			t.printLevelOrder();
			System.out.println("\n***************");
			
			System.out.println("Get Codewords:");
			System.out.println(t.getCodewords());
			System.out.println("\n***************");
			
			System.out.println("Convert to array");
			t.convert();
			System.out.println("\n***************");
			
			System.out.println("Decode 1111011011010:");
			System.out.println(t.decode("1111011011010"));
			System.out.println("\n***************");
		}catch(IllegalArgumentException e){
			System.out.println(e);
		}
		
		
		

	}

}
