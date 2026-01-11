class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        
        for (int i=0; i<s.length; i++) {
            String str = s[i];
            
            StringBuilder sb = new StringBuilder();
            // "110" 모두 없애기
            int count = 0; // "110"의 개수
            for (char c : str.toCharArray()) {
                sb.append(c);
                
                int len = sb.length();
                if (len >= 3 && sb.charAt(len-3) == '1' && 
                    sb.charAt(len-2) == '1' && sb.charAt(len-1) == '0') {
                    count++;
                    sb.setLength(len-3); // "110" 자르기
                }
            }
            
            // 사전순으로 가장 먼저 나오려면 0 뒤에 넣어야 함
            // 가장 마지막 0 뒤에 넣기
            int index = sb.lastIndexOf("0");
            sb.insert(index+1, "110".repeat(count));
            
            answer[i] = sb.toString();
        }
        
        return answer;
    }
}