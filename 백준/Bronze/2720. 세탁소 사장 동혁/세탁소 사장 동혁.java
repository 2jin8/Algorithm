import java.io.*;

/**
 * 단위가 큰 동전부터 거슬러주기
 */
public class Main {
    static int[] coins = {25, 10, 5, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int money = Integer.parseInt(br.readLine());

            int[] total = new int[4];
            for (int i = 0; i < 4; i++) {
                if (money == 0) break;
                total[i] = money / coins[i];
                money %= coins[i];
            }
            for (int i : total) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}