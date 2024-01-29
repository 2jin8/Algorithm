import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] times = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(times);

        long total = 0L, before = 0L;
        for (int i = 0; i < n; i++) {
            before += times[i];
            total += before;
        }
        System.out.println(total);
    }
}