import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = scan.nextLine();
        }

        int total = 0;
        for (int i = 0; i < n; i++) {
            String word = words[i];
            if (word.length() % 2 == 1) continue;

            Stack<Character> wordStack = new Stack<>();
            for (char c : word.toCharArray()) {
                wordStack.push(c);
            }

            Stack<Character> ansStack = new Stack<>();
            while (!wordStack.isEmpty()) {
                ansStack.push(wordStack.pop());

                int size = ansStack.size();
                if (size > 1) {
                    char w1 = ansStack.elementAt(size - 1);
                    char w2 = ansStack.elementAt(size - 2);
                    if (w1 == w2) {
                        ansStack.pop();
                        ansStack.pop();
                    }
                }
            }
            if (ansStack.isEmpty()) total++;
        }
        System.out.println(total);
    }
}