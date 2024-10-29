import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static int[] now, want; // now: 현재 상태, want: 만들고자 하는 상태
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        now = new int[N]; want = new int[N];
        String[] strings = br.readLine().split("");
        for (int i = 0; i < N; i++) {
            now[i] = Integer.parseInt(strings[i]);
        }

        strings = br.readLine().split("");
        for (int i = 0; i < N; i++) {
            want[i] = Integer.parseInt(strings[i]);
        }

        if (Arrays.equals(now, want)) {
            System.out.println(0);
            return;
        }

        int c1 = pressOne();
        int c2 = unpressOne();
        if (c1 == -1 && c2 == -1)
            System.out.println(-1);
        else if (c1 == -1)
            System.out.println(c2);
        else if (c2 == -1)
            System.out.println(c1);
        else
            System.out.println(Math.min(c1, c2));
    }

    // 1번 전구 눌렀을 때
    private static int pressOne() {
        int[] now1 = now.clone();
        now1[0] = now1[0] == 0? 1: 0;
        now1[1] = now1[1] == 0? 1: 0;

        int result = pressButton(now1);
        return result == -1? -1 : result + 1;
    }

    // 1번 전구 누르지 않았을 때
    private static int unpressOne() {
        int[] now2 = now.clone();
        
        return pressButton(now2);
    }

    private static int pressButton(int[] ary) {
        int cnt = 0;
        for (int i = 1; i < N; i++) {
            if (ary[i - 1] != want[i - 1]) {
                ary[i - 1] = ary[i - 1] == 0? 1: 0;
                ary[i] = ary[i] == 0? 1: 0;
                if (i != N - 1) {
                    ary[i + 1] = ary[i + 1] == 0 ? 1 : 0;
                }
                cnt++;
            }
            
            if (Arrays.equals(ary, want))
                return cnt;
        }
            
       return -1;
    }

}