import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 비교를 위해 q는 0, u는 1, a는 2, c는 3, k는 4로 변경
        String str = br.readLine();
        int len = str.length();
        int[] ducks = new int[len];
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (c == 'q') ducks[i] = 0;
            else if (c == 'u') ducks[i] = 1;
            else if (c == 'a') ducks[i] = 2;
            else if (c == 'c') ducks[i] = 3;
            else ducks[i] = 4;
        }

        int total = 0, cnt = 0;
        boolean[] used = new boolean[len];
        while (true) {
            if (cnt > (len / 5)) break;

            int idx = 0;
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                if (!used[i] && ducks[i] == idx) { // 울음 소리의 순서가 맞는 경우
                    list.add(i);
                    idx++;
                    if (idx == 5) idx = 0;
                }
            }

            if (!list.isEmpty() && list.size() % 5 == 0) { // 제대로 된 울음 소리가 저장된 경우
                total++;
                for (int l : list) {
                    used[l] = true;
                }
            }
            cnt++;
        }
        // 끝났을 때 used에 false가 하나라도 있다면 녹음한 소리가 올바르지 않은 것
        if (check(len, used)) System.out.println(total);
        else System.out.printf("-1");
    }

    public static boolean check(int len, boolean[] used) {
        for (int i = 0; i < len; i++) {
            if (!used[i]) return false; // 잘못된 울음 소리가 하나라도 있는 경우
        }
        return true;
    }
}