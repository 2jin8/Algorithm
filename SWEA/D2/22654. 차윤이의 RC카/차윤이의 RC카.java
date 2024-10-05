import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, Q, C;
	static int[] start, end;
	static char[] cmd, map[];
	static int[] dx = { -1, 0, 1, 0 }, dy = { 0, 1, 0, -1 }; // 상우하좌

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j);
					if (map[i][j] == 'X') {
						start = new int[] { i, j };
					} else if (map[i][j] == 'Y') {
						end = new int[] { i, j };
					}
				}
			}

			Q = Integer.parseInt(br.readLine());
			sb.append("#").append(t).append(" ");
			for (int i = 0; i < Q; i++) {
				st = new StringTokenizer(br.readLine());
				C = Integer.parseInt(st.nextToken());
				cmd = st.nextToken().toCharArray();
				sb.append(move()).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static int move() {
		int x = start[0], y = start[1], d = 0;
		for (int i = 0; i < C; i++) {
			char c = cmd[i];
			switch (c) {
			case 'A': // 앞으로 직진
				x += dx[d];
				y += dy[d];
				// 범위를 벗어나면 기존 위치로 복구
				if (x < 0 || y < 0 || x >= N || y >= N || map[x][y] == 'T') {
					x -= dx[d];
					y -= dy[d];
				}
				break;
			case 'L': // 왼쪽으로 90도 회전
				if (--d < 0)
					d = 3;
				break;
			case 'R': // 오른쪽으로 90도 회전
				if (++d > 3)
					d = 0;
				break;
			}
			
		}
		// "이동 가능 여부가 아닌, 커맨드를 전부 실행 후 목적지에 도달했는지를 확인"해야 하는 거였음..!
		if (x == end[0] && y == end[1])
			return 1;
		return 0;
	}
}