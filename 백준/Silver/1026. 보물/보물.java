import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        Integer[] B = new Integer[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A); // 오름차순 정렬

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(B, Collections.reverseOrder()); // 내림차순 정렬

        int total = 0;
        for (int i = 0; i < n; i++) {
            total += A[i] * B[i];
        }
        System.out.println(total);
    }
}