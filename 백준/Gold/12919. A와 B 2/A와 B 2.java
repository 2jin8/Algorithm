import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static boolean isMake;
    static String S, T;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        makeS(T);
        System.out.println(isMake? 1 : 0);
    }

    static void makeS(String newT) {
        if (isMake) return;

        if (newT.length() == S.length()) {
            if (newT.equals(S)) {
                isMake = true;
            }
            return;
        }

        // A로 끝나면 제거하기
        StringBuilder sb = new StringBuilder(newT);
        if (newT.charAt(newT.length() - 1) == 'A') {
            makeS(sb.deleteCharAt(newT.length() - 1).toString());
        }

        // B로 시작하면 제고하고 뒤집기
        sb = new StringBuilder(newT);
        if (newT.charAt(0) == 'B') {
            makeS(sb.deleteCharAt(0).reverse().toString());
        }
    }
}
