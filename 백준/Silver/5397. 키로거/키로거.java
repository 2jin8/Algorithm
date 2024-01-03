import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String key = br.readLine();
            LinkedList<Character> list = new LinkedList<>();
            ListIterator<Character> iterator = list.listIterator();
            for (int j = 0; j < key.length(); j++) {
                switch (key.charAt(j)) {
                    case '<': // 커서 왼쪽으로 이동
                        if (iterator.hasPrevious()) iterator.previous();
                        break;
                    case '>': // 커서 오른쪽으로 이동
                        if (iterator.hasNext()) iterator.next();
                        break;
                    case '-': // 앞에 글씨 지우기
                        if (iterator.hasPrevious()) {
                            iterator.previous();
                            iterator.remove();
                        }
                        break;
                    default:
                        iterator.add(key.charAt(j));
                        break;
                }
            }
            for (char c : list) {
                bw.write(c);
            }
            bw.write("\n");
        }
        br.close();
        bw.flush(); bw.close();
    }
}