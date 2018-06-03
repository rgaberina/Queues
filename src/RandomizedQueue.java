import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 
 */

/**
 * @author berina
 *
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] queue;

	private int size;

	/* construct an empty randomized queue */
	public RandomizedQueue() {
		queue = (Item[]) new Object[2];
		size = 0;
	}

	/* is the randomized queue empty? */
	public boolean isEmpty() {
		return size == 0;
	}

	/* return the number of items on the randomized queue */
	public int size() {
		return size;
	}

	private void resize(int capacity) {
		if (capacity > 0) {
			assert capacity >= size;
			Item[] newq = (Item[]) new Object[capacity];
			for (int i = 0; i < size; i++) 
				newq[i] = queue[i];
			queue = newq;
		}
	}

	/* add the item */
	public void enqueue(Item item) {
		if (item == null)
			throw new IllegalArgumentException("Item cannot be null!");
		if (queue.length == size)
			resize(2 * queue.length);
		queue[size++] = item;
	}

	private void checkEmpty() {
		if (isEmpty())
			throw new NoSuchElementException("Queue is empty");
	}

	/* remove and return a random item */
	public Item dequeue() {
		checkEmpty();
		int rand  = StdRandom.uniform(size);
		Item item = queue[rand];
		for (int i = rand+1; i < size; i++)
			queue[i-1] = queue[i];
		size--;
		if (size <= queue.length/4)
			resize(queue.length/2);
		return item;
	}

	/* return a random item (but do not remove it) */
	public Item sample() {
		checkEmpty();
		int rand  = StdRandom.uniform(size);
		return queue[rand];
	}

	/* return an independent iterator over items in random order */
	public Iterator<Item> iterator() {
		return new RandomQueueIterator();
	}

	private class RandomQueueIterator implements Iterator<Item> {
		private Item[] rQueue;
		private int pos;

		public RandomQueueIterator() {
			pos = 0;
			rQueue = queue;
			StdRandom.shuffle(rQueue);
		}

		public boolean hasNext() {
			return pos < size;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			return rQueue[pos++];
		}
	}

	public static void main(String[] args) {
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		System.out.println(rq.size());
		rq.enqueue(13);
		System.out.println(rq.dequeue());   
		System.out.println(rq.size());   
		System.out.println(rq.isEmpty());  
		rq.enqueue(40);
		System.out.println(rq.size() );  
		System.out.println(rq.dequeue());
		rq.enqueue(38);
	}

}
