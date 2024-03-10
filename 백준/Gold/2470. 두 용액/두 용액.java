import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr); // 오름차순 정렬

        // 0에 가장 가깝게
        int l = 0, r = N - 1, ans1 = 0, ans2 = 0;
        long min = Long.MAX_VALUE;
        while (l < r) {
            long diff = arr[l] + arr[r]; // 특성값
            if (min > Math.abs(diff)) { // min보다 작은 값이라면 갱신
                ans1 = arr[l];
                ans2 = arr[r];
                if (diff == 0) break; // 특성값이 0이라면 탐색 종료
                min = Math.abs(diff);
            }

            if (diff < 0) l++; // 특성값이 0 미만이라면, 더 큰 값과 더해질 수 있도록 l 증가
            else r--; // 특성값이 0 초과라면, 더 작은 값과 더해질 수 있도록 r 감소
        }
        System.out.println(ans1 + " " + ans2);
    }
}