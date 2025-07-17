class Solution {
    public String solution(String s) {
        String[] str = s.split("");
        
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<s.length(); i++) {
            char c = str[i].charAt(0);
            // 공백 or 숫자인 경우
            if (Character.isDigit(c) || c == ' ') {
                sb.append(str[i]);
                continue;
            }
            
            if (i == 0 || str[i-1].equals(" ")) { // 첫 문자인 경우
                sb.append(str[i].toUpperCase());
            } else {
                sb.append(str[i].toLowerCase());
            }
        }
        return sb.toString();
    }
}