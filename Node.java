
public class Node<E> {
	
	public Node<E> next;
	
	E e;
	
	public Node(E e, Node<E> next){
		this.e=e;
		this.next=next;
	}

}
