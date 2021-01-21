class PQNode<P, T> {
	public T data;
	public P priority;
	public PQNode<P, T> next;

	public PQNode() {
		next = null;
	}

	public PQNode(T e, P p) {
		data = e;
		priority = p;
	}
}

public class PQKImp<P extends Comparable<P>, T> implements PQK<P, T> {
	private int size;
	private PQNode<P, T> head;
	private int MaxSize;

	public PQKImp(int k) {
		size = 0;
		head = null;
		MaxSize = k;
	}

	public int length() {
		return size;
	}

	public void enqueue(P pr, T e) {
		PQNode<P, T> tmp = new PQNode<P, T>(e, pr);
		if ((size == 0) || (pr.compareTo(head.priority)) == 1) {
			tmp.next = head;
			head = tmp;

		} else {
			PQNode<P, T> p = head;
			PQNode<P, T> q = null;
			while ((p != null) && (pr.compareTo(p.priority)) < 1) {
				q = p;
				p = p.next;
			}
			tmp.next = p;
			q.next = tmp;
		}
		size++;
		PQNode<P, T> current = head;
		PQNode<P, T> w = null;

		if (size > MaxSize) {
			while (current.next != null) {
				w = current;
				current = current.next;
			}
			if (w != null)
				w.next = null;
			size--;
		}
	}

	public Pair<P, T> serve() {
		PQNode<P, T> node = head;
		Pair<P, T> pqe = new Pair<P, T>(node.priority, node.data);
		head = head.next;
		size--;
		return pqe;
	}
}