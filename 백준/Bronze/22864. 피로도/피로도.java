import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int a = Integer.parseInt(st.nextToken()); // 쌓이는 피로도
        int b = Integer.parseInt(st.nextToken()); // 처리할 수 있는 일의 양
        int c = Integer.parseInt(st.nextToken()); // 줄어드는 피로도
        int m = Integer.parseInt(st.nextToken()); // 최대 피로도

        // 24번 반복
        int work = 0, tired = 0;
        for (int i = 0; i < 24; i++) {
            if (tired + a > m) { // 쉬지 않을 때 피로도가 m을 넘는 경우
                tired = Math.max(0, tired - c);
            } else { // 쉬지 않고 일을 하는 경우
                tired += a;
                work += b;
            }
        }
        System.out.println(work);
    }
}