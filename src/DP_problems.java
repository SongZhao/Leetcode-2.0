public class DP_problems{

    //Regular Expression Matching
    //'.' Matches any single character.
    //'*' Matches zero or more of the preceding element.
    //isMatch("aab", "c*a*b") → true

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

}