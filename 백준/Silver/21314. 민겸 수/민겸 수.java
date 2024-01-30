import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder result = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        findMax(str);
        findMin(str);
        System.out.println(result);
    }

    public static void findMax(String str) {
        // K를 기준으로 나누고 변환하기 (MKKMMK → MK, K, MMK)
        int m = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'M') m++;
            else {
                result.append("5").append("0".repeat(m));
                m = 0;
            }
        }
        if (m != 0) result.append("1".repeat(m));
        result.append("\n");
    }

    public static void findMin(String str) {
        // 각각 한 토큰씩 확인하기
        StringTokenizer st = new StringTokenizer(str, "K", true);
        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            int m = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == 'M') m++;
                else {
                    result.append("5");
                    m = 0;
                }
            }
            if (m != 0) result.append("1").append("0".repeat(m - 1));
        }
    }
}