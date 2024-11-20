import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, K, cnt;
	static int[] weights, record;
	static boolean[] used;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); // 매일 감소하는 중량
		weights = new int[N + 1];
		record = new int[N + 1];
		used = new boolean[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
		}
		cnt = 0;
		dfs(1);
		System.out.println(cnt);
	}

	static void dfs(int depth) {
		if (depth > N) {
			if (checkWeight()) cnt++;
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (used[i]) continue;

			used[i] = true;
			record[depth] = i;
			dfs(depth + 1);
			used[i] = false;
		}
	}

	static boolean checkWeight() {
		int start = 500;
		for (int i = 1; i <= N; i++) {
			int increase = weights[record[i]]; // 중량 증가량
			int weight = increase - K; // 하루에 증가할 수 있는 중량 (increase만큼 증가, K만큼 감소)
			// 중량이 500 미만이면 조건을 만족하지 않음
			if (start + weight < 500)
				return false;
			
			start += weight;
		}
		return true;
	}
}