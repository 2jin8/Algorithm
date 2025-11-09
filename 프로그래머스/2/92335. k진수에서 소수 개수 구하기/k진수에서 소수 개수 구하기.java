import java.util.*;

class Solution {
    public int solution(int n, int k) {
        String number = Integer.toString(n, k);
        System.out.println(number);
        
        int answer = 0;
        // 전부 0이 기준 >> 0으로 자르기
        StringTokenizer st = new StringTokenizer(number, "0");
        while (st.hasMoreTokens()) {
            if (isPrime(Long.parseLong(st.nextToken())))
                answer++;
        }
        return answer;
    }
    
    static boolean isPrime(long number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) 
                return false;
        }
        return true;
    }
}