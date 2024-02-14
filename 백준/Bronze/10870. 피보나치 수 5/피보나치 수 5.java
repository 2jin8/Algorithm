import java.io.*;
public class Main {
    static final int MAX = 20;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[MAX + 1];
        arr[0] = 0; arr[1] = 1;
        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        System.out.println(arr[n]);
    }
}