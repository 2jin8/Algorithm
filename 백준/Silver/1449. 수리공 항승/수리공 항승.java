import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[] pipe = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pipe[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(pipe);
        int i = 0, tape = 0;
        while (i < N) {
            // pipe[1] = 1 => 0.5 ~ 3.5
            double start = pipe[i] - 0.5;
            double end = start + L;

            while (i < N) {
                if (pipe[i] >= start && pipe[i] <= end) {
                    i++;
                } else {
                    tape++;
                    break;
                }
            }
        }
        System.out.println(tape + 1);
    }
}