import java.io.File;
import java.util.Scanner;

public class Recommender {

	// Return the top k recommended friends for user i using the popular nodes
	// method. If i does not exist, return null. In case of a tie, users with the
	// lowest id are selected.
	public static <K extends Comparable<K>> PQK<Double, K> recommendPop(Graph<K> g, K i, int k) {
		PQK<Double, K> RF = new PQKImp<>(k);
		try {
			if (!g.isNode(i))
				return null;
			List<K> node = g.getNodes();
			List<K> nig = g.neighb(i);
			node.findFirst();
			nig.findFirst();
			for (int j = 0; j < node.size(); j++) {
				K n = node.retrieve();
				if (!nig.exists(n)) {
					if (i.compareTo(n) == 0) {
						node.findNext();
						continue;
					}
					RF.enqueue((double) g.deg(n), n);
				}
				node.findNext();
			}
		} catch (Exception e) {

		}
		return RF;
	}

	// Return the top k recommended friends for user i using common neighbors
	// method. If i does not exist, return null. In case of a tie, users with the
	// lowest id are selected.
	public static <K extends Comparable<K>> PQK<Double, K> recommendCN(Graph<K> g, K i, int k) {
		PQK<Double, K> RF = new PQKImp<>(k);
		try {
			if (!g.isNode(i))
				return null;
			List<K> node = g.getNodes();
			List<K> nig = g.neighb(i);
			node.findFirst();
			double common = 0;
			for (int j = 0; j < node.size(); j++) {
				K n = node.retrieve();
				if (nig.exists(n) || n.equals(i))
					node.findNext();
				else {
					List<K> nigcomList = g.neighb(n);
					nig.findFirst();
					for (int r = 0; r < nig.size(); r++) {
						K n1 = nig.retrieve();
						if (nigcomList.exists(n1))
							common++;
						nig.findNext();
					}
					RF.enqueue(common, n);
					common = 0;
					node.findNext();
				}
			}
		} catch (Exception e) {
			
		}
		return RF;
	}
	
	// Read graph from file. The file is a text file where each line contains an
	// edge. The end and start of the edge are separated by space(s) or tabs.
	public static Graph<Integer> read(String fileName) {
		try {
			Graph<Integer> g = new MGraph<Integer>();
			Scanner scanner = new Scanner(new File(fileName));
			while (scanner.hasNextInt()) {
				int i = scanner.nextInt();
				g.addNode(i);
				int j = scanner.nextInt();
				g.addNode(j);
				g.addEdge(i, j);
			}
			scanner.close();
			return g;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
