import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int A = Integer.parseInt(st.nextToken()); // 쌓이는 피로도
        int B = Integer.parseInt(st.nextToken()); // 처리한 일
        int C = Integer.parseInt(st.nextToken()); // 줄어드는 피로도
        int M = Integer.parseInt(st.nextToken()); // 번아웃 방지 최대치 피로도

        int tired = 0, work = 0;
        for (int i = 0; i < 24; i++) {
            if (tired + A > M) { // 일을 한다면 번아웃이 발생하는 경우
                // 쉬어주기
                tired = Math.max(0, tired - C); // 음수 방지
            } else {
                // 일하기
                tired += A;
                work += B;
            }
        }
        System.out.println(work);
    }
}