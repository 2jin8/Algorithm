import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int NUM = 10;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] cows = new ArrayList[NUM + 1];
        for (int i = 0; i <= NUM; i++) {
            cows[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int cow = Integer.parseInt(st.nextToken());
            int move = Integer.parseInt(st.nextToken());
            cows[cow].add(move);
        }

        int moveCnt = 0;
        for (int i = 1; i <= NUM; i++) {
            if (cows[i].size() <= 1) continue;
            // 배열의 크기가 1보다 큰 경우 건넜는지 확인
            int before = cows[i].get(0);
            for (int j = 1; j < cows[i].size(); j++) { 
                int now = cows[i].get(j);
                if (before != now) moveCnt++;
                before = now;
            }
        }
        System.out.println(moveCnt);
    }
}