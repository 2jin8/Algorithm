import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] stair = new int[n];
        for (int i = 0; i < n; i++) {
            stair[i] = scan.nextInt();
        }

        int[] score = new int[n];

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                score[0] = stair[0];
            } else if (i == 1) {
                score[1] = stair[0] + stair[1];
            } else if (i == 2) {
                score[2] = Math.max(stair[0], stair[1]) + stair[2];
            } else {
                score[i] = Math.max(score[i - 2], score[i - 3] + stair[i - 1]) + stair[i];
            }
        }
        System.out.println(score[n - 1]);
    }
}