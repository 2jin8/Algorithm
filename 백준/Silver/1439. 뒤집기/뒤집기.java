import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer zeroTokenizer = new StringTokenizer(str, "0");
        StringTokenizer oneTokenizer = new StringTokenizer(str, "1");
        System.out.println(Math.min(zeroTokenizer.countTokens(), oneTokenizer.countTokens()));
    }
}