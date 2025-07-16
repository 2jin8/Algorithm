import java.util.*;
import java.io.*;

class Solution {
    public String solution(String s) {
        StringTokenizer st = new StringTokenizer(s, " ");
        int N = st.countTokens();
        int[] numbers = new int[N];
        for (int i=0; i<N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);
        return numbers[0] + " " + numbers[N - 1];
    }
}