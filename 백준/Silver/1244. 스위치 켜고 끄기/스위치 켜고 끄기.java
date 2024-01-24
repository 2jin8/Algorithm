import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N, M;
    static boolean[] switches;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        switches = new boolean[N + 1]; // 1: ON, 0: OFF
        for (int i = 0; i < N; i++) {
            switches[i + 1] = Integer.parseInt(str[i]) == 1;
        }
        M = Integer.parseInt(br.readLine()); // 학생 수
        for (int i = 0; i < M; i++) {
            str = br.readLine().split(" ");
            int student = Integer.parseInt(str[0]); // 1: 남, 2: 여
            int idx = Integer.parseInt(str[1]); // 받은 수
            if (student == 1) { // 남학생
                // 받은 수의 배수에 해당하는 스위치 상태 변경
                boyChange(idx);
            } else if (student == 2){ // 여학생
                // 받은 수를 기준으로 좌우 대칭 & 가장 범위 넓은 구간에 속하는 스위치 상태 변경
                girlChange(idx);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(switches[i] == true ? 1 : 0).append(" ");
            if (i % 20 == 0) sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void boyChange(int num) {
        for (int i = 1; i <= N; i++) {
            if (i % num == 0) switches[i] = !switches[i];
        }
    }

    public static void girlChange(int num) {
        int l = num - 1, r = num + 1;
        while (l > 0 && r <= N) { // 상태 변경할 범위 찾기
            if (switches[l] != switches[r]) break;
            l--; r++;
        }
        for (int i = l + 1; i < r; i++) {
            switches[i] = !switches[i];
        }
    }
}