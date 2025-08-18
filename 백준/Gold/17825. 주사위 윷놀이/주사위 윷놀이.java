import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int answer;
    static Node[] board;
    static int[] dices, horses, places;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        dices = new int[10];
        horses = new int[10]; // 선택한 말 번호
        for (int i = 0; i < 10; i++) {
            dices[i] = Integer.parseInt(st.nextToken());
        }

        board = new Node[33]; // 총 33개의 점 (시작점, 도착점 포함)
        initBoard();
        startGame(0);
        System.out.println(answer);
    }

    public static int moveHorse() {
        int score = 0;
        for (int i = 0; i < 10; i++) {
            int horse = horses[i]; // 말의 번호
            int dice = dices[i]; // 이동 횟수

            // 현재 말 이동하기
            int now = places[horse];
            visited[now] = false;

            // 시작 위치가 파란 블럭인지 확인
            Node nextNode = board[now];
            int next = nextNode.isBlue ? nextNode.nextBlue : nextNode.next;
            for (int j = 1; j < dice; j++) {
                next = board[next].next;
                if (board[next].isLast) break;
            }

            // 이동한 칸에 이미 다른 말이 놓여져있다면 해당 순서는 불가능한 것 (도착 지점 제외)
            if (!board[next].isLast && visited[next]) return -1;

            // 말 위치 갱신 & 점수 계산
            places[horse] = next;
            score += board[next].score;

            // 도착 지점 제외 방문 체크
            visited[next] = true;
        }
        return score;
    }

    static void startGame(int depth) {
        // 이동할 말 모두 선택
        if (depth == 10) {
            places = new int[4]; // 각 말의 위치
            visited = new boolean[33]; // 각 점에 대한 방문 배열

            int score = moveHorse();
            if (score != -1) answer = Math.max(answer, score);
            return;
        }

        // 이동할 말 선택 (중복순열)
        for (int i = 0; i < 4; i++) {
            horses[depth] = i;
            startGame(depth + 1);
        }
    }

    static void initBoard() {
        // 출발 지점
        board[0] = new Node(0, 1);

        // ↺
        for (int i = 1; i < 20; i++) {
            board[i] = new Node(i * 2, i + 1);
        }
        board[20] = new Node(40, 32);

        // →
        board[5].isBlue = true;
        board[5].nextBlue = 21;
        board[21] = new Node(13, 22);
        board[22] = new Node(16, 23);
        board[23] = new Node(19, 29);

        // ↑
        board[10].isBlue = true;
        board[10].nextBlue = 24;
        board[24] = new Node(22, 25);
        board[25] = new Node(24, 29);

        // ←
        board[15].isBlue = true;
        board[15].nextBlue = 26;
        board[26] = new Node(28, 27);
        board[27] = new Node(27, 28);
        board[28] = new Node(26, 29);

        // 중앙 & ↑
        board[29] = new Node(25, 30);
        board[30] = new Node(30, 31);
        board[31] = new Node(35, 20);

        // 도착 지점
        board[32] = new Node(0, 32);
        board[32].isLast = true;
    }

    static class Node {
        int next, nextBlue, score;
        boolean isBlue, isLast;

        public Node(int score, int next) {
            this.score = score;
            this.next = next;
        }
    }
}