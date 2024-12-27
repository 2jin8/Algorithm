import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] cards;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		cards = new int[N];
		for (int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(cards);

		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			int target = Integer.parseInt(st.nextToken());
			int lb = lower_bound(target);
			int ub = upper_bound(target);
			
			// lb > ub 이면 해당 카드를 가지고 있지 않은 것
			sb.append(lb > ub ? 0 : (ub - lb + 1)).append(" ");
		}
		System.out.println(sb);
	}

	static int lower_bound(int target) {
		int left = 0, right = N - 1;
		int ans = N;

		while (left <= right) {
			int mid = (left + right) / 2;

			// target이 cards[mid]보다 작거나 같으면 왼쪽으로
			if (target <= cards[mid]) {
				right = mid - 1;
				ans = mid;
			} else {
				left = mid + 1;
			}
		}
		return ans;
	}

	static int upper_bound(int target) {
		int left = 0, right = N - 1;
		int ans = -1;

		while (left <= right) {
			int mid = (left + right) / 2;

			// target이 cards[mid]보다 크거나 같으면 오른쪽으로
			if (target >= cards[mid]) {
				left = mid + 1;
				ans = mid;
			} else {
				right = mid - 1;
			}
		}
		return ans;
	}
}