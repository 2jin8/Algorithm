import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> deque = new ArrayList<>();


        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            int push;
            switch (s) {
                case "push_front":
                    push = Integer.parseInt(st.nextToken());
                    deque.add(0, push);
                    break;
                case "push_back":
                    push = Integer.parseInt(st.nextToken());
                    deque.add(push);
                    break;
                case "pop_front":
                    bw.write((deque.isEmpty() ? -1 : deque.remove(0)) + "\n");
                    break;
                case "pop_back":
                    bw.write((deque.isEmpty() ? -1 : deque.remove(deque.size() - 1)) + "\n");
                    break;
                case "size":
                    bw.write(deque.size() + "\n");
                    break;
                case "empty":
                    bw.write((deque.isEmpty() ? 1 : 0) + "\n");
                    break;
                case "front":
                    bw.write((deque.isEmpty() ? -1 : deque.get(0)) + "\n");
                    break;
                case "back":
                    bw.write((deque.isEmpty() ? -1 : deque.get(deque.size() - 1)) + "\n");
                    break;
            }
        }
        bw.flush(); bw.close();
        br.close();
    }
}