import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        // 00시 00분 00초 ~ n시 59분 59초
        int total = 0;
        for (int h = 0; h <= n; h++) {
            for (int m = 0; m < 60; m++) {
                for (int s = 0; s < 60; s++) {
                    StringBuilder time = new StringBuilder();
                    time.append(h < 10 ? "0" + h : "" + h)
                            .append(m < 10 ? "0" + m : "" + m)
                            .append(s < 10 ? "0" + s : "" + s);
                    if (time.toString().contains("" + k)) {
                        total++;
                    }
                }
            }
        }
        System.out.println(total);
    }
}