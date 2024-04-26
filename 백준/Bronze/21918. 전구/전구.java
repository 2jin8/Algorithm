import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] lamp = new int[N + 1]; // 0: false, 1: true
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            lamp[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            switch (a) {
                case 1: // b번째 전구의 상태를 c로 변경
                    lamp[b] = c;
                    break;
                case 2: // l번째부터 r번째까지의 전구의 상태 변경
                    for (int j = b; j <= c; j++) {
                        lamp[j] = (lamp[j] == 0 ? 1 : 0);
                    }
                    break;
                case 3:
                    for (int j = b; j <= c; j++) {
                        lamp[j] = 0;
                    }
                    break;
                case 4:
                    for (int j = b; j <= c; j++) {
                        lamp[j] = 1;
                    }
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(lamp[i]).append(" ");
        }
        System.out.println(sb.toString());
    }
}