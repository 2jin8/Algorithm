import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int INF = 1000001;
    private static int N;
    private static int[] T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        T = new int[N + 1];
        for (int i = 2; i <= N; i++) {
            T[i] = INF;
        }

        System.out.println(MakeOne());
    }

    private static int MakeOne() {
        if (N == 1)
            return 0;

        for (int i = 1; i <= N; i++) { // 하나씩 증가

            if (i % 3 == 0) {
                T[i] = Math.min(T[i], T[i / 3] + 1);
            }
            if (i % 2 == 0) {
                T[i] = Math.min(T[i], T[i / 2] + 1);
            }

            T[i] = Math.min(T[i], T[i - 1] + 1);
        }
        return T[N];
    }
}