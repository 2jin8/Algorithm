import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 남학생끼리, 여학생끼리 & 같은 학년끼리
        // 한 방에 배정할 수 있는 최대 인원수 K,, 필요한 방의 최소 개수
        // 학년별 upper(학생 수 / k)
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] students = new int[6][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int sex = Integer.parseInt(st.nextToken()); // 0: 여학생, 1: 남학생
            int grade = Integer.parseInt(st.nextToken());
            students[grade - 1][sex]++;
        }

        int total = 0;
        for (int i = 0; i < 6; i++) {
            int girl = students[i][0];
            int boy = students[i][1];
            if (girl != 0) {
                total += (int) Math.ceil((double) girl / k);
            }
            if (boy != 0) {
                total += (int) Math.ceil((double) boy / k);
            }
        }
        System.out.println(total);
    }
}