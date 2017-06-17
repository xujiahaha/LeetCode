public class Solution {
    public String addBinary(String a, String b) {
        // corner case
        if(a == null || a.length() == 0) return b;
        if(b == null || b.length() == 0) return a;
        
        // add from right to left
        StringBuilder sb = new StringBuilder();
        int carry = 0, i = a.length()-1, j = b.length()-1;
        while(i >= 0 || j >= 0 || carry > 0) {
            int sum = 0;
            if(i >= 0) {
                sum += a.charAt(i)-'0';
                i--;
            }
            if(j >= 0) {
                sum += b.charAt(j)-'0';
                j--;
            }
            sum += carry;
            sb.append(sum%2);
            carry = sum/2;
        }
        return sb.reverse().toString();
    }
}