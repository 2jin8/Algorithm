import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashSet<String> words = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String password = br.readLine();
            words.add(password);
            sb.setLength(0);
            String reverse = sb.append(password).reverse().toString();
            if (words.contains(reverse)) {
                System.out.println(password.length() + " " + password.charAt(password.length() / 2));
                break;
            }
        }
    }
}
