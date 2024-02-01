import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            int total = i, idx = i;
            while (idx != 0) {
                total += idx % 10;
                idx /= 10;
            }
            if (total == n) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(0);
    }
}