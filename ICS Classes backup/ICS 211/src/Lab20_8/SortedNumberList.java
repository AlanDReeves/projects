package Lab20_8;

/*
 * @author Alan Reeves
 */
public class SortedNumberList {
	public Node head;
	public Node tail;

	public SortedNumberList() {
		head = null;
		tail = null;
	}

   // Optional: Add any desired private methods here

   public void fixTail() {
    Node currNode = head;
    while (currNode.getNext() != null) {
        currNode = currNode.getNext();
    }
    tail = currNode;

   }
   
   
	// Inserts the number into the list in the correct position such that the
	// list remains sorted in ascending order.

   //trying to insert something equal to the head causes a null pointer exception

	public void insert(double number) {
	   Node newNode = new Node(number);
       //if the list is empty
       if (head == null) {
        head = newNode;


        //if the new node belongs before the head
        //place in front of head even if equal to head
       } else if (number <= head.getData()) {
        newNode.setNext(head);
        head.setPrevious(newNode);
        head = newNode;

        fixTail();

        //if there is only 1 value in the list
       } else if (head.getNext() == null) {
        head.setNext(newNode);
        newNode.setPrevious(head);

        fixTail();
       }


       //if a head and tail exist, but the new node is greater than head.data
       else {

        //cycle until data larger than number is found or null is reached
        Node currNode = head;
        while (currNode.getData() < number && currNode.getNext() != null) {
            currNode = currNode.getNext();
        }

        //puts newNode at the end if it is the largest value in the list
        if (currNode.getNext() == null && currNode.getData() < number) {
            currNode.setNext(newNode);
            newNode.setPrevious(currNode);

            fixTail();
        } 
        
        //if newNode is not the largest value
        else {
            Node before = currNode.getPrevious();

            before.setNext(newNode);
            newNode.setPrevious(before);

            newNode.setNext(currNode);
            currNode.setPrevious(newNode);

        }

        fixTail();
       }

	}

	// Removes the node with the specified number value from the list. Returns
	// true if the node is found and removed, false otherwise.
	public boolean remove(double number) {
	   
       //check if there is anything in the list
        if (head == null) {
        return false;
       }

       //check if the number to remove is the head
       if (head.getData() == number) {
        if (head.getNext() == null) {
            head = null;
            return true;
        } else {
            //know that there is at least one value after head
            head = head.getNext();
            return true;
        }
       }


       //all other cases
       //find either a node that matches or reach the tail
       Node currNode = head;
       while (currNode.getNext() != null && currNode.getData() != number) {
        currNode = currNode.getNext();
       }
       //check if currNode is the tail
       if (currNode.getNext() == null && currNode.getData() == number) {
        
        currNode.getPrevious().setNext(null);
        fixTail();
        return true;
       }
       //if node is found and is not the tail
       if (currNode.getData() == number) {
        Node previous = currNode.getPrevious();
        Node next = currNode.getNext();

        previous.setNext(next);
        next.setPrevious(previous);
        return true;
       }

	   
	   return false;
	}
}// end of SortedNumberList
