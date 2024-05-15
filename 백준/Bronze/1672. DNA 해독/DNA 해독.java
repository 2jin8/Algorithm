import java.io.*;
import java.util.*;

public class Main {
    static char[][] changeArr = {
            {'A', 'C', 'A', 'G'},
            {'C', 'G', 'T', 'A'},
            {'A', 'T', 'C', 'G'},
            {'G', 'A', 'G', 'T'}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            stack.push(str.charAt(i));
        }

        while (stack.size() > 1) {
            int j = StringToInt(stack.pop());
            int i = StringToInt(stack.pop());
            stack.push(changeArr[i][j]);
        }

        System.out.println(stack.pop());
    }

    static int StringToInt(char str) {
        int result = -1;
        switch (str) {
            case 'A':
                result = 0;
                break;
            case 'G':
                result = 1;
                break;
            case 'C':
                result = 2;
                break;
            case 'T':
                result = 3;
                break;
        }
        return result;
    }
}