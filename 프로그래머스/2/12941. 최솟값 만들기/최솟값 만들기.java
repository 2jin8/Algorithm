import java.util.*;
class Solution
{
    public int solution(int []A, int []B)
    {
        // 각 배열의 값을 곱해서 최소가 되도록
        // 큰 값과 작은 값을 곱해야 함
        Arrays.sort(A);
        Arrays.sort(B);
        
        int answer = 0;
        int len = A.length;
        for (int i=0; i<len; i++) {
            answer += A[i] * B[len - 1 - i];
        }
        return answer;
    }
}