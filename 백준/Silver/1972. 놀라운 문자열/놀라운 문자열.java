import java.io.*;
import java.util.*;

public class Main {
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String str = br.readLine();
            n = str.length();
            if (str.equals("*")) {
                System.out.println(sb.toString());
                break;
            }

            boolean result = checkPair(str);
            sb.append(str);
            if (result) sb.append(" is surprising.").append("\n");
            else sb.append(" is NOT surprising.").append("\n");
        }
    }

    public static boolean checkPair(String str) {
        String[] strings = str.split("");
        for (int i = 1; i <= n - 1; i++) {
            HashSet<String> hashSet = new HashSet<>();
            for (int j = 0; j < n - i; j++) {
                String pair = strings[j] + strings[j + i];
                if (!hashSet.contains(pair)) {
                    hashSet.add(pair);
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}