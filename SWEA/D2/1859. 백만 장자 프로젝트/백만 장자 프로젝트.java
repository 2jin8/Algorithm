import java.io.*;
import java.util.*;

class Solution
{
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int[] prices = new int[n];
            for (int i = 0; i < n; i++) {
                prices[i] = Integer.parseInt(st.nextToken());
            }

            int max = prices[n - 1];
            long total = 0;
            for (int i = n - 2; i >= 0; i--) {
                if (max > prices[i]) { // 현재 가격이 미래의 최대 주가보다 낮다면
                    // 주식 구매하기
                    total += max - prices[i];
                } else { // 현재 가격이 미래의 최대 주가보다 높다면
                    // 새로운 가격으로 최대 주가 변경하기
                    max = prices[i];
                }
            }
            sb.append("#").append(t).append(" ").append(total).append("\n");
        }
        System.out.println(sb.toString());
    }
}