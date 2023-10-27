import java.util.*;

public class Main {
    static final String A = "AAAA";
    static final String B = "BB";
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String string = scan.nextLine();
        StringTokenizer st = new StringTokenizer(string, ".");
        int tokenCnt = st.countTokens();
        String[] changes = new String[tokenCnt];
        for (int i = 0; i < tokenCnt; i++) {
            String s = st.nextToken();
            int len = s.length();
            if (len % 2 != 0) { // X의 개수가 홀수면 완전히 덮기 불가능
                System.out.println(-1);
                return;
            }

            if (len == 2) { // X가 2개면 BB로 덮기
                changes[i] = B;
            } else if (len == 4) { // 4개면 AAAA로 덮기
                changes[i] = A;
            } else { //  최대한 AAAA로 덮고 나머지 2개를 BB로 덮기
                StringBuilder sb = new StringBuilder();
                int count4 = len / 4; // AAAA를 사용할 개수
                int count2 = (len % 4) / 2; // BB를 사용할 개수
                for (int j = 0; j < count4; j++) sb.append(A);
                for (int j = 0; j < count2; j++) sb.append(B);
                changes[i] = sb.toString();
            }
        }

        int cIdx = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == 'X') {
                String change = changes[cIdx++];
                sb.append(change);
                i += change.length() - 1;
            } else {
                sb.append(c);
            }
        }
        System.out.println(sb);
    }
}