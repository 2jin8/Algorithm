import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[n];
        boolean[] used = new boolean[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int k = Integer.parseInt(br.readLine());
        int total = 0;
        for (int i = 0; i < n - 1; i++) {
            if (!used[i] || nums[i] < k) { // 사용하지 않았고 원하는 k보다 작은 경우
                int left = k - nums[i];
                for (int j = i + 1; j < n; j++) {
                    if (nums[j] == left) { // i랑 쌍을 이루는 수
                        total++; // 쌍의 수 증가
                        used[i] = true; used[j] = true; // 사용 처리
                        break;
                    }
                }
            }
        }
        System.out.println(total);
    }
}
