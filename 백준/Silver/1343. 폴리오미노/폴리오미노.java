import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String board = br.readLine();
        StringTokenizer st = new StringTokenizer(board, ".");
        String[] change = new String[st.countTokens()];
        int idx = 0;
        while (st.hasMoreTokens()) {
            String b = st.nextToken();
            if (b.length() % 2 != 0) { // X의 수가 홀수인 경우
                System.out.println(-1); // 덮을 수 없음
                return;
            }

            StringBuilder sb = new StringBuilder();
            int len = b.length();
            for (int i = 0; i < len / 4; i++) { // 사전순 → A로 먼저 덮기
                sb.append("AAAA");
            }
            len %= 4;
            for (int i = 0; i < len / 2; i++) {
                sb.append("BB");
            }
            change[idx++] = sb.toString();
        }

        StringBuilder result = new StringBuilder();
        idx = 0;
        for (int i = 0; i < board.length(); i++) {
            char c = board.charAt(i);
            if (c == 'X') {
                String s = change[idx++];
                result.append(s);
                i += s.length() - 1;
            } else {
                result.append(c);
            }
        }
        System.out.println(result);
    }
}