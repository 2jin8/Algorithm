import java.io.*;

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

        int duck = 0, num = len;
        boolean[] used = new boolean[len];
        while (num > 0) { // 각 글자를 탐색할 때까지 반복
            int idx = 0, total = 0;
            for (int i = 0; i < len; i++) {
                if (!used[i] && ducks[i] == idx) { // 사용하지 않았고 오리 울음 소리의 순서와 일치하는 경우
                    used[i] = true; // 사용 처리
                    idx = (idx + 1) % 5; // 다음 울음 소리로 이동
                    total++; 
                }
            }
            num -= total;
            if (total != 0 && total % 5 == 0) { // 올바른 울음 소리인 경우
                duck++;
            } else { // 올바르지 않은 울음 소리인 경우, -1 출력하고 종료하기
                System.out.println(-1);
                return;
            }
        }
        System.out.println(duck);
    }
}