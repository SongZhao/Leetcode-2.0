public class DP_problems{

    //when it comes to DP, the first thing for us to figure out is the format of the sub problem
    //(or the state of each sub problem). The format of the sub problem can be helpful when we are 
    //trying to come up with the recursive relation.
    //find a way to divided the original problem into the sub problems and use the solutions of the 
    //sub problems to somehow create the solution of the original one.



    //Regular Expression Matching
    //'.' Matches any single character.
    //'*' Matches zero or more of the preceding element.
    //isMatch("aab", "c*a*b") → true
    //recursion approach
    public boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() && 
                               (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatch(text, pattern.substring(2)) || 
                    (first_match && isMatch(text.substring(1), pattern)));
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }
    //dp approach
    //ref: https://discuss.leetcode.com/topic/40371/easy-dp-java-solution-with-detailed-explanation/2
    public boolean isMatch(String s, String p) {

        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i-1]) {
                dp[0][i+1] = true;          // can cancel stuff like 'a*'
            }
        }
        for (int i = 0 ; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '.') {
                    dp[i+1][j+1] = dp[i][j];
                }
                if (p.charAt(j) == s.charAt(i)) {
                    dp[i+1][j+1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
                        dp[i+1][j+1] = dp[i+1][j-1];
                    } else {
                        dp[i+1][j+1] = (dp[i][j+1] || dp[i+1][j-1]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }


    //Wildcard Matching
    //'?' Matches any single character.
    //'*' Matches any sequence of characters (including the empty sequence).
    public boolean isMatch(String s, String p) {
        int i = 0;
        int j = 0;
        int ikeeper = 0;
        int jkeeper = -1;
        while(i < s.length())
        {
            if(j < p.length() && (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j)))
            {
                i++;    //if j is valid and p[j] is '?'' or s[i] = p[j], increment both index;
                j++;
            }
            else if(j < p.length() && p.charAt(j) == '*')
            {
                ikeeper = i;    //record both current i and j 
                jkeeper = j;    
                j++;            //increment j
            }
            else if(jkeeper != -1) //if jkeeper isnt 0, which means we had a '*'
            {
                j = jkeeper + 1; //the reason we want to record '*' location is 
                ikeeper++;       //because we wanna roll back, if the first clause 
                i = ikeeper;     //doesnt work out. so basiclly its a backtrack,                                      
                                 //which we treat '*' as empty seq and if not treat
            }                    //it as the char locate in ikeeper, or loop this clause till it finds next s[i] that matches p[j].
            else
                return false;   //if j > p.length&&we didnt see any * in p
        }
        while(j < p.length() && p.charAt(j) == '*') //we might have more left in p when we loop over s. if those left chars are all '*' then its a match. otherwise return false.
            j++;
        return j == p.length();
    }



    //Longest Valid Parentheses
    //stack approach
    public int longestValidParentheses(String s) {
        if(s.length() == 0 || s == null)
            return 0;
        char[] inc = s.toCharArray();
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        int count = 0;
        for(int i =0; i < inc.length; i++)
        {
            if(s.charAt(i) == '(')
                stack.push(i);
            else
            {
                if(!stack.isEmpty() && s.charAt(stack.peek()) == '(')
                {
                    stack.pop();
                    count = stack.isEmpty()? i + 1 : i - stack.peek();
                    max = Math.max(count, max);
                }
                else 
                    stack.push(i);                  //also need to push ')' because we want substring.
            }
        }
        return max;
    }
    //dp approach
    public int longestValidParentheses(String s) {
        int maxans = 0;
        int dp[] = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }
    //left-right approach, which only use 1 space
    public int longestValidParentheses(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right >= left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxlength;
    }

}