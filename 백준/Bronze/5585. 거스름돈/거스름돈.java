import java.io.*;

/**
 * 단위가 큰 동전부터 거슬러주기
 */
public class Main {
    static int[] coins = {500, 100, 50, 10, 5, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int money = 1000 - Integer.parseInt(br.readLine());

        int total = 0;
        for (int coin : coins) {
            if (money == 0) break;

            total += money / coin;
            money %= coin;
        }
        System.out.println(total);
    }
}