import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Pos>[] holes = new ArrayList[5];
	static ArrayList<Pos> startList = new ArrayList<>();
	static int[] dx = { 0, 1, 0, -1 }, dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			startList.clear();
			for (int i = 0; i < 5; i++) {
				holes[i] = new ArrayList<Pos>();
			}

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 0) { // 가능한 핀볼 시작 위치
						startList.add(new Pos(i, j));
					} else if (map[i][j] >= 6) { // 웜홀 정보
						holes[map[i][j] - 6].add(new Pos(i, j));
					}
				}
			}

			int score = 0;
			for (Pos start : startList) {
				for (int i = 0; i < 4; i++) {
					score = Math.max(score, pinball(start, i));
				}
			}
			sb.append("#").append(t).append(" ").append(score).append("\n");
		}
		System.out.println(sb);
	}

	static int pinball(Pos start, int d) {
		int x = start.x, y = start.y;
		int score = 0, move = 0;
		while (true) {
			// 출발 위치로 돌아오면 종료
			if (x == start.x && y == start.y && move > 0)
				break;
			// 블랙홀에 빠지면 종료
			if (map[x][y] == -1)
				break;

			// 다음 칸으로 이동
			x += dx[d];
			y += dy[d];
			move++;
			
			// 벽에 부딪히면 반대 방향으로 돌아감
			if (x < 0 || y < 0 || x >= N || y >= N) {
				score++;
				d = (d + 2) % 4; // 반대 방향으로 돌리기
				x += dx[d]; // 기존 위치로 돌아가도록
				y += dy[d];
			} 
			
			switch (map[x][y]) {
			case 0:
				break;
			case 1:
				if (d == 0) d = 2;
				else if (d == 1) d = 0;
				else if (d == 2) d = 3;
				else if (d == 3) d = 1;
				score++;
				break;
			case 2:
				if (d == 0) d = 2;
				else if (d == 1) d = 3;
				else if (d == 2) d = 1;
				else if (d == 3) d = 0;
				score++;
				break;
			case 3:
				if (d == 0) d = 1;
				else if (d == 1) d = 3;
				else if (d == 2) d = 0;
				else if (d == 3) d = 2;
				score++;
				break;
			case 4:
				if (d == 0) d = 3;
				else if (d == 1) d = 2;
				else if (d == 2) d = 0;
				else if (d == 3) d = 1;
				score++;
				break;
			case 5:
				d = (d + 2) % 4;
				score++;
				break;
			default:
				// 웜홀
				if (map[x][y] >= 6) {
					ArrayList<Pos> hole = holes[map[x][y] - 6];
					Pos p1 = hole.get(0);
					Pos p2 = hole.get(1);
					if (p1.x == x && p1.y == y) {
						x = p2.x;
						y = p2.y;
					} else {
						x = p1.x;
						y = p1.y;
					}
				}
			}
		}
		return score;
	}

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}