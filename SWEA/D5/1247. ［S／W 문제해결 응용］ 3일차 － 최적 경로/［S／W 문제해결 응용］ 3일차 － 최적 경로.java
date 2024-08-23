import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, minDist;
	static boolean[] used;
	static Point[] customer, visit; // customer: 고객의 좌표, visit: 고객의 집 방문 순서

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
			dfs(1, 0);
			sb.append("#").append(t).append(" ").append(minDist).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int depth, int dist) { // depth: 1 ~ N
		// 현재 경로가 최단 경로보다 크다면 종료
		if (dist > minDist)
			return;

		// 고객의 집(N개)을 다 방문한 경우
		if (depth > N) {
			dist += Math.abs(visit[depth].x - visit[depth - 1].x) + Math.abs(visit[depth].y - visit[depth - 1].y);
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
			int newDist = Math.abs(visit[depth].x - visit[depth - 1].x) + Math.abs(visit[depth].y - visit[depth - 1].y);
			dfs(depth + 1, dist + newDist);
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