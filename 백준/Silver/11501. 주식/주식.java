import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());

            int[] arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            // 뒤에서부터 탐색
            int max = arr[N - 1];
            long total = 0;
            for (int i = N - 2; i >= 0; i--) {
                if (max < arr[i]) { // 자신보다 큰 값 존재 ⇒ 최대값 갱신
                    max = arr[i];
                } else { // 자신보다 작은 값 존재 ⇒ 주식 구매
                    total += max - arr[i];
                }
            }
            sb.append(total).append("\n");
        }
        System.out.println(sb.toString());
    }
}