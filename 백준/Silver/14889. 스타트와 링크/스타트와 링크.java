import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, minDiff;
	static int[][] arr;
	static boolean[] used;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		arr = new int[N][N];
		used = new boolean[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		minDiff = Integer.MAX_VALUE;
		dfs(0, 0);
		System.out.println(minDiff);
	}

	static void getMinDiff() {
		int team1Sum = 0, team2Sum = 0;
		int[] team1 = new int[N / 2];
		int[] team2 = new int[N / 2];
		int idx1 = 0, idx2 = 0;
		for (int i = 0; i < N; i++) {
			// used[i]가 true면 team1, false면 team2
			if (used[i]) team1[idx1++] = i;
			else team2[idx2++] = i;
		}

		for (int i = 0; i < N / 2 - 1; i++) {
			for (int j = i + 1; j < N / 2; j++) {
				team1Sum += arr[team1[i]][team1[j]] + arr[team1[j]][team1[i]];
				team2Sum += arr[team2[i]][team2[j]] + arr[team2[j]][team2[i]];
			}
		}
		minDiff = Math.min(minDiff, Math.abs(team1Sum - team2Sum));
	}

	// 팀 구성하기
	static void dfs(int depth, int start) {
		if (depth == N / 2) {
			getMinDiff();
			return;
		}

		for (int i = start; i < N; i++) {
			used[i] = true;
			dfs(depth + 1, i + 1);
			used[i] = false;
		}
	}
}
