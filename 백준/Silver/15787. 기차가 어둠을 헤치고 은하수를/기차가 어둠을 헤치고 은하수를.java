import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static final int K = 20;
    static boolean[][] trains;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        trains = new boolean[N + 1][K + 1];
        for (int t = 0; t < M; t++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            int x;
            switch (cmd) {
                case 1: // 기차에 태우기
                    x = Integer.parseInt(st.nextToken());
                    trains[i][x] = true;
                    break;
                case 2: // 기차에서 내리기
                    x = Integer.parseInt(st.nextToken());
                    trains[i][x] = false;
                    break;
                case 3: // 한 칸씩 뒤로 이동
                    for (int j = K; j >= 2; j--) {
                        trains[i][j] = trains[i][j - 1];
                    }
                    trains[i][1] = false;
                    break;
                case 4: // 한 칸씩 앞으로 이동:
                    for (int j = 1; j < K; j++) {
                        trains[i][j] = trains[i][j + 1];
                    }
                    trains[i][K] = false;
                    break;
            }
        }

        HashSet<String> hashSet = new HashSet<>();
        for (int i = 1; i <= N; i++) { // 문자열과 해시로 기차의 상태 확인하기
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j <= K; j++) {
                if (trains[i][j]) sb.append("1");
                else sb.append("0");
            }
            hashSet.add(sb.toString());
        }
        System.out.println(hashSet.size());
    }
}