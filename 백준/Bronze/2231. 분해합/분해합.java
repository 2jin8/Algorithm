import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int ans = 0;
        for (int i = 1; i < N; i++) {
            int total = i, num = i;
            while (num > 0) {
                total += num % 10;
                num /= 10;
            }
            if (total == N) {
                ans = i;
                break;
            }
        }
        System.out.println(ans);
    }
}