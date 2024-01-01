import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        HashMap<Integer, String> findString = new HashMap<>();
        HashMap<String, Integer> findDigit = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            String s = br.readLine();
            findString.put(i, s);
            findDigit.put(s, i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String str = br.readLine();
            if (Character.isDigit(str.charAt(0))) { // 입력이 숫자
                sb.append(findString.get(Integer.parseInt(str))).append("\n");
            } else { // 입력이 문자
                sb.append(findDigit.get(str)).append("\n");
            }
        }
        System.out.println(sb);
    }
}