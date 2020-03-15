class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> result = new LinkedList<>();
        backtrack(result, num, target, 0, "", 0, 0);
        return result;
    }
    
    private void backtrack(List<String> result, String num, int target, int index, String expression, long value, long prevSignedOperand) {

        if(index == num.length()) {
            if(value == target) {
                result.add(expression);
            }
            return;
        }
        
        // try candidates
        for(int cursor = index; cursor < num.length(); cursor++) {
            if(index != cursor && num.charAt(index) == '0') break;
            // current operand
            long operand = Long.parseLong(num.substring(index, cursor + 1));
            if(index == 0) {
                backtrack(result, num, target, cursor + 1, "" + operand, operand, operand);
            } else {
                // add
                backtrack(result, num, target, cursor + 1, expression + "+" + operand, value + operand, operand);
                // subtract
                backtrack(result, num, target, cursor + 1, expression + "-" + operand, value - operand, -1 * operand);
                // multiply
                backtrack(result, num, target, cursor + 1, expression + "*" + operand, value - prevSignedOperand + operand*prevSignedOperand, operand*prevSignedOperand);
            }
        }

    }
}
