import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int s = sc.nextInt();
        int ans = n+1;
        int[] prefixSum = new int[n+1];
        prefixSum[1] = sc.nextInt();
        if(prefixSum[1] >= s){
            System.out.println(1);
            return;
        }
        for(int i = 2; i <= n; i++){
            prefixSum[i] = prefixSum[i-1] + sc.nextInt();
            
            if(prefixSum[i] >=s){
                ans = Math.min(ans,i);
                //0부터 말고 최소 길이 크기 부터 시작
                if(i-ans >= 0){
                    for(int j = i-ans+1; j <= i; j++){
                        if(prefixSum[i] - prefixSum[j] < s){
                            ans = i - j+1;
                            break;
                        }
                    }
                }
            }

        }
        if(ans == n+1){
            System.out.println(0);
        }
        else{
            System.out.println(ans);
        }
        sc.close();
    }
}

