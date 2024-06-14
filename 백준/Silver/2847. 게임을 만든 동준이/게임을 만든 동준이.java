import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n =Integer.parseInt(br.readLine());
        int[] scores = new int[n];
        for (int i = 0; i < n; i++) {
            scores[i] = Integer.parseInt(br.readLine());
        }

        int total = 0;
        for (int i = n - 1; i > 0; i--) {
            if (scores[i - 1] >= scores[i]) { // 점수가 증가하는 형태가 아닌 경우
                total += scores[i - 1] - scores[i] + 1;
                scores[i - 1] = scores[i] - 1;
            }
        }
        System.out.println(total);
    }
}