import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

    static HashSet<String> visited = new HashSet<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        int answer = 0;
        while (true) {
            // 두 단어가 같아지면 기록 후 종료
            if (S.equals(T)) {
                answer = 1;
                break;
            }

            // 두 단어의 길이가 같아지면 종료
            if (S.length() == T.length()) {
                break;
            }


            char last = T.charAt(T.length() - 1);
            if (last == 'A') {
                // 마지막 문자가 A인 경우
                // 제거하기
                T = T.substring(0, T.length() - 1);
            } else if (last == 'B') {
                // 마지막 문자가 B인 경우
                // 제거 후 뒤집기
                T = T.substring(0, T.length() - 1);
                T = new StringBuilder(T).reverse().toString();
            }
        }
        System.out.println(answer);
    }
}
