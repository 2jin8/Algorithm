import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(br.readLine());
		Point[] stores = new Point[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			stores[i] = new Point(dir, dist);
		}

		st = new StringTokenizer(br.readLine());
		int dir = Integer.parseInt(st.nextToken());
		int dist = Integer.parseInt(st.nextToken());

		int minDist = 0;
		for (Point store : stores) {
			if (dir == store.dir) { // 같은
				minDist += Math.abs(dist - store.dist);
				continue;
			}

			int d1 = -1, d2 = -1;
			switch (store.dir) {
			case 1:
				if (dir == 2) {
					d1 = store.dist + H + dist;
					d2 = W - store.dist + H + W - dist;
//					d1 = H + dist + store.dist;
//					d2 = H + (W - dist) + (W - store.dist);
				} else if (dir == 3) {
					d1 = store.dist + dist;
					d2 = W - store.dist + H + W + H - dist;
//					d1 = dist + store.dist;
//					d2 = H + W + (W - dist) + (H - store.dist);
				} else {
					d1 = W - store.dist + dist;
					d2 = store.dist + H + W + H - dist;
//					d1 = (W - dist) + store.dist;
//					d2 = H + W + dist + (H - store.dist);
				}
				break;
			case 2:
				if (dir == 1) {
					d1 = store.dist + H + dist;
					d2 = W - store.dist + H + W - dist;
//					d1 = H + dist + store.dist;
//					d2 = H + (W - dist) + (W - store.dist);
				} else if (dir == 3) {
					d1 = store.dist + H - dist;
					d2 = W - store.dist + H + W + dist;
//					d1 = dist + (H - store.dist);
//					d2 = H + W + store.dir + (W - dist);
				} else {
					d1 = W - store.dist + H - dist;
					d2 = store.dist + H + W + dist;
//					d1 = (W - dist) + (H - store.dist);
//					d2 = H + W + store.dir + dist;
				}
				break;
			case 3:
				if (dir == 1) {
					d1 = store.dist + dist;
					d2 = H - store.dist + W + H + W - dist;
//					d1 = dist + store.dist;
//					d2 = H + W + (W - dist) + (H - store.dist);
				} else if (dir == 2) {
					d1 = H - store.dist + dist;
					d2 = store.dist + H + W + W - dist;
//					d1 = dist + (H - store.dist);
//					d2 = H + W + (W - dist) + store.dist;
				} else {
					d1 = store.dist + W + dist;
					d2 = H - store.dist + W + H - dist;
//					d1 = W + dist + store.dist;
//					d2 = W + (H - dist) + (H - store.dist);
				}
				break;
			case 4:
				if (dir == 1) {
					d1 = store.dist + W - dist;
					d2 = H - store.dist + W + H + dist;
//					d1 = (W - dist) + store.dist;
//					d2 = H + W + dist + (H - store.dist);
				} else if (dir == 2) {
					d1 = H - store.dist + W - dist;
					d2 = store.dist + W + H + dist;
//					d1 = (W - dist) + (H - store.dist);
//					d2 = H + W + dist + store.dist;
				} else {
					d1 = store.dist + W + dist;
					d2 = H - store.dist + W + H - dist;
//					d1 = W + dist + store.dist;
//					d2 = W + (H - dist) + (H - store.dist);
				}
				break;
			}
			minDist += Math.min(d1, d2);
		}
		System.out.println(minDist);
	}

	static class Point {
		int dir, dist;

		public Point(int dir, int dist) {
			this.dir = dir;
			this.dist = dist;
		}
	}
}
