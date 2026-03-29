import java.util.*;

class Solution {
    static ArrayList<int[]> moveList = new ArrayList<>();
    public int[][] solution(int n) {
        hanoi(n, 1, 3, 2);
        
        int N = moveList.size();
        int[][] answer = new int[N][2];
        for (int i=0; i<N; i++) {
            answer[i] = moveList.get(i);
        }
        return answer;
    }
    
    static void hanoi(int n, int from, int to, int via) {
        int[] move = new int[] {from, to};
        
        if (n == 1) { // 원판이 1개 남았으면 그냥 이동(=기록)
            moveList.add(move);            
        } else { // 원판이 여러 개 남았으면 계속 이동
            hanoi(n-1, from, via, to); // 나머지 원판 시작지(from)에서 경유지(via)로 이동
            moveList.add(move); // 현재 원판 도착지로 이동(=기록)
            hanoi(n-1, via, to, from); // 나머지 원판 경유지(via)에서 도착지(to)로 이동
        }
        
    }
}