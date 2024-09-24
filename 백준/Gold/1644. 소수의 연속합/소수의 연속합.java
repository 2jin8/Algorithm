import java.util.Scanner;
import java.util.*;
public class Main{
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
        
        ArrayList<Integer> p = new ArrayList<>();
        for(int i = 2; i <= n; i++){
            if(primes[i] != 0){
                p.add(primes[i]);
            }
        }
        int ans = 0;
        int start = 0;
        int end = 0;
        int sum = 0;
        while(end < p.size()){
            if(sum < n){
                sum += p.get(end++);
            }
            if(sum > n){
                sum -= p.get(start++);
            }
            if(sum == n){
                sum -= p.get(start++);
                ans++;
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
