import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 창용 마을 무리의 개수
public class Solution {

	static int N, M;
	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 마을 사람 수
			M = Integer.parseInt(st.nextToken()); // 사람의 관계 수

			int group = N;
			make();
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (union(a, b)) { // 그룹이 만들어지는 경우
					group--;
				}
			}
			sb.append("#").append(t).append(" ").append(group).append("\n");
		}
		System.out.println(sb);
	}

	static void make() {
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

	static int findSet(int a) {
		if (a == parents[a])
			return a;

		return parents[a] = findSet(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) // 부모가 같은 경우
			return false;

		parents[bRoot] = aRoot; // 왼쪽 노드에 오른쪽 노드를 붙이기
		return true;
	}
}