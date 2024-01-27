import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Integer[] cost = new Integer[n];
        for (int i = 0; i < n; i++) {
            cost[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(cost, Collections.reverseOrder());
        int total = 0;
        for (int i = 0; i < n; i++) {
            if ((i + 1) % 3 == 0) continue;
            total += cost[i];
        }
        System.out.println(total);
    }
}