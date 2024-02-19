import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dots;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dots = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            dots[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(dots);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if (end <= dots[0] || start >= dots[N - 1]) {
                sb.append(0).append("\n");
                continue;
            }
            int s = findStart(start);
            int e = findEnd(end);

            sb.append(e - s + 1).append("\n");
        }
        System.out.println(sb);
    }

    public static int findStart(int dot) {
        int start = 0, end = N - 1;
        int idx = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (dots[mid] >= dot) {
                idx = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return idx;
    }

    public static int findEnd(int dot) {
        int start = 0, end = N - 1;
        int idx = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (dots[mid] <= dot) {
                idx = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return idx;
    }
}