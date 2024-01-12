import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int a = Integer.parseInt(str[0]);
        int b = Integer.parseInt(str[1]);
        int c = Integer.parseInt(str[2]);

        System.out.println(pow(a, b, c));
    }

    public static long pow(int a, int b, int c) {
        if (b == 1) return a % c;
        long val = pow(a, b / 2, c);
        val = (val * val) % c;
        if (b % 2 == 0) // b가 홀수라면 그대로 리턴
            return val;
        else // b가 짝수라면 a % c 값 곱해주고 리턴
            return (val * a) % c;
    }
}