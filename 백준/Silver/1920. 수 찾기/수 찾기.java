import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] A;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);

		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			sb.append(binarySearch(Integer.parseInt(st.nextToken()))).append("\n");
		}
		System.out.println(sb);
	}

	static int binarySearch(int target) {
		int left = 0, right = N - 1;
		while (left <= right) {
			int mid = (left + right) / 2;

			if (target == A[mid])
				return 1;

			// target이 중간값보다 크다면 우측으로
			if (target > A[mid]) left = mid + 1;
			else right = mid - 1;
		}
		return 0;
	}
}