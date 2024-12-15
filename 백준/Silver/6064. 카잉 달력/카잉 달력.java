import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int M, N, x, y;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());

			int lcm = (N * M) / gcd(M, N);
			int ans = -1;

			// x는 고정시키기
			for (int i = x; i <= lcm; i += M) { // 최소공배수만큼 반복하면 됨
				int ny = i % N;
				if (ny == 0) ny = N;
				if (ny == y) { // y만 비교하기
					ans = i;
					break;
				}
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}
}
