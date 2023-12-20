import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        ArrayList<Integer> queue = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command) {
                case "push":
                    queue.add(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    if (queue.isEmpty()) bw.write("-1\n");
                    else bw.write(queue.remove(0) + "\n");
                    break;
                case "size":
                    bw.write(queue.size() + "\n");
                    break;
                case "empty":
                    if (queue.isEmpty()) bw.write("1\n");
                    else bw.write("0\n");
                    break;
                case "front":
                    if (queue.isEmpty()) bw.write("-1\n");
                    else bw.write(queue.get(0) + "\n");
                    break;
                case "back":
                    if (queue.isEmpty()) bw.write("-1\n");
                    else bw.write(queue.get(queue.size() - 1) + "\n");
                    break;
            }
        }
        bw.flush(); bw.close();
    }
}