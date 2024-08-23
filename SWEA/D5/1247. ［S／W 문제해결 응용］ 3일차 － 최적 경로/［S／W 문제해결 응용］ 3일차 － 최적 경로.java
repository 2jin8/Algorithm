import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, minDist;
	static boolean[] used;
	static Point[] customer, visit; // customer: 고객의 좌표, visit: 고객의 집 방문 순서

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 두 위치 사이 거리: Math.abs(x1 - x2) + Math.abs(y1 - y2);
		// N 명의 고객을 모두 방문하고 집으로 돌아오는 경로 중 가장 짧은 것 찾기
		// 회사 > 고객 > 집

		// N이 최대 10 & 방문 순서 O ,, 순열인가?

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			customer = new Point[N];
			used = new boolean[N];
			visit = new Point[N + 2];
			minDist = Integer.MAX_VALUE;

			StringTokenizer st = new StringTokenizer(br.readLine());
			visit[0] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			visit[N + 1] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for (int i = 0; i < N; i++) {
				customer[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			dfs(1);
			sb.append("#").append(t).append(" ").append(minDist).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int depth) { // depth: 1 ~ N
		if (depth > N) {
			int dist = 0;
			for (int i = 0; i < N + 1; i++) {
				dist += Math.abs(visit[i].x - visit[i + 1].x) + Math.abs(visit[i].y - visit[i + 1].y);
				if (dist > minDist)
					return;
			}
			if (minDist > dist)
				minDist = dist;
			return;
		}

		// depth번째로 방문할 고객의 집 선택하기
		for (int i = 0; i < N; i++) {
			if (used[i])
				continue;

			used[i] = true;
			visit[depth] = customer[i];
			dfs(depth + 1);
//			dfs(depth + 1, Math.abs(visit[depth-1].x - visit[depth].x) + visit[depth]);
			used[i] = false;
		}
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}