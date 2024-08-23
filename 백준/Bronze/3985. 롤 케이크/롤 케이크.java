import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int L, N;
	static boolean[] used;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		L = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		used = new boolean[L + 1];

		int maxAns1 = -1, maxIdx1 = -1, maxAns2 = -1, maxIdx2 = -1;
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			if (maxAns1 < k - p) { // 가장 많은 조각을 받을 것으로 기대하고 있는 방청객 찾기
				maxAns1 = k - p;
				maxIdx1 = i;
			}

			int total = 0;
			for (int j = p; j <= k; j++) {
				if (!used[j]) {
					total++;
					used[j] = true;
				}
			}
			if (maxAns2 < total) {
				maxAns2 = total;
				maxIdx2 = i;
			}
		}
		
		System.out.println(maxIdx1);
		System.out.println(maxIdx2);
	}
}