import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, K, S, X, Y;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };
	static Queue<Pos> queue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];

		ArrayList<Pos> virusList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) {
					virusList.add(new Pos(i, j, map[i][j]));
					visited[i][j] = true;
				}
			}
		}

		Collections.sort(virusList); // 바이러스 번호가 작은 것부터 증식해야 하므로 정렬
		queue = new ArrayDeque<>();
		for (Pos virus : virusList) {
			queue.offer(virus);
		}

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken()) - 1;
		Y = Integer.parseInt(st.nextToken()) - 1;

		// 0초 후에 확인하는 것은 bfs 탐색이 필요없음
		if (S > 0) bfs(0, 0);
		System.out.println(map[X][Y]);
	}

	static void bfs(int sx, int sy) {

		int second = 0;
		while (!queue.isEmpty()) {
			int size = queue.size(); // 시간을 측정해야 하므로 큐 크기만큼 반복
			for (int s = 0; s < size; s++) {
				Pos now = queue.poll();
				int x = now.x, y = now.y;

				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny])
						continue;

					map[nx][ny] = map[x][y];
					visited[nx][ny] = true;
					queue.offer(new Pos(nx, ny, map[nx][ny]));
				}
			}
			// S초 후라면 탐색 종료
			if (++second == S) break;
		}
	}

	static class Pos implements Comparable<Pos> {
		int x, y, virus;

		public Pos(int x, int y, int virus) {
			this.x = x;
			this.y = y;
			this.virus = virus;
		}

		@Override
		public int compareTo(Pos o) { // 바이러스 번호 기준 오름차순 정렬
			return Integer.compare(this.virus, o.virus);
		}
	}
}