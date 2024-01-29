import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 도시의 개수
        long[] losts = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            losts[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(losts);
        
        long total;
        int l = 0, r;
        if (n % 2 == 0) {
            total = 0L;
            r = n - 1;
        } else {
            total = losts[n - 1];
            r = n - 2;
        }

        while (l <= r) {
            total = Math.max(total, losts[l++] + losts[r--]);
        }
        System.out.println(total);
    }
}