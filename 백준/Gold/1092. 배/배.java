import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Integer[] crane = new Integer[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            crane[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(crane, Collections.reverseOrder());

        int m = Integer.parseInt(br.readLine());
        Integer[] box = new Integer[m];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < m; i++) {
            box[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(box, Collections.reverseOrder());

        if (box[0] > crane[0]) {
            System.out.println(-1);
            return;
        }

        boolean[] used = new boolean[m]; // 박스 옮김 여부
        int time = 0, total = 0; // time: 소요 시간, total: 옮긴 박스의 수
        while (true) {
            if (total == m) break;
            int bIdx = 0, cIdx = 0;
            while (true) {
                if (bIdx == m || cIdx == n) break;
                else if (!used[bIdx] && box[bIdx] <= crane[cIdx]) {
                    used[bIdx] = true;
                    bIdx++;
                    cIdx++;
                    total++;
                } else {
                    bIdx++;
                }
            }
            time++;
        }
        System.out.println(time);
    }
}