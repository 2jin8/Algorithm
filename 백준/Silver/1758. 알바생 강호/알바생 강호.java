import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Integer[] tips = new Integer[N];
        for (int i = 0; i < N; i++) {
            tips[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(tips, Collections.reverseOrder());


        long total = 0;
        int order = 1;
        for (int tip : tips) {
            int tmp = tip + 1 - order++;
            if (tmp < 0) break; // 음수면 종료
            total += tmp;
        }
        System.out.println(total);
    }
}