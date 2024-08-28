import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, group;
	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			parents = new int[N + 1];

			group = N;
			makeSet();
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (union(a, b)) { // 합집합 연산 성공
					group--; // 그룹 수 감소
				}
			}
			sb.append("#").append(t).append(" ").append(group).append("\n");
		}
		System.out.println(sb);
	}

	static void makeSet() {
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

	static int findRoot(int x) {
		if (x == parents[x])
			return x;

		return parents[x] = findRoot(parents[x]);
	}

	static boolean union(int a, int b) {
		int rootA = findRoot(a);
		int rootB = findRoot(b);
		if (rootA == rootB)
			return false;

		parents[rootB] = rootA; // b를 A에 연결
		return true;
	}
}