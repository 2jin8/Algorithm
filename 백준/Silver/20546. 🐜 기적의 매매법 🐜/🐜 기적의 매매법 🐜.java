import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int DAY = 14;
    static int[] values;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int money = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        values = new int[DAY + 1];
        for (int i = 1; i <= DAY; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        int totalJH = BNP(money);
        int totalSM = TIMING(money);
        if (totalJH == totalSM) System.out.println("SAMESAME");
        else if (totalJH > totalSM) System.out.println("BNP");
        else System.out.println("TIMING");
    }

    public static int BNP(int m) {
        int money = m, count = 0;
        for (int i = 1; i < DAY; i++) {
            if (money == 0) break;

            int value = values[i];
            if (money >= value) {
                count += money / value;
                money %= value;
            }
        }
        return money + count * values[DAY];
    }

    public static int TIMING(int m) {
        int money = m, count = 0;
        for (int i = 4; i < DAY; i++) {
            if (values[i - 1] > values[i - 2] && values[i - 2] > values[i - 3]) { // 3일 연속 상승
                // 매도하기
                money += count * values[i];
                count = 0;
            } else if (values[i - 1] < values[i - 2] && values[i - 2] < values[i - 3]) { // 3일 연속 하락
                // 매수하기
                count += money / values[i];
                money %= values[i];
            }
        }
        return money + count * values[DAY];
    }
}