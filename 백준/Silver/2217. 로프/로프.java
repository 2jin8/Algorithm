import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] ropes = new int[n];
        for (int i = 0; i < n; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }
        // 들어 올릴 수 있는 중량이 작은 로프를 하나씩 빼서 확인하기 위해 오름차순 정렬
        Arrays.sort(ropes);

        int weight = 0, k = n;
        for (int rope : ropes) {
            weight = Math.max(weight, rope * k--);
        }
        System.out.println(weight);
    }
}