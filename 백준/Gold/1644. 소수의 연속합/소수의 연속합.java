import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int n;
    static int[] primes;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n  = sc.nextInt();
        primes = new int[n+1];
        for(int i = 2 ; i < n+1; i++){
            primes[i] = i;
        }

        //소수구하기
        getPrimes();
        
        int ans = 0;

        for(int i =2; i <= n ; i++){
            if(primes[i] != 0){
                //누적합 구하기
                int sum = i;
                if(sum == n){
                    ans++;
                    continue;
                }
                for(int j = i+1; j <= n; j++){
                    if(primes[j] != 0){
                        sum += primes[j];
                        //n이 나오면 count
                        if(sum == n){
                            ans++;
                            break;
                        }
                        if(sum > n){
                            break;
                        }
                    }
                }
            }
        }

        System.out.println(ans);
        sc.close();
    }

    static void getPrimes(){
        for(int i = 2; i <= n; i++){
            if(primes[i] == 0){
                continue;
            }

            for(int j = i + i; j <=n; j+=i){
                primes[j] = 0;
            }
        }
    }



    // 일반 
    // static void getPrimes(){
    //     //0과 1은 제외
    //     for(int i = 2; i <= n; i++){
    //         if(isPrime(i)){
    //             arr.add(i);
    //         }
    //     }
    // }
    // static boolean isPrime(int n){
    //     // .
    //     for(int i = 2; i< n;i++){
    //         if(n % i == 0){
    //             return false;
    //         } 
    //     }
    //     return true;
    // }
}
