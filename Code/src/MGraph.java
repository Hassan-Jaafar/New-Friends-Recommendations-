public class MGraph<K extends Comparable<K>> implements Graph<K> {
	public Map<K, List<K>> adj;

	public MGraph() {
		adj = new BSTMap<>();
	}

	public boolean addNode(K i) {
		try {
			if (isNode(i))
				return false;
			adj.insert(i, new LinkedList<K>());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	public boolean isNode(K i) {
		try {
			if (adj.retrieve(i).first)
				return true;
		} catch (Exception e) {

		}
		return false;
	}

	public boolean addEdge(K i, K j) {
		try {
			if (isEdge(i, j))
				return false;
			else if (isNode(i) && isNode(j)) {
				adj.retrieve(i).second.insert(j);
				adj.retrieve(j).second.insert(i);
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	public boolean isEdge(K i, K j) {
		try {
			if (isNode(i) && isNode(j)) {
				if (adj.retrieve(i).second.exists(j))
					return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	public List<K> neighb(K i) {
		try {
			if (isNode(i)) {
				List<K> n = new LinkedList<K>();
				adj.retrieve(i).second.findFirst();
				for (int j = 0; j < adj.retrieve(i).second.size(); j++) {
					n.insert(adj.retrieve(i).second.retrieve());
					adj.retrieve(i).second.findNext();
				}
				return n;
			}
		} catch (Exception e) {

		}
		return null;
	}

	public int deg(K i) {
		try {
			if (isNode(i)) {
				return adj.retrieve(i).second.size();
			}

		} catch (Exception e) {

		}
		return -1;
	}

	public List<K> getNodes() {
		try {
			return adj.getKeys();
		} catch (Exception e) {

		}
		return new LinkedList<K>();
	}
}
