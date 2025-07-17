import java.util.*;

class Solution {
    
    static int[] a = {1, 2, 3, 4, 5};
    static int[] b = {2, 1, 2, 3, 2, 4, 2, 5};
    static int[] c = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    
    public int[] solution(int[] answers) {
        int[] count = new int[4];
        int N = a.length, M = b.length, K = c.length;
        for (int i = 0; i < answers.length; i++) {
            if (a[i % a.length] == answers[i]) count[1]++;
            if (b[i % b.length] == answers[i]) count[2]++;
            if (c[i % c.length] == answers[i]) count[3]++;
        }

        int max = 0;
        ArrayList<Integer> answerList = new ArrayList<>();
        for (int i=1; i<=3; i++) {
            if (max < count[i]) {
                answerList.clear();
                answerList.add(i);
                max = count[i];
            } else if (max == count[i]) {
                answerList.add(i);
            }
        }
        return answerList.stream().sorted().mapToInt(i -> i).toArray();
    }
}