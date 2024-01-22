import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] eight = {"000", "001", "010", "011", "100", "101", "110", "111"};
        char[] chars = br.readLine().toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            sb.append(eight[chars[i] - '0']);
        }


        while (true) {
            if (sb.length() != 0 && sb.charAt(0) == '0') {
                sb.deleteCharAt(0);
            } else {
                break;
            }
        }
        if (sb.length() == 0) System.out.println(0);
        else System.out.println(sb);
    }
}