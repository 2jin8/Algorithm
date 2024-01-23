import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken()); // 전구의 개수
        int m = Integer.parseInt(st.nextToken()); // 명령어의 개수
        boolean[] lamps = new boolean[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            // 0: off, 1: on
            int status = Integer.parseInt(st.nextToken());
            lamps[i] = status == 1;
        }
        for (int t = 0; t < m; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            int cmd = Integer.parseInt(st.nextToken());

            if (cmd == 1) {
                int i = Integer.parseInt(st.nextToken()) - 1;
                int x = Integer.parseInt(st.nextToken());
                lamps[i] = x == 1;
            } else {
                int l = Integer.parseInt(st.nextToken()) - 1;
                int r = Integer.parseInt(st.nextToken()) - 1;
                if (cmd == 2) {
                    for (int j = l; j <= r; j++) {
                        lamps[j] = !lamps[j];
                    }
                } else if (cmd == 3) {
                    Arrays.fill(lamps, l, r + 1, false);
                } else {
                    Arrays.fill(lamps, l, r + 1, true);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (boolean lamp : lamps) {
            sb.append(lamp ? 1 : 0).append(" ");
        }
        System.out.println(sb);
    }
}