import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int K, N;
	static int[] lines;

	// 가지고 있는 랜선들을 잘라서 랜선 N개를 만들 수 있는 랜선의 최대 길이
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken()); // 가지고 있는 랜선의 개수
		N = Integer.parseInt(st.nextToken()); // 필요한 랜선의 개수
		lines = new int[K];
		
		int maxLan = 0;
		for (int i = 0; i < K; i++) {
			lines[i] = Integer.parseInt(br.readLine());
			maxLan = Math.max(maxLan, lines[i]);
		}
		System.out.println(findMaxLanLength(maxLan));
	}

	static long findMaxLanLength(int max) {
		long left = 1, right = max;
		long ans = 0;

		while (left <= right) {
			long mid = (left + right) / 2;
			// 만들 수 있는 랜선의 개수가 N개 이상이면 자르는 길이를 늘려보기
			// => 우측으로 이동하기
			if (check(mid)) {
				left = mid + 1;
				ans = mid;
			} else {
				right = mid - 1;
			}
		}
		return ans;
	}

	static boolean check(long length) {
		long total = 0;
		for (int line : lines) {
			total += line / length;
		}
		return total >= N;
	}
}