import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 회전 벨트 접시의 수
        int d = sc.nextInt(); // 초밥 가짓수
        int k = sc.nextInt(); // 연속 접시의 수
        int c = sc.nextInt(); // 쿠폰
        
        
        int[] s = new int[n];
        int[] arr = new int[d+1];

        for(int i = 0 ; i < n; i++){
            s[i] = sc.nextInt();
        }
        int start = 0;
        int end = n;
        int ans = 0; 
        while(start < end){
            int i = start;
            while(i < start+k){
                int sushi = s[i % n];
                //가짓수 배열 +1
                arr[sushi] += 1;

                i++;
            }
            //쿠폰도 +1;
            arr[c]++;

            int num = 0;
            for(int z = 0 ; z < d+1; z++){
                if(arr[z] != 0){
                    num++;
                    arr[z] = 0;
                }
            }
            ans = Math.max(ans, num);
            start++;
        }

        System.out.println(ans);
    }
}
