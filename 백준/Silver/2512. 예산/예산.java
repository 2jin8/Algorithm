import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int end = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            end = Math.max(end, arr[i]);
        }
        M = Integer.parseInt(br.readLine());
        System.out.println(bSearch(0, end));
    }

    public static int bSearch(int start, int end) {
        int result = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            int money = 0;
            for (int i = 0; i < N; i++) {
                if (mid >= arr[i]) money += arr[i];
                else money += mid;
            }
            if (money <= M) { // 오른쪽으로 범위 좁히기
                result = mid;
                start = mid + 1;
            } else { // 왼쪽으로 범위 좁히기
                end = mid - 1;
            }
        }
        return result;
    }

}