import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, M;
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

			make();
			sb.append("#").append(t).append(" ");
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int cmd = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (cmd == 0) { // union 연산
					union(a, b);
				} else { // 같은 집합인지 확인하기
					int ga = find(a);
					int gb = find(b);
					sb.append(ga == gb ? 1 : 0);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void make() { // 초기화
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

	static int find(int x) {
		if (x == parents[x])
			return x;

		return parents[x] = find(parents[x]);
	}

	static void union(int a, int b) {
		int ga = find(a);
		int gb = find(b);
		if (ga == gb)
			return;

		parents[gb] = ga; // b를 a에 붙이기
	}
}