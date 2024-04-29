import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int answer = 0;
    static Ask[] asks;
    static boolean[] used = new boolean[10];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        asks = new Ask[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int number = Integer.parseInt(st.nextToken());
            int strike = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());
            asks[i] = new Ask(number, strike, ball);
        }
        makeNumber(0, 0);

        System.out.println(answer);
    }

    static void makeNumber(int depth, int total) {
        if (depth == 3) {
            // 주어진 질문과 비교했을 때 스트라이크, 볼의 개수가 모두 일치하는지 확인하기
            if (check(String.valueOf(total))) answer++;
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (!used[i]) { // 다른 숫자 세 개로 구성된 세자리수
                used[i] = true;
                makeNumber(depth + 1, total * 10 + i);
                used[i] = false;
            }
        }
    }

    static boolean check(String num) {
        for (Ask ask : asks) {
            int strike = 0, ball = 0;
            String checkNum = String.valueOf(ask.number);
            // 한 자리씩 확인하기
            for (int i = 0; i < 3; i++) {
                char c = num.charAt(i);
                if (checkNum.charAt(i) == c) { // 스트라이크 확인
                    strike++;
                } else { // 볼 확인
                    if (checkNum.contains(String.valueOf(c))) {
                        ball++;
                    }
                }
            }
            if (strike != ask.strike || ball != ask.ball) {
                return false;
            }
        }
        return true;
    }


    static class Ask {
        int number;
        int strike;
        int ball;

        Ask(int number, int strike, int ball) {
            this.number = number;
            this.strike = strike;
            this.ball = ball;
        }
    }
}