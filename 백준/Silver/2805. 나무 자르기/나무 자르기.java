import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        int max = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }
        System.out.println(binarySearch(arr, 1, max));;
    }

    public static int binarySearch(int[] arr, int start, int end) {
        int result = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            long sum = 0;
            for (int i = 0; i < N; i++) {
                // 나무의 높이가 자르는 높이보다 큰 경우
                if (arr[i] > mid) sum += arr[i] - mid;
            }

            if (sum < M) { // 가져가야 할 나무가 부족한 경우, 자르는 높이 낮추기
                end = mid - 1;
            } else { // 가져가야 할 나무가 충분하거나 같은 경우, 자르는 높이 높이기
                result = mid;
                start = mid + 1;
            }
        }
        return result;
    }
}