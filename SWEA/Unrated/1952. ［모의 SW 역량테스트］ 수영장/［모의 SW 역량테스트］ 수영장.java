import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N = 4, M = 12, minPrice;
	static int[] prices, month;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			prices = new int[N]; // 이용권 금액
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				prices[i] = Integer.parseInt(st.nextToken());
			}

			month = new int[M + 1]; // 월 별 이용 계획
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				month[i] = Integer.parseInt(st.nextToken());
			}
			
            // 최대 금액인 1년치 금액으로 초기화하기
            minPrice = prices[3];
            // 1월부터 시작하기
			dfs(1, 0);
			sb.append("#").append(t).append(" ").append(minPrice).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int depth, int price) {
		// 최소 사용 금액보다 크다면 return
		if (minPrice <= price)
			return;

		// 모든 달을 다 계산했다면 최소 사용 금액 갱신하기
		if (depth > M) {
			minPrice = price;
			return;
		}
		
		// 해당 달에 이용하지 않는다면 바로 다음 달로 넘어가기
		if (month[depth] == 0) {
			dfs(depth+1, price);
			return;
		}

		// 1일권
		dfs(depth + 1, price + prices[0] * month[depth]);

		// 1달권
		dfs(depth + 1, price + prices[1]);

		// 3달권
		dfs(depth + 3, price + prices[2]);
	}
}