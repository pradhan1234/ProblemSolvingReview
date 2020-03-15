class Solution { 
    private int cursor = 0;
    public String decodeString(String s) {
        
        StringBuilder result = new StringBuilder();
        int k = 0;
        
        while(cursor < s.length()) {
            char token = s.charAt(cursor);
            
            if(token >= '0' && token <= '9') {
                cursor++;
                k = k * 10 + (token - '0');
            } else if(token == '[') {
                cursor++;
                String nextResult = decodeString(s);
                while(k > 0) {
                    result.append(nextResult);
                    k--;
                }
            } else if (token == ']') {
                cursor++;
                return result.toString();
            } else { 
                cursor++;
                result.append(token);
            }
            
        }
        
        return result.toString();
        
    }    
}
