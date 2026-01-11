import java.util.*;

class Solution {
    // 한 칸을 기준으로 잡고 2 by 2 확인 -> 4칸이 된다면 없애기 (새로운 배열)
    // 다 탐색한 뒤 0이 된 부분은 아래로 내리기
    // 한 사이클 끝나면 새로운 배열 기존 배열로 복사
    // i: 0 ~ N - 1, j: 0 ~ M - 1
    
    // 새로 없어지는 것들이 없을 때까지 반복
    static char[][] map, newMap;
    static boolean isChanged;
    public int solution(int m, int n, String[] board) {
        map = new char[m][n];
        for (int i=0; i<m; i++) {
            map[i] = board[i].toCharArray();
        }

        int answer = 0;
        newMap = new char[m][n];
        while (true) {
            isChanged = false;
            for (int i=0; i<m; i++) {
                newMap[i] = map[i].clone();
            }
            
            // 2 by 2 블럭 터뜨리기
            for (int i=0; i<m-1; i++) {
                for (int j=0; j<n-1; j++) {
                    if (map[i][j] != 'X')
                        answer += checkSquare(i, j);
                }
            }
            
            // 블럭 아래로 이동
            moveDown(m, n);
            
            // 블럭이 터지지 않았으면 종료
            if (!isChanged) break;
        }
        return answer;
    }
    
    static int checkSquare(int x, int y) {
        char n1 = map[x][y], n2 = map[x][y+1];
        char n3 = map[x+1][y], n4 = map[x+1][y+1];
        
        // 2 by 2 블럭인 경우
        int total = 0;
        if (n1 == n2 && n2 == n3 && n3 == n4 && n4 == n1) {
            isChanged = true;
            
            // 부순 블럭의 개수 세기
            if (newMap[x][y] != 'X') total++;
            if (newMap[x][y+1] != 'X') total++;
            if (newMap[x+1][y] != 'X') total++;
            if (newMap[x+1][y+1] != 'X') total++;
                
            // 부순 블록이라고 표시하기
            newMap[x][y] = 'X';
            newMap[x][y+1] = 'X';
            newMap[x+1][y] = 'X';
            newMap[x+1][y+1] = 'X';
        }
        return total;
    }
    
    static void moveDown(int m, int n) {
        for (int i=0; i<m; i++) {
            Arrays.fill(map[i], 'X');
        }
        
        StringBuilder sb = new StringBuilder();
        for (int j=0; j<n; j++) {
            sb.setLength(0);
            for (int i=m-1; i>=0; i--) {
                sb.append(newMap[i][j]);
            }
            
            String line = sb.toString().replace("X", "");
            int idx = m-1;
            for (char l : line.toCharArray()) {
                map[idx--][j] = l;
            }
        }
    }
}