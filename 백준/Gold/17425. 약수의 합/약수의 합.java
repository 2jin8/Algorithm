import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 1000001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        long[] fx = new long[MAX];
        long[] gx = new long[MAX];

        // fx 구하기
        Arrays.fill(fx, 1);
        for (int i = 2; i < MAX; i++) {
            for (int j = 1; i * j < MAX; j++) {
                fx[i * j] += i;
            }
        }

        // gx 구하기
        for (int i = 1; i < MAX; i++) {
            gx[i] = gx[i - 1] + fx[i];
        }

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            bw.write(gx[n] + "\n");
        }
        bw.flush(); bw.close();
    }
}