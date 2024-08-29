import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, M, ans = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Point> homeList = new ArrayList<>();
	static ArrayList<Point> chickenList = new ArrayList<>();
	static Point[] points;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); // 선택할 치킨집의 개수
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) { // 집
					homeList.add(new Point(i, j));
				} else if (map[i][j] == 2) { // 치킨집
					chickenList.add(new Point(i, j));
				}
			}
		}

		points = new Point[M];
		if (M == chickenList.size()) { // 폐업시켜야 하는 치킨집이 없는 경우
			for (int i = 0; i < M; i++) {
				points[i] = chickenList.get(i);
			}
			ans = calcDist();
		} else {
			dfs(0, 0);
		}
		System.out.println(ans);
	}

	static void dfs(int depth, int start) {
		// M개의 치킨집을 다 고른 경우
		if (depth == M) {
			// 도시의 치킨 거리 최솟값 구하기
			ans = Math.min(ans, calcDist());
			return;
		}

		// 조합
		for (int i = start; i < chickenList.size(); i++) {
			points[depth] = chickenList.get(i);
			dfs(depth + 1, i + 1);
		}
	}

	static int calcDist() {
		int minDist = 0;
		// 각 집의 치킨 거리 구하기
		for (Point home : homeList) {
			int dist = Integer.MAX_VALUE;
			for (Point chicken : points) {
				dist = Math.min(dist, Math.abs(home.x - chicken.x) + Math.abs(home.y - chicken.y));
			}
			// 도시의 치킨 거리 최솟값 구하기
			minDist += dist;
		}
		return minDist;
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}