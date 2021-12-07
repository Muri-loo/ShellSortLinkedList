

import java.io.* ;
import java.util.* ;

class ShellSortLL {

	private static Node head, last;
	private static int count, cmp, exch;




	public ShellSortLL(){
		head=null;
		last=null;
		count=0;
		cmp=0;
		exch=0;
	}

	public boolean isEmpty(){
		return head==null;
	}

	public void enqueue(int item){
		Node oldlast = last;
		last = new Node();
		last.key = item;
		last.next = null;
		if (isEmpty()) head = last;
		else oldlast.next = last;
		count++;
	}

	private static class Node{
		int key;
		Node next;
	}
	
	private Node movepointerFrom(Node node, int distance){
		int i=0;
		if (distance<0) return null;
		while(i<distance){
			node=node.next;
			i=i+1;
		}
		return node;
	}

	private void SortTotal(){
		cmp=0;
		exch=0;
		if(count<1) return;
		int h = 1;
		while (h < count/3) h = 3*h + 1;
		while (h>1){
			shellSort(h);
			h/=3;
		}


		if (h == 1) {
			bubbleSort();
		}
		System.out.println("compare");
		System.out.println(cmp);
		System.out.println("exchanges");
		System.out.println(exch);
	}
	private void bubbleSort (){
		if(count<1) return;
		Boolean isChanged;
		Node Lchange=null;
		Node aux=null;
		do{
			Node prevNode = null;
			Node current = head;
			Node nextNode = current.next;
			isChanged = false;
			while ( nextNode != null) {
				if(nextNode==Lchange) break;
				cmp++;
				if (current.key > nextNode.key) {
					aux=current;
					exch++;
					if (head == current) {
						head = nextNode;
					} else {
						prevNode.next=nextNode;
					}
					current.next=nextNode.next;
					nextNode.next=current;
					isChanged = true;
					current = nextNode;
				}
				prevNode = current;
				current = prevNode.next;
				nextNode = current.next;
			}
			Lchange=aux;
		}while( isChanged );
	}

	private void shellSort(int h){
		Node current, nodeH, currentPrev, nodeHPrev;

		current = head;	
		currentPrev = null;	
		
		nodeHPrev=movepointerFrom(current,h-1);
		nodeH=movepointerFrom(nodeHPrev,1);
		
		boolean isChanged;
		isChanged=true;
		do{
			isChanged = false;

			while (nodeH!=null ) {
				cmp++;
				if (current.key > nodeH.key) {
					exch++;
					swap(currentPrev, current, nodeHPrev, nodeH); 

					Node aux1=current;
					current=nodeH;
					nodeH=aux1;

					if (currentPrev==null)currentPrev=head;
					else currentPrev=current;
					nodeHPrev=nodeH;
					current=current.next;
					nodeH=nodeH.next;

					isChanged = true;

				}else{
					if (currentPrev==null)currentPrev=head;
					else currentPrev=current;
					nodeHPrev=nodeH;
					current=current.next;
					nodeH=nodeH.next;
				}
			}


		}while (isChanged);
	}

	private void swap(Node prev1, Node node1, Node prev2, Node node2){
		if (node1 != null && node2 != null) {

			if (prev1 != null) prev1.next = node2;
			else head = node2;

			if (prev2 != null) prev2.next = node1;
			else	head = node1;

			Node temp = node1.next;
			node1.next = node2.next;
			node2.next = temp;
		}

	}
	public void setCount(int count){
		this.count=count;
	}

	public void display(){
		Node current = head;
		if (head == null) {
			System.out.println("List is empty");
			return;
		}
		while (current != null) {
			System.out.print(current.key + " ");
			current = current.next;
		}
		System.out.println();
	}
	public void readValues( ) {
		//Scanner scanner = new Scanner(System.in);
		//System.out.println("What file should we look at?");
		//String fileName = scanner.next();
		//File file = new File(fileName);		
		File file = new File("/Users/muril/Desktop/10k.txt");		
		Scanner scanner2;
		try {
			scanner2 = new Scanner(file);
			int initialData = scanner2.nextInt();
			//System.out.println(initialData);
			enqueue(initialData);
			while (scanner2.hasNext()) {
				int data = scanner2.nextInt();
				enqueue(data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		
		ShellSortLL sList = new ShellSortLL();
		sList.readValues();
		
		System.out.println("Original list: ");
		//sList.display();

		sList.SortTotal();

		System.out.println("List after swapping nodes: ");
		sList.display();
	}
}