class Node<T> {
	public T data;
	public Node<T> next;

	public Node() {
		data = null;
		next = null;
	}

	public Node(T val) {
		data = val;
		next = null;
	}
}

public class LinkedList<T> implements List<T> {
	private Node<T> head;
	private Node<T> current;

	public LinkedList() {
		head = current = null;
	}

	public boolean empty() {
		return head == null;
	}

	public boolean full() {
		return false;
	}

	public void findFirst() {
		current = head;
	}

	public void findNext() {
		current = current.next;
	}

	public boolean last() {
		return current.next == null;
	}

	public T retrieve() {
		return current.data;
	}

	public void update(T e) {
		current.data = e;
	}

	public void insert(T e) {
		try {
			Node<T> tmp;
			if (empty()) {
				current = head = new Node<T>(e);
			} else {
				tmp = current.next;
				current.next = new Node<T>(e);
				current = current.next;
				current.next = tmp;
			}
		} catch (Exception e2) {

		}
	}

	public void remove() {
		try {
			if (current == head) {
				head = head.next;
			} else {
				Node<T> tmp = head;

				while (tmp.next != current) {
					tmp = tmp.next;
					tmp.next = current.next;
				}

				if (current.next == null)
					current = head;
				else
					current = current.next;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public int size() {
		int c = 0;
		Node<T> tmp = head;
		while (tmp != null) {
			c++;
			tmp = tmp.next;
		}
		return c;
	}

	public boolean exists(T e) {
		try {
			Node<T> tmp = head;
			while (tmp != null) {
				if (tmp.data.equals(e)) {
					return true;
				}
				tmp = tmp.next;
			}
		} catch (Exception e2) {

		}
		return false;
	}
}
