import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Integer[] tips = new Integer[n];
        for (int i = 0; i < n; i++) {
            tips[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(tips, Collections.reverseOrder());
        long money = 0;
        for (int i = 0, order =  1; i < n; i++, order++) {
            int m = tips[i] - (order - 1);
            if (m < 0) break;
            money += m;
        }
        System.out.println(money);
    }
}