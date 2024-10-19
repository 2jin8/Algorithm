import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static int[] factorials;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		factorials = new int[N + 1];
		factorials[0] = 1;
		for (int i = 1; i <= N; i++) {
			factorials[i] = i * factorials[i - 1];
		}
		int nCk = factorials[N] / (factorials[N - K] * factorials[K]);
		System.out.println(nCk);
	}
}