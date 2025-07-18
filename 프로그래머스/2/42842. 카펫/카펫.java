class Solution {
    public int[] solution(int brown, int yellow) {
        int w = 1;
        for (int i=1;i<5000;i++) {
            int a = i * i;
            int b = ((brown + 4) / 2)  * i;
            int c = brown + yellow;
            
            if (a - b + c == 0) {
                w = i;
                break;
            }
        }
        int h = brown / 2 + 2 - w;
        
        return new int[] {Math.max(w, h), Math.min(w, h)};
    }
}