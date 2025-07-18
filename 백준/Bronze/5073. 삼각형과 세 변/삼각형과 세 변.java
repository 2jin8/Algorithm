import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == 0 && b == 0 && c == 0)
                break;

            int total = a + b + c;
            int maxLen = Math.max(a, Math.max(b, c));
            if (total - maxLen <= maxLen)
                sb.append("Invalid").append("\n");
            else if (a != b && b != c && c != a)
                sb.append("Scalene").append("\n");
            else if (a == b && b == c)
                sb.append("Equilateral").append("\n");
            else
                sb.append("Isosceles").append("\n");
        }
        System.out.println(sb);
    }
}
