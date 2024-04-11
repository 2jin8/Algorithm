import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringTokenizer zero = new StringTokenizer(s, "1");
        StringTokenizer one = new StringTokenizer(s, "0");
        System.out.println(Math.min(zero.countTokens(), one.countTokens()));
    }
}