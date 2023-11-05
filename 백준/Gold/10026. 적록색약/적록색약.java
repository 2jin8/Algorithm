import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static boolean[][] twoVisited;
    private static boolean[][] threeVisited;

    private static int[][] twoColor;
    private static int[][] threeColor;
    private static int count = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        init(n);

        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                switch (str[j]) {
                    case "R":
                        twoColor[i][j] = 1;
                        threeColor[i][j] = 1;
                        break;
                    case "G":
                        twoColor[i][j] = 1;
                        threeColor[i][j] = 2;
                        break;
                    case "B":
                        twoColor[i][j] = 0;
                        threeColor[i][j] = 0;
                        break;
                }
            }
        }

        // 적록색약 X
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!threeVisited[i][j]) {
                    DFS(threeColor[i][j], threeColor, threeVisited, i, j);
                    count++;
                }
            }
        }
        System.out.print(count + " ");
        count = 0;
        
        // 적록색약 O
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!twoVisited[i][j]) {
                    DFS(twoColor[i][j], twoColor, twoVisited, i, j);
                    count++;
                }
            }
        }
        System.out.println(count);
        br.close();
    }

    private static void init(int n) {
        twoColor = new int[n][n];
        threeColor = new int[n][n];

        twoVisited = new boolean[n][n];
        threeVisited = new boolean[n][n];
    }

    private static void DFS(int checkNum, int[][] color, boolean[][] visited, int i, int j) {
        visited[i][j] = true;

        // 위(i + 1)
        if (i != color.length - 1) {
            if (color[i + 1][j] == checkNum && !visited[i + 1][j]) {
                DFS(checkNum, color, visited, i + 1, j);
            }
        }

        // 아래(i - 1)
        if (i != 0) {
            if (color[i - 1][j] == checkNum && !visited[i - 1][j]) {
                DFS(checkNum, color, visited, i - 1, j);
            }
        }
        // 오른쪽(j + 1)
        if (j != color[0].length - 1) {
            if (color[i][j + 1] == checkNum && !visited[i][j + 1]) {
                DFS(checkNum, color, visited, i, j + 1);
            }
        }

        // 왼쪽(j - 1)
        if (j != 0) {
            if (color[i][j - 1] == checkNum && !visited[i][j - 1]) {
                DFS(checkNum, color, visited, i, j - 1);
            }
        }
    }
}