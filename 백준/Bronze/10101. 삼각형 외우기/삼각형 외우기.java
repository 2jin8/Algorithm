import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] radius = new int[3];
        int total = 0;
        for (int i = 0; i < 3; i++) {
            radius[i] = Integer.parseInt(br.readLine());
            total += radius[i];
        }

        // 세 각의 합이 180이 아닌 경우
        if (total != 180) {
            System.out.println("Error");
            return;
        }

        // 세 각의 크기가 모두 60인 경우
        if (radius[0] == radius[1] && radius[1] == radius[2]) {
            System.out.println("Equilateral");
        }
        // 같은 각이 없는 경우
        else if ((radius[0] != radius[1]) && (radius[0] != radius[2]) && (radius[1] != radius[2])) {
            System.out.println("Scalene");
        } 
        // 두 각이 같을 때
        else {
            System.out.println("Isosceles");
        }
    }
}
