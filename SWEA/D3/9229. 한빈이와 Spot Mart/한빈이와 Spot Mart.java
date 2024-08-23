import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// N * N: 1,000,000 (이중 for 문 가능)
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 과자의 개수
			int M = Integer.parseInt(st.nextToken()); // 과자 봉지의 무게 제한
			int[] weights = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				weights[i] = Integer.parseInt(st.nextToken());
			}

			int maxWeight = -1;
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					int weight = weights[i] + weights[j];
					// 무게 제한을 넘지 않고 최대 무게보다 무거우면 갱신
					if (weight <= M && weight > maxWeight) {
						maxWeight = weight;
					}
				}
			}
			sb.append("#").append(t).append(" ").append(maxWeight).append("\n");
		}
		System.out.println(sb);
	}
}