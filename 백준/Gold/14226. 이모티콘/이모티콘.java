import java.util.*;

public class Main {
    private static boolean[][] visited = new boolean[1001][1001];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        bfs(s);
    }

    public static void bfs(int s) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 1, 0));
        visited[0][1] = true;
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            int clipBoard = now.clipBoard;
            int total = now.total;
            if (total == s) {
                System.out.println(now.time);
                return;
            }

            // 1. 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.
            queue.offer(new Node(total, total, now.time + 1));

            // 2. 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
            if (clipBoard != 0 && clipBoard + total <= s && !visited[clipBoard][clipBoard + total]) {
                queue.offer(new Node(clipBoard, clipBoard + total, now.time + 1));
                visited[clipBoard][clipBoard + total] = true;
            }

            // 3. 화면에 있는 이모티콘 중 하나를 삭제한다.
            if (total >= 1 && !visited[clipBoard][total - 1]) {
                queue.offer(new Node(clipBoard, total - 1, now.time + 1));
                visited[clipBoard][total - 1] = true;
            }
        }
    }
}

class Node {
    int total; // 만들어진 스티커의 수
    int clipBoard; // 클립보드에 저장된 스티커의 수
    int time; // 걸린 시간

    public Node(int clipBoard, int total, int time) {
        this.clipBoard = clipBoard;
        this.total = total;
        this.time = time;
    }
}