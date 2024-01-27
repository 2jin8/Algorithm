import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int money = Integer.parseInt(br.readLine());
        int coin = 0;
        while (money >= 0) {
            if (money % 5 == 0) {
                coin += money / 5;
                money = 0;
                break;
            } else {
                coin++;
                money -= 2;
            }
        }
        if (money == 0) System.out.println(coin);
        else System.out.println(-1);
    }
}