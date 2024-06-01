import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashMap<String, Integer> answerOrder = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        String[] answer = new String[n];
        for (int i = 0; i < n; i++) { // 올바른 정답
            answerOrder.put(st.nextToken(), i);
        }


        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) { // 현우의 답안
            answer[i] = st.nextToken();
        }

        int total = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                String a1 = answer[i], a2 = answer[j]; // 현우의 답
                if (answerOrder.get(a1) < answerOrder.get(a2)) { // 두 답의 순서 확인
                    total++;
                }
            }
        }
        System.out.println(total + "/" + (n * (n - 1) / 2));
    }
}