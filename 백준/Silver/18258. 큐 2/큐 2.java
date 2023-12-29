import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            switch (cmd) {
                case "push":
                    q.offerLast(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    if (q.isEmpty()) bw.write("-1\n");
                    else bw.write(q.removeFirst() + "\n");
                    break;
                case "size":
                    bw.write(q.size() + "\n");
                    break;
                case "empty":
                    if (q.isEmpty()) bw.write("1\n");
                    else bw.write("0\n");
                    break;
                case "front":
                    if (q.isEmpty()) bw.write("-1\n");
                    else bw.write(q.getFirst() + "\n");
                    break;
                case "back":
                    if (q.isEmpty()) bw.write("-1\n");
                    else bw.write(q.getLast() + "\n");
                    break;
            }
        }
        br.close();
        bw.flush(); bw.close();
    }
}