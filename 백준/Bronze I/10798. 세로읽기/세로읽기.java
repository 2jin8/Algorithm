import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] arr = new char[5][];
        int max = 0;
        for (int i = 0; i < 5; i++) {
            arr[i] = br.readLine().toCharArray();
            max = Math.max(max, arr[i].length);
        }

        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (idx < max) {
            for (int i = 0; i < 5; i++) {
                if (arr[i].length > idx) sb.append(arr[i][idx]);
            }
            idx++;
        }
        System.out.println(sb);
    }
}