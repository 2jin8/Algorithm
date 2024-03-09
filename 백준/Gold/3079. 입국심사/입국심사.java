import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        long max = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }
        // left = 0, right = 심사하는데 가장 오래 걸리는 시간 * M명(= 최대로 걸리는 시간)
        System.out.println(biSearch(0, max * M)); 
    }

    public static long biSearch(long left, long right) {
        long ans = Long.MAX_VALUE;
        while (left <= right) {
            long mid = (left + right) / 2;
            long cnt = 0;
            for (int a : arr) { // mid 시간이 걸린다고 할 때, 계산할 수 있는 인원의 수 구하기 
                cnt += mid / a;
                if (cnt >= M) break; 
            }

            if (cnt >= M) { // M명보다 많이 계산할 수 있는 경우
                ans = Math.min(ans, mid); // 답 기록
                right = mid - 1; // 걸리는 시간을 줄이기(왼쪽으로 범위 좁히기)
            } else { // M명보다 적게 계산할 수 있는 경우
                left = mid + 1; // 걸리는 시간을 늘릭(오른쪽으로 범위 좁히기)
            }
        }
        return ans;
    }
}