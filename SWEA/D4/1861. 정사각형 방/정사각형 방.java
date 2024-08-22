import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];

			PriorityQueue<Room> pq = new PriorityQueue<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					pq.offer(new Room(i, j, arr[i][j]));
				}
			}

			visited = new boolean[N][N];
			int maxRoom = -1, maxNum = -1;
			while (!pq.isEmpty()) {
				Room now = pq.poll();
				if (!visited[now.x][now.y]) {
					int room = bfs(now);
					if (room > maxRoom) { // num이 작은 것부터 확인하므로 무조건 더 작은 num이 들어감
						maxRoom = room;
						maxNum = arr[now.x][now.y];
					}
				}
			}
			sb.append("#").append(t).append(" ").append(maxNum).append(" ").append(maxRoom).append("\n");
		}
		System.out.println(sb);
	}

	static int bfs(Room room) {
		Queue<Room> queue = new ArrayDeque<>();
		queue.offer(room);
		visited[room.x][room.y] = true;

		int roomCnt = 0; // 방문할 수 있는 방의 개수
		while (!queue.isEmpty()) {
			Room now = queue.poll();
			int x = now.x, y = now.y;
			roomCnt++;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				// 아직 값을 기록하지 않았고 현재 칸보다 1이 큰 경우(=이동가능)
				if (!visited[nx][ny] && arr[nx][ny] == arr[x][y] + 1) {
					queue.add(new Room(nx, ny, arr[nx][ny]));
					visited[nx][ny] = true;
					break; // 배열에 적힌 값은 다 같으므로 break 해줘도 됨
				}
			}
		}
		return roomCnt;
	}

	static class Room implements Comparable<Room> {
		int x, y;
		int num;

		public Room(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}

		@Override
		public int compareTo(Room o) {
			return Integer.compare(this.num, o.num);
		}
	}
}