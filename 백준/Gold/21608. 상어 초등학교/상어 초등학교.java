import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] room;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static HashMap<Integer, Pos> hashMap = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        room = new int[n][n];

        HashMap<Integer, List<Integer>> favorites =  new HashMap<>();
        for (int i = 0; i < n * n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int student = Integer.parseInt(st.nextToken()); // 학생의 번호
            List<Integer> favorite = new ArrayList<>();  // 좋아하는 학생의 번호들
            favorite.add(Integer.parseInt(st.nextToken()));
            favorite.add(Integer.parseInt(st.nextToken()));
            favorite.add(Integer.parseInt(st.nextToken()));
            favorite.add(Integer.parseInt(st.nextToken()));
            favorites.put(student, favorite);

            // 모든 칸이 빈칸이라면 (1, 1)이 조건에 만족하는 칸이므로, 해당 학생을 넣기
            if (hashMap.isEmpty()) {
                room[1][1] = student;
                hashMap.put(student, new Pos(1, 1));
                continue;
            }

            getPosition(student, favorite);
        }

        int total = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int now = room[i][j];
                int cnt = 0;
                List<Integer> favorite = favorites.get(now);

                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= n || !favorite.contains(room[nx][ny])) { // 범위 벗어나거나 기록된 칸이면 pass
                        continue;
                    }
                    cnt++;
                }

                if (cnt == 1) total += 1;
                else if (cnt == 2) total += 10;
                else if (cnt == 3) total += 100;
                else if (cnt == 4) total += 1000;
            }
        }
        System.out.println(total);
    }

    public static void getPosition(int student, List<Integer> favorite) {
        // 우선순위 큐 선언하기
        PriorityQueue<Pos> pq = new PriorityQueue<>((p1, p2) -> {
            if (p1.like == p2.like) { // 인접한 칸 중에서 좋아하는 학생의 수가 같다면
                if (p1.cnt == p2.cnt) { // 인접한 칸의 개수가 같다면
                    // x를 기준으로 오름차순 정렬
                    if (p1.x == p2.x) return p1.y - p2.y; // x가 같다면 y를 기준으로 오름차순 정렬
                    return p1.x - p2.x;
                }
                return p2.cnt - p1.cnt; // 인접한 빈칸의 수를 기준으로 내림차순 정렬
            }
            return p2.like - p1.like; // 인접한 칸에 존재하는 좋아하는 학생의 수를 기준으로 내림차순 정렬
        });

        // 좋아하는 학생의 인접한 빈 칸 구하기
        HashMap<String, Integer> map = new HashMap<>(); // 좌표, 현재 칸 기준 인접한 좋아하는 학생의 수
        for (int fv : favorite) {
            if (hashMap.containsKey(fv)) {
                Pos pos = hashMap.get(fv);
                for (int i= 0; i < 4; i++) {
                    int nx = pos.x + dx[i];
                    int ny = pos.y + dy[i];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= n || room[nx][ny] != 0) { // 범위 벗어나거나 기록된 칸이면 pass
                        continue;
                    }

                    String newPos = nx + "," + ny;
                    // 인접한 칸에 위치하는 좋아하는 학생의 수 구하기
                    if (map.containsKey(newPos)) {
                        map.put(newPos, map.get(newPos) + 1);
                    } else {
                        map.put(newPos, 1);
                    }
                }
            }
        }

        if (map.isEmpty()) { // 좋아하는 학생이 자리를 잡지 않은 경우
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (room[i][j] == 0) map.put(i + "," + j, 0);
                }
            }
        }

        // 인접한 빈 칸 구하기
        for (String pos : map.keySet()) {
            StringTokenizer st = new StringTokenizer(pos, ",");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int like = map.get(pos);
            int cnt = 0;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n || room[nx][ny] != 0) { // 범위 벗어나거나 기록된 칸이면 pass
                    continue;
                }
                cnt++;
            }
            pq.offer(new Pos(x, y, cnt, like));
        }

        if (!pq.isEmpty()) { // 좋아하는 학생이 자리를 잡은 경우
            Pos pos = pq.poll();
            room[pos.x][pos.y] = student;
            hashMap.put(student, new Pos(pos.x, pos.y));
        }
    }

    static class Pos {
        int x, y;
        int cnt; // 비어있는 칸의 수
        int like; // 좋아하는 사람의 수

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
            this.cnt = 0;
            this.like = 0;
        }

        public Pos(int x, int y, int cnt, int like) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.like = like;
        }
    }
}