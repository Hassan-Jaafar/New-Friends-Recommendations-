
public class BSTMap<K extends Comparable<K>, T> implements Map<K, T> {
	public BSTNode<K, T> root; 
	private int size;

	public BSTMap() {
		root = null;
		size = 0;
	}

	public int size() {

		return size;
	}

	public boolean full() {

		return false;
	}

	public void clear() {
		root = null;
		size = 0;
	}

	// Update the data of the key k if it exists and return true. If k does not
	// exist, the method returns false.
	public boolean update(K k, T e) {
		try {
			BSTNode<K, T> p = root;
			if (root == null)
				return false;
			while (p != null) {
				if (p.key.compareTo(k) == 0) {
					p.data = e;
					return true;
				} else if (k.compareTo(p.key) == -1)
					p = p.left;
				else
					p = p.right;
			}
		} catch (Exception e2) {

		}
		return false;
	}

	// Search for element with key k and returns a pair containing true and its data
	// if it exists. If k does not exist, the method returns false and null.
	public Pair<Boolean, T> retrieve(K k) {
		Pair<Boolean, T> e = new Pair<Boolean, T>(false, null);
		try {

			BSTNode<K, T> p = root;
			if (root == null)
				return e;
			while (p != null) {
				if (p.key.compareTo(k) == 0) {
					Pair<Boolean, T> w = new Pair<Boolean, T>(true, p.data);
					return w;
				} else if (k.compareTo(p.key) == -1)
					p = p.left;
				else
					p = p.right;

			}
		} catch (Exception e2) {
			// TODO: handle exception
		}

		return e;
	}

	// Insert a new element if does not exist and return true. If k already exists,
	// return false.
	public boolean insert(K k, T e) {

		try {
			BSTNode<K, T> p = root;
			BSTNode<K, T> q = null;
			while (p != null) {
				q = p;
				if (p.key.compareTo(k) == 0) {
					return false;
				} else if (k.compareTo(p.key) == -1)
					p = p.left;
				else
					p = p.right;
			}
			BSTNode<K, T> r = new BSTNode<K, T>(k, e);
			if (root == null) {
				root = r;
				size++;
				return true;
			} else if (k.compareTo(q.key) == -1) {
				size++;
				q.left = r;
				return true;
			} else if (k.compareTo(q.key) == 1) {
				q.right = r;
				size++;
				return true;
			}
		} catch (Exception e2) {

		}
		return false;

	}

	// Remove the element with key k if it exists and return true. If the element
	// does not exist return false.
	public boolean remove(K k) {
		try {
			K k1 = k;
			BSTNode<K, T> p = root;
			BSTNode<K, T> q = null; // Parent of p
			while (p != null) {

				if (k1.compareTo(p.key) == -1) {
					q = p;
					p = p.left;
				} else if (k1.compareTo(p.key) == 1) {
					q = p;
					p = p.right;
				} else { // Found the key

					// Check the three cases
					if ((p.left != null) && (p.right != null)) {
						// Case 3: two children
						// Search for the min in the right subtree
						BSTNode<K, T> min = p.right;
						q = p;
						while (min.left != null) {
							q = min;
							min = min.left;
						}
						p.key = min.key;
						p.data = min.data;
						k1 = min.key;
						p = min;
						// Now fall back to either case 1 or 2
					}
					// The subtree rooted at p will change here
					if (p.left != null) { // One child
						p = p.left;
					} else { // One or no children
						p = p.right;
					}

					if (q == null) { // No parent for p, root must change
						root = p;
					} else {
						if (k1.compareTo(q.key) == -1) {
							q.left = p;
						} else {
							q.right = p;
						}
					}
					size--;
					return true;

				}
			}
		} catch (Exception e) {

		}
		return false; // Not found
	}

	// Return the list of keys in increasing order.
	public List<K> getKeys() {
		List<K> keys = new LinkedList<K>();
		try {
			BSTNode<K, T> r = root;
			keys = orderkeys(r, keys);

		} catch (Exception e) {

		}
		return keys;
	}

	private List<K> orderkeys(BSTNode<K, T> r, List<K> k) {
		try {
			if (r == null)
				return k;
			orderkeys(r.left, k);
			k.insert(r.key);
			orderkeys(r.right, k);
		} catch (Exception e) {

		}
		return k;
	}
}