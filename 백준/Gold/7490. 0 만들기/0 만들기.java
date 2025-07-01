import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static String[] calc = {"+", "-", " "};
    static ArrayList<String> answerList = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            answerList.clear();
            makeZero(2, "1");
            Collections.sort(answerList); // 아스키 순서로 출력하기 위해 정렬
            for (String answer : answerList) { 
                sb.append(answer).append("\n");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void makeZero(int number, String answer) {
        if (number > N) {
            String replaceAnswer = answer.replace(" ", ""); // 공백 제거
            if (getSum(replaceAnswer) == 0) {
                // 0이 되는 수식 출력
                answerList.add(answer);
            }
            return;
        }

        for (int i = 0; i < 3; i++) {
            makeZero(number + 1, answer + calc[i] + number);
        }
    }

    // 수식 계산 메서드
    static int getSum(String answer) {
        StringTokenizer st = new StringTokenizer(answer, "+-", true);
        int sum = Integer.parseInt(st.nextToken());
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            switch (token) {
                case "+":
                    sum += Integer.parseInt(st.nextToken());
                    break;
                case "-":
                    sum -= Integer.parseInt(st.nextToken());
                    break;
            }
        }
        return sum;
    }
}
