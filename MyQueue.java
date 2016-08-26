
public class MyQueue<E> {//queues based on linked lists 

	
	private Node<E> head, tail;
	
	public MyQueue(){
		//tail = new Node<E>(null, null);
		head = new Node<E>(null, null);
	    tail=head;
	}

	public int getSize() {
		// TODO Auto-generated method stub
		int size=0;
		for(Node<E> p=head.next;p!=null; p=p.next){
			size++;
		}
		return size;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (head==tail);
	}

	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}


	public void enqueue(E e) {
		// TODO Auto-generated method stub
		Node<E> p=new Node<E>(e,null);
		tail.next=p;
		tail=p;
		
	}

	public E dequeue() {
		// TODO Auto-generated method stub
		if(isEmpty()){
			System.out.println("Queue is already empty");
			return null;
		}else{
			if(head.next==tail){
				tail=head;
			}
			E e=head.next.e;
			head.next=head.next.next;
			return e;
		}
		
	}

}
	


