import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        BigInteger a = new BigInteger(str[0]);
        BigInteger b = new BigInteger(str[1]);
        System.out.println(a.add(b));
    }
}