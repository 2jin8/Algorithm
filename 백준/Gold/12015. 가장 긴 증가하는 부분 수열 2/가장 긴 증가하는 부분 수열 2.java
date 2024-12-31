import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, INF = 1_000_001;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		Arrays.fill(arr, INF);

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			int bs = binarySearch(num);
			arr[bs] = num;
		}

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (arr[i] == INF) break;
			cnt++;
		}
		System.out.println(cnt);
	}

	static int binarySearch(int target) {
		int left = 0, right = N - 1;
		int ans = -1;

		while (left <= right) {
			int mid = (left + right) / 2;

			// target보다 크거나 같으면 더 작은 부분을 확인해야 함
			// => 좌측으로
			if (target <= arr[mid]) {
				ans = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return ans;
	}
}