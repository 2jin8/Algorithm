import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    static Integer[] count = new Integer[26]; // A ~ Z
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Arrays.fill(count, 0); // Integer는 null 로 초기화 됨!
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            int value = 1;
            for (int j = str.length() - 1; j >= 0; j--) {
                char c = str.charAt(j);
                count[c - 'A'] += value;
                value *= 10;
            }
        }

        Arrays.sort(count, Collections.reverseOrder()); // 내림차순 정렬
        int mul = 9, total = 0;
        for (Integer c : count) {
            if (c == 0) break;

            total += mul-- * c; // 가장 많이 차지하는 수부터 큰 수 할당
        }
        System.out.println(total);
    }
}
