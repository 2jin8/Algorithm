import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[i] = Integer.parseInt(st.nextToken());
        }

        // 센서의 좌료를 오름차순으로 정렬
        Arrays.sort(pos);

        // 다음 센서와의 차이 diff 배열에 기록하기
        int[] diff = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            diff[i] = pos[i + 1] - pos[i];
        }

        // 센서와의 차이를 오름차순으로 정렬
        Arrays.sort(diff);

        // 센서 n개를 담당하는 집중국 k개
        // 센서를 n개에서 k개로 합쳐서 집중국이 k개를 담당할 수 있도록 함
        // 따라서 n - k번 합쳐야 함
        int total = 0;
        for (int i = 0; i < n - k; i++) { // n - k번 합치기
            total += diff[i];
        }
        System.out.println(total);
    }
}