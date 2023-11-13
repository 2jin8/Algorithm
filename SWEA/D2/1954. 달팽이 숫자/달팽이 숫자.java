import java.util.Scanner;

class Solution
{
    static int n, num, mid;
    static int[] dxUp = {-1, 0, 1, 0};
    static int[] dyUp = {0, 1, 0, -1};
    static int[] dxDown = {1, 0, -1, 0};
    static int[] dyDown = {0, -1, 0, 1};
    
    public static void main(String args[]) throws Exception
    {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            n = sc.nextInt();
            num = 1;
            mid = (int) (Math.ceil(n / 2.0));
            int[][] snail = new int[n][n];
            dfs(snail, 0, 0);
            
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                	sb.append(snail[i][j]+ " ");
                }
                sb.append("\n");
            }
            System.out.println("#"+test_case);
            System.out.print(sb.toString());
        }
    }

    public static void dfs(int[][] snail, int x, int y) {
        snail[x][y] = num++;

        for (int i = 0; i < 4; i++) {
            int tx = 0, ty = 0;
            if (x < mid) {
                tx = x + dxUp[i];
                ty = y + dyUp[i];
            } else {
                tx = x + dxDown[i];
                ty = y + dyDown[i];
            }

            if (tx < 0 || ty < 0 || tx >= n || ty >= n)
                continue;

            if (snail[tx][ty] == 0) dfs(snail, tx, ty);
        }
    }
}