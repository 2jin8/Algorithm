import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M = 9, maxScore;
	static int[] orders; // 타순
	static int[][] results;
	static boolean[] used;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		results = new int[N + 1][M + 1]; // 1이닝, 1번 타자
		StringTokenizer st = null;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				results[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		used = new boolean[M + 1];
		orders = new int[M + 1]; // 1부터 채우기
		orders[4] = 1; // 1번 선수를 4번 타자로 미리 결정함
		used[1] = true; // 1번 선수 사용 처리
		getOrders(1);
		System.out.println(maxScore);
	}

	// 순서가 중요 > 순열
	static void getOrders(int depth) {
		// 타순을 다 정한 경우
		if (depth > M) {
			// 시뮬 돌려서 점수 구하기
			playBaseball();
			return;
		}

		// 4번 타자는 이미 정해져있음
		if (depth == 4) {
			getOrders(depth + 1);
			return;
		}

		for (int i = 2; i <= M; i++) {
			if (used[i])
				continue;

			used[i] = true;
			orders[depth] = i;
			getOrders(depth + 1);
			used[i] = false;
		}
	}

	static void playBaseball() {
		int score = 0, order = 1; // 이닝이 변경되어도 타자 번호는 유지됨
		Queue<Integer> runner = new ArrayDeque<>();

		for (int i = 1; i <= N; i++) {
			int out = 0;
			runner.clear(); // 이닝이 시작할 때 주자는 없음
			for (int j = 0; j < 3; j++) { 
				runner.offer(0);
			}
			
			// 3아웃일 때, 이닝 변경
			while (out != 3) {
				int player = orders[order];
				switch (results[i][player]) {
				case 0: // 아웃
					out++;
					break;
				case 1: // 안타
					score += runPlayer(runner, 0);
					break;
				case 2: // 2루타
					score += runPlayer(runner, 1);
					break;
				case 3: // 3루타
					score += runPlayer(runner, 2);
					break;
				case 4: // 홈런
					score += runPlayer(runner, 3);
					break;
				}
				if (++order > M)
					order = 1;
			}
		}
		maxScore = Math.max(maxScore, score);
	}

	static int runPlayer(Queue<Integer> runner, int cnt) {
		int score = 0;
		runner.offer(1); // 홈에 있던 주자
		score += runner.poll();

		for (int i = 0; i < cnt; i++) {
			runner.offer(0);
			score += runner.poll();
		}
		return score;
	}
}