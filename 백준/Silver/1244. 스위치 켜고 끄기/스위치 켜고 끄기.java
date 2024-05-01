import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int[] switches;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 스위치 개수
        switches = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine()); // 학생의 수
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int student = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());
            if (student == 1) { // 남학생
                switchStatus(number, n + 1, number);
            } else { // 여학생
                int left = number - 1, right = number + 1;
                while (left >= 1 && right <= n) {
                    if (switches[left] != switches[right]) {
                        break;
                    }
                    left--; right++;
                }
                switchStatus(left + 1, right, 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(switches[i]).append(" ");
            if (i % 20 == 0) sb.append("\n");
        }
        System.out.println(sb);
    }

    static void switchStatus(int l, int r, int add) {
        for (int i = l; i < r; i += add) {
            switches[i] = (switches[i] == 1) ? 0 : 1;
        }
    }
}