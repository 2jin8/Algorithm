import java.util.*;

class Solution {
    public int solution(int N, int number) {
        // dp[i] = N을 i번 사용해서 만들 수 있는 모든 수의 집합
        List<Set<Integer>> dp = new ArrayList<>();
        dp.add(new HashSet<>()); // 0번 (인덱스 맞추기용)
        
        // 사용횟수는 최대 8 (8을 넘으면 -1)
        for (int i=1; i<=8; i++) {
            Set<Integer> set = new HashSet<>();
            
            // 숫자 이어붙이기 (55, 555, ...)
            int num = Integer.parseInt(String.valueOf(N).repeat(i));
            set.add(num);
            
            // 이전 dp들의 조합
            for (int j=1; j<i; j++) {
                Set<Integer> aSet = dp.get(j);
                Set<Integer> bSet = dp.get(i-j);
                for (int a : aSet) {
                    for (int b : bSet) {
                        set.add(a + b);
                        set.add(a - b);
                        set.add(a * b);
                        if (b != 0) set.add(a / b);
                    }
                }
            }
            
            // 표현하려는 수가 존재하면 리턴
            if (set.contains(number))
                return i;
            
            // i번 사용해서 만들 수 있는 모든 수의 집합 저장
            dp.add(set);
        }
        return -1;
    }
}