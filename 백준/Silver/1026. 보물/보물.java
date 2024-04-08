import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Integer[] A = new Integer[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A); // A는 오름차순으로 정렬
        
        Integer[] B = new Integer[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i< n; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(B, (b1, b2) -> b2 - b1); // B는 내림차순으로 정렬
        
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += A[i] * B[i];
        }
        System.out.println(total);
    }
}