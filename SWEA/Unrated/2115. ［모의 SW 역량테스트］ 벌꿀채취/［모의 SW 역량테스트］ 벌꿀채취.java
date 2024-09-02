import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	static int N, M, C, maxHoney, ans;
	static int[][] map;
	static Pos[] posInfo;
	static ArrayList<Integer> honeyList = new ArrayList<>();
	static ArrayList<Pos> posList = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 벌통 크기
			M = Integer.parseInt(st.nextToken()); // 선택할 수 있는 벌통의 개수
			C = Integer.parseInt(st.nextToken()); // 채취할 수 있는 최대 양
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 가능한 선택
			posInfo = new Pos[2];
			posList.clear();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= N - M; j++) {
					posList.add(new Pos(i, j, j + M - 1));
				}
			}
			ans = 0;
			dfs(0, 0);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int depth, int start) {
		// 채취할 곳을 다 선택한 경우
		if (depth == 2) {
			// 채취 영역이 겹치지 않을 때, 얻을 수 있는 최대 양 구하기
			if (checkArea()) {
				ans = Math.max(ans, getHoney(0) + getHoney(1));
			}
			return;
		}

		for (int i = start; i < posList.size(); i++) {
			posInfo[depth] = posList.get(i);
			dfs(depth + 1, i + 1);
		}
	}

	static boolean checkArea() {
		Pos p1 = posInfo[0], p2 = posInfo[1];
		// x값이 다르면 영역이 겹치지 않음
		if (p1.x != p2.x)
			return true;

		// y값이 겹치면 영역이 겹침
		for (int i = p1.sy; i <= p1.ey; i++) {
			if (i == p2.sy || i == p2.ey)
				return false;
		}
		return true;
	}

	static int getHoney(int idx) {
		Pos pos = posInfo[idx];
		int honey = 0, money = 0;
		honeyList.clear();
		for (int i = pos.sy; i <= pos.ey; i++) {
			int h = map[pos.x][i];
			honey += h;
			money += h * h;
			honeyList.add(h);
		}
		if (honey <= C) // 꿀의 합이 채취할 수 있는 최대 양보다 작거나 같으면
			return money; // money 반환하기

		// C보다 크면 가능한 최대 채취량 구하기
		maxHoney = 0;
		getMaxHoney(0, 0, 0);
		return maxHoney;
	}

	static void getMaxHoney(int depth, int honey, int money) {
		if (honey > C) // C보다 크면 탐색할 필요 없음
			return;

		if (depth == honeyList.size()) { // 다 확인한 경우
			maxHoney = Math.max(maxHoney, money);
			return;
		}

		int h = honeyList.get(depth);
		// 현재 꿀 선택 O
		getMaxHoney(depth + 1, honey + h, money + h * h); 
		// 현재 꿀 선택 X
		getMaxHoney(depth + 1, honey, money); 
	}

	static class Pos {
		int x, sy, ey; // 시작/끝 y, x

		public Pos(int x, int sy, int ey) {
			this.x = x;
			this.sy = sy;
			this.ey = ey;
		}
	}
}