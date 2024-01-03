import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = br.readLine();
        LinkedList<Character> list = new LinkedList<>();
        ListIterator<Character> iterator = list.listIterator();
        for (int i = 0; i < str.length(); i++) {
            iterator.add(str.charAt(i));
        }

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] cmd = br.readLine().split(" ");
            switch (cmd[0]) {
                case "L": // 커서를 왼쪽으로 한 칸 이동
                    // 역방향으로 순회할 때, 다음 요소를 가지고 있는 경우
                    if (iterator.hasPrevious()) iterator.previous();
                    break;
                case "D": // 커서를 오른쪽으로 한 칸 이동
                    // 순방향으로 순회할 때, 다음 요소를 가지고 있는 
                    if (iterator.hasNext()) iterator.next();
                    break;
                case "B": // 커서 왼쪽에 있는 문자 삭제
                    if (iterator.hasPrevious()) {
                        iterator.previous();
                        iterator.remove();
                    }
                    break;
                case "P": // $라는 문자 커서 왼쪽에 추가
                    iterator.add(cmd[1].charAt(0));
                    break;
            }
        }

        for (char c : list) {
            bw.write(c);
        }
        bw.flush(); bw.close();
    }
}