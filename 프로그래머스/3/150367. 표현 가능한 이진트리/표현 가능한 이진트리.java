class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for (int i=0; i<numbers.length; i++) {
            long number = numbers[i];
            // 이진수로 변환
            String binary = Long.toBinaryString(number);
            
            // 포화 이진트리로 만들기 => 앞에 0 붙이기
            // 포화 이진트리 개수 = 2^n - 1
            binary = makeFullBinaryTree(binary);
            
            
            if (isValid(binary, false)) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }
        
        return answer;
    }
    
    public String makeFullBinaryTree(String binary) {
        int len = binary.length(), fullLen = 1;
        
        while (fullLen < len) {
            fullLen = fullLen * 2 + 1;
        }
        
        int need = fullLen - len;
        
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<need; i++) {
            sb.append("0");
        }
        sb.append(binary);
        return sb.toString();
    }
    
    static boolean isValid(String str, boolean isZero) {
        if (str.length() == 0)
            return true;
        
        int mid = str.length() / 2;
        char root = str.charAt(mid);
        if (isZero && root == '1')
            return false;
        
        boolean nextParentZero = (root == '0');
        
        // 왼쪽, 오른쪽 서브트리 검사
        String left = str.substring(0, mid);
        String right = str.substring(mid+1);
        
        return isValid(left, nextParentZero) && 
            isValid(right, nextParentZero);
    }
}