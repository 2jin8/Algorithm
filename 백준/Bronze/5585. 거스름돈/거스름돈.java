import java.util.*;

public class Main {

    private static int[] coins = {500, 100, 50, 10, 5, 1};
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int money = 1000 - scan.nextInt();

        int idx = 0, cnt = 0;
        while (money != 0) {
            if (money < coins[idx]) { // 거스름돈 < 동전의 값
                idx++;
                continue;
            }

            int div = money / coins[idx];
            money -= div * coins[idx++];
            cnt += div;
        }
        System.out.println(cnt);
    }
}