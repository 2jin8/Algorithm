import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, MAX = Integer.MAX_VALUE;
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		StringTokenizer st = null;

		Fish babyShark = null;
		PriorityQueue<Fish> sizePQ = new PriorityQueue<>((f1, f2) -> Integer.compare(f1.size, f2.size));
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 9) { // 아기 상어
					babyShark = new Fish(i, j, 2, 0);
				} else if (arr[i][j] != 0) { // 물고기
					sizePQ.offer(new Fish(i, j, arr[i][j], 0));
				}
			}
		}

		PriorityQueue<Fish> distPQ = new PriorityQueue<>((f1, f2) -> {
			if (f1.dist == f2.dist) { // 거리가 같다면 가장 위에 있는 물고기
				if (f1.x == f2.x) { // 가장 위에 있는 물고기가 여러 마리면 가장 왼쪽에 있는 물고기
					return f1.y - f2.y;
				}
				return f1.x - f2.x;
			}
			return f1.dist - f2.dist; // 거리가 가장 가까운 물고기 먹기
		});

		int time = 0, eatCnt = 0;
		while (true) {
			while (!sizePQ.isEmpty()) {
				// 아기 상어보다 작은 물고기가 있다면 poll
				if (babyShark.size > sizePQ.peek().size) {
					Fish fish = sizePQ.poll();
					fish.dist = getDist(fish, babyShark);
					distPQ.offer(fish);
				} else {
					break;
				}
			}

			// 먹을 수 있는 물고기가 없는 경우
			if (distPQ.isEmpty())
				break;

			Fish fish = distPQ.poll();
			// 해당 물고기까지 이동할 수 없다면 종료
			// poll한 것이 가장 작은 거리에 위치하는 것이니 이후 poll하는 것도 MAX
			if (fish.dist == MAX) { 
				break;
			}
			
			if (++eatCnt == babyShark.size) {
				babyShark.size++;
				eatCnt = 0;
			}
			// 배열 값 변경하기
			arr[babyShark.x][babyShark.y] = 0; // 기존 아기 상어 위치 삭제
			babyShark.x = fish.x; // 아기 상어의 위치 변경
			babyShark.y = fish.y;
			arr[babyShark.x][babyShark.y] = 9; // 새로운 아기 상어 위치 표시
			time += fish.dist; // 이동 시간 = 물고기를 잡아먹을 수 있는 시간

			// 다음 탐색을 위해 잡아먹지 않은 물고기들은 다시 넣기
			while (!distPQ.isEmpty()) { 
				sizePQ.offer(distPQ.poll());
			}
		}
		System.out.println(time);
	}

	static int[] dx = { 1, -1, 0, 0, }, dy = { 0, 0, 1, -1 };

	static int getDist(Fish fish, Fish babyShark) {
		Queue<int[]> queue = new ArrayDeque<>();
		int[][] dist = new int[N][N];
		queue.offer(new int[] { babyShark.x, babyShark.y });
		dist[babyShark.x][babyShark.y] = 1;

		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0], y = now[1];
			if (x == fish.x && y == fish.y) // 물고기 위치에 다다르면 해당 거리 반환
				return dist[x][y] - 1;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				if (dist[nx][ny] == 0 && arr[nx][ny] <= babyShark.size) {
					queue.offer(new int[] { nx, ny });
					dist[nx][ny] = dist[x][y] + 1;
				}
			}
		}
		return Integer.MAX_VALUE;
	}

	static class Fish {
		int x, y, size, dist;

		public Fish(int x, int y, int size, int dist) {
			this.x = x;
			this.y = y;
			this.size = size;
			this.dist = dist;
		}
	}
}