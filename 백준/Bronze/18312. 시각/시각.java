import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        // 00시 00분 00초 ~ N시 59분 59초
        // K가 하나라도 포함되는 시각
        int total = 0;
        String value = String.valueOf(K);
        for (int h = 0; h <= N; h++) {
            String hour = String.valueOf(h);
            if (hour.length() == 1) hour = "0" + hour;
            if (hour.contains(value)) { // 시에 K가 포함된 경우
                total += 60 * 60;
                continue;
            }
            for (int m = 0; m < 60; m++) {
                String minute = String.valueOf(m);
                if (minute.length() == 1) minute = "0" + minute;
                if (minute.contains(value)) { // 분에 K가 포함된 경우
                    total += 60;
                    continue;
                }
                for (int s = 0; s < 60; s++) {
                    String second = String.valueOf(s);
                    if (second.length() == 1) second = "0" + second;
                    if (second.contains(value)) { // 초에 K가 포함된 경우
                        total++;
                    }
                }
            }
        }
        System.out.println(total);
    }
}