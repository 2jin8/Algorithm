import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, K, L = 100_001;
	static int[] pos = new int[L];
	static boolean[] visited = new boolean[L];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		bfs();
	}

	static void bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(N, 0));
		visited[N] = true;

		while (!queue.isEmpty()) {
			Point now = queue.poll();
			if (now.x == K) {
				System.out.println(now.hour);
				break;
			}

			// X + 1로 이동
			int next = now.x + 1;
			if (next < L && !visited[next]) {
				queue.offer(new Point(next, now.hour + 1));
				visited[next] = true;
			}

			// X - 1로 이동
			next = now.x - 1;
			if (next >= 0 && !visited[next]) {
				queue.offer(new Point(next, now.hour + 1));
				visited[next] = true;
			}
			
			// 2 * X로 이동
			next = now.x  * 2;
			if (next < L && !visited[next]) {
				queue.offer(new Point(next, now.hour + 1));
				visited[next] = true;
			}
		}
	}

	static class Point {
		int x;
		int hour;

		public Point(int x, int hour) {
			this.x = x;
			this.hour = hour;
		}
	}
}