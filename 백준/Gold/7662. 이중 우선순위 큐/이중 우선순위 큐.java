import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static PriorityQueue<Number> pq1 = new PriorityQueue<>((n1, n2) -> {
		if (n1.k == n2.k)
			return Integer.compare(n1.order, n2.order);
		return Integer.compare(n2.k, n1.k);
	});
	static PriorityQueue<Number> pq2 = new PriorityQueue<>((n1, n2) -> {
		if (n1.k == n2.k)
			return Integer.compare(n1.order, n2.order);
		return Integer.compare(n1.k, n2.k);
	});
	static HashSet<Number> deletedSet = new HashSet<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			// pq1: 내림차순 정렬, pq2: 오름차순 정렬
			pq1.clear();
			pq2.clear();
			deletedSet.clear();

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				String cmd = st.nextToken();
				int k = Integer.parseInt(st.nextToken());
				if (cmd.equals("I")) { // 데이터 삽입 연산
					Number number = new Number(i, k);
					pq1.offer(number);
					pq2.offer(number);
				} else if (cmd.equals("D")) { // 데이터 삭제 연산
					if (k == 1) { // 최댓값 삭제
						while (!pq1.isEmpty()) {
							Number number = pq1.poll();
							if (!deletedSet.contains(number)) {
								deletedSet.add(number);
								break;
							}
						}
					} else if (k == -1) { // 최솟값 삭제
						while (!pq2.isEmpty()) {
							Number number = pq2.poll();
							if (!deletedSet.contains(number)) {
								deletedSet.add(number);
								break;
							}
						}
					}
				}
			}
			
			while (!pq1.isEmpty()) {
				Number number = pq1.peek();
				if (deletedSet.contains(number)) {
					pq1.poll();
				} else {
					break;
				}
			}
			
			while (!pq2.isEmpty()) {
				Number number = pq2.peek();
				if (deletedSet.contains(number)) {
					pq2.poll();
				} else {
					break;
				}
			}

			if (pq1.isEmpty() || pq2.isEmpty())
				sb.append("EMPTY").append("\n");
			else
				sb.append(pq1.peek().k).append(" ").append(pq2.peek().k).append("\n");
		}
		System.out.println(sb);
	}

	static class Number {
		int order, k;

		public Number(int order, int k) {
			this.order = order;
			this.k = k;
		}
	}
}