import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 */

/**
 * @author berina
 * @param <T>
 *
 */
public class Deque<Item> implements Iterable<Item> {

	private Node start;
	private Node end;
	private int size;

	private class Node {
		private Item item;
		private Node next;
		private Node previous;
	}

	/* construct an empty deque */
	public Deque() {
		size = 0;
		start = null;
		end = null;
	}

	/* is the deque empty? */
	public boolean isEmpty() {
		return size == 0;
	}

	/* return the number of items on the deque */
	public int size() {
		return size;
	}
	
	private void checkItem(Item item) {
		if (item == null)
			throw new IllegalArgumentException("Item cannot be null");
	}

	/* add the item to the front */
	public void addFirst(Item item) {
		checkItem(item);
		Node n = new Node();
		n.item = item;
		n.previous = null;
		if (isEmpty()) {
			n.next = null;
			end = n;
		} else {
			n.next = this.start;
			start.previous = n;
		}
		start = n;
		size++;
	}

	/* add the item to the end */
	public void addLast(Item item) {
		checkItem(item);
		Node n = new Node();
		n.item = item;
		n.next = null;
		if (isEmpty()) {
			n.previous = null;
			start = n;
		} else {
			n.previous = end;
			end.next = n;
		}
		end = n;
		size++;
	}
	
	private void checkEmpty() {
		if (isEmpty())
			throw new NoSuchElementException("DeQue is empty");
	}

	/* remove and return the item from the front */
	public Item removeFirst() {
		checkEmpty();
		Item t = start.item;
		start = start.next;
		if (start != null) 
			start.previous = null;
		else
			end = null;
		size--;
		return t;
	}

	/* remove and return the item from the end */
	public Item removeLast() {
		checkEmpty();
		Item t = end.item;
		end = end.previous;
		if (end != null)
			end.next = null;
		else
			start = null;
		size--;
		return t;
	}

	/* return an iterator over items in order from front to end */
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {

		private Node current = start;

		public boolean hasNext()  {
			return current != null;                     
		}

		public void remove()      
		{
			throw new UnsupportedOperationException();  
		}

		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next; 
			return item;
		}
	}
	/* unit testing (optional) */
	public static void main(String[] args) {
		Deque<String> d = new Deque<>();
		d.addFirst("Hello");
		d.addFirst("Hi");
		d.addLast("Berina");
		System.out.println(d.removeFirst());
		d.addLast("Febin");
		System.out.println(d.removeLast());
		System.out.println(d.removeFirst());
		System.out.println(d.removeLast());
		d.addFirst("girl");
		System.out.println(d.removeLast());
	}

}
