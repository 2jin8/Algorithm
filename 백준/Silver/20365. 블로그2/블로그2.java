import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String color = br.readLine();
        int blue = 0;
        for (int i = 0; i < n; i++) {
            if (color.charAt(i) == 'B') blue++;
            while (i < n && color.charAt(i) == 'B') {
                i++;
            }
        }
        int red = 0;
        for (int i = 0; i < n; i++) {
            if (color.charAt(i) == 'R') red++;
            while (i < n && color.charAt(i) == 'R') {
                i++;
            }
        }
        System.out.println(Math.min(red, blue) + 1);
    }
}