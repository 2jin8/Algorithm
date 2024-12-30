import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 하나의 용액을 정하고, 나머지 용액을 정하기
		int a = -1, b = -1, minSum = Integer.MAX_VALUE;
		for (int i = 0; i < N - 1; i++) {
			int c = binarySearch(i);
			int sum = Math.abs(arr[i] + arr[c]);
			if (sum < minSum) {
				a = arr[i];
				b = arr[c];
				minSum = sum;
			}
		}
		System.out.println(a + " " + b);
	}

	static int binarySearch(int start) {
		int left = start + 1, right = N - 1;
		int ans = 0, minSum = Integer.MAX_VALUE;

		while (left <= right) {
			int mid = (left + right) / 2;
			int sum = arr[start] + arr[mid];
			// 특성값이 0이면 return
			if (sum == 0)
				return mid;

			// 특성값이 더 작은 것으로 갱신하기
			if (Math.abs(sum) < minSum) {
				minSum = Math.abs(sum);
				ans = mid;
			}

			// 특성값이 0보다 작으면 더 큰 값을 더해주도록 => 우측으로
			if (sum < 0) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return ans;
	}
}