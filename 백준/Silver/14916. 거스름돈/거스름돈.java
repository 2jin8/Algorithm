import java.util.*;
import java.io.*;

public class Main {

    private static int money;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        money = Integer.parseInt(br.readLine());

        int five = money / 5;

        int total = 0;
        for (int i = five; i >= 0; i--) {
            int tmp = money - 5 * i;
            if (tmp % 2 == 0) {
                total = i + tmp / 2;
                System.out.println(total);
                return;
            }
        }
        System.out.println(-1);
    }

}