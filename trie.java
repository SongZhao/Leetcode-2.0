
public class trie {
	class TrieNode 
	{

	    TrieNode[] children = new TrieNode[26];
	    boolean isLeaf;
	    // Constructor
	    public TrieNode() 
	    {
	    }
	}

	public class Trie 
	{

	    private TrieNode root;

	    public Trie() 
	    {
	        root = new TrieNode();
	    }

	    // Inserts a word into the trie.
	    public void insert(String word) 
	    {
	        insert(word.toCharArray(), 0, root);
	    }

	    public void insert(char[] word, int i, TrieNode node) 
	    {

	        if (i == word.length) 
	        {
	            node.isLeaf = true;
	            return;
	        }

	        char c = word[i];
	        if (node.children[c - 'a'] == null) 
	        {
	            node.children[c - 'a'] = new TrieNode();
	        }

	        insert(word, i + 1, node.children[c - 'a']);
	    }

	    // Returns if the word is in the trie.
	    public boolean search(String word) 
	    {
	        return search(word.toCharArray(), 0, root);
	    }

	    public boolean search(char[] word, int i, TrieNode node) 
	    {

	        if (i == word.length) 
	        {
	            return node.isLeaf;
	        }

	        char c = word[i];

	        return node.children[c - 'a'] != null && search(word, i + 1, node.children[c - 'a']);
	    }

	    // Returns if there is any word in the trie
	    // that starts with the given prefix.
	    public boolean startsWith(String prefix) 
	    {
	        return startsWith(prefix.toCharArray(), 0, root);
	    }

	    public boolean startsWith(char[] word, int i, TrieNode node) 
	    {

	        if (i == word.length) 
	        {
	            return true;
	        }

	        char c = word[i];
	        return node.children[c - 'a'] != null && startsWith(word, i + 1, node.children[c - 'a']);
	    }
	}
	
	 /***
    Design a data structure that supports the following two operations:

    void addWord(word)
    bool search(word)
    search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

    For example:

    addWord("bad")
    addWord("dad")
    addWord("mad")
    search("pad") -> false
    search("bad") -> true
    search(".ad") -> true
    search("b..") -> true
    Note:
    You may assume that all words are consist of lowercase letters a-z.
	***/
	
	class TrieNode{
	    String value;
	    HashMap<Character, TrieNode> children = null;
	    Boolean flag;
	
	    public TrieNode(){
	        value = null;
	        children = new HashMap<>();
	        flag = Boolean.FALSE;
	    }
	
	    public TrieNode(String value){
	        this.value = value;
	        children = new HashMap<>();
	        flag = Boolean.FALSE;
	    }
	
	    public void add(Character c){
	        String val = "";
	        if(this.value == null){ //root node
	            val = String.valueOf(c);
	        }else{
	            val = this.value + String.valueOf(c);
	        }
	        this.children.put(c, new TrieNode(val));
	    }
	
	}
	
	class Trie{
	    TrieNode root = null;
	
	    public Trie(){
	        root = new TrieNode();
	    }
	
	    public void addWord(String word){
	        TrieNode node = root;
	        for(Character c: word.toCharArray()){
	            if(!node.children.containsKey(c)){
	                node.add(c);
	            }
	            node = node.children.get(c);
	        }
	        node.flag = true;
	    }
	
	    public TrieNode getRoot(){
	        return root;
	    }
	
	}
	
	
	public class WordDictionary {
	
	    Trie trie = new Trie();
	    // Adds a word into the data structure.
	    public void addWord(String word) {
	        trie.addWord(word);
	    }
	
	    public boolean searchPattern(TrieNode node, String pattern, int counter){
	        if(node == null) return false;    
	        if(counter == pattern.length()) return node.flag;
	
	        Character c = pattern.charAt(counter);
	        if(c == '.'){
	            for(Character ch: node.children.keySet()){
	                if(searchPattern(node.children.get(ch), pattern, counter+1)) return true;
	            }
	        }else if(c != '.' && node.children.containsKey(c)) {
	            return searchPattern(node.children.get(c), pattern, counter+1);
	        }
	
	        return false;
	    }
	
	    // Returns if the word is in the data structure. A word could
	    // contain the dot character '.' to represent any one letter.
	    public boolean search(String word) {
	        return searchPattern(trie.getRoot(), word, 0);
	    }
	}
	
	// Your WordDictionary object will be instantiated and called as such:
	// WordDictionary wordDictionary = new WordDictionary();
	// wordDictionary.addWord("word");
	// wordDictionary.search("pattern");
	
	
	
	//Word Search II
	 public void dfs(Trie trie, Set<String> set, int i, int j, char[][] board, String word, boolean[][] visited){
	        if(i<0 || j<0 || i>board.length-1 || j> board[0].length-1 || visited[i][j]) return;

	        //get the current stream of the word
	        word += board[i][j];

	        //for a particular path we take in the graph, we make sure we dont revisit them in the following recurrences
	        visited[i][j] = true;

	        //search the trie for the word
	        SearchResult result = trie.search(word);

	        //if considering the word as a prefix fails, then there is no use to proceed, so we terminate
	        if(result == SearchResult.NOT_FOUND){ 
	            //reset visited to false
	            visited[i][j] = false;
	            return;
	        }//if the word itself was found, then add it to the set
	        else if(result == SearchResult.WORD_FOUND) set.add(word);

	        //now perform DFS
	        dfs(trie, set, i-1, j, board, word, visited);
	        dfs(trie, set, i+1, j, board, word, visited);
	        dfs(trie, set, i, j-1, board, word, visited);
	        dfs(trie, set, i, j+1, board, word, visited);                

	        //reset visited to false
	        visited[i][j] = false;
	    }

	    public List<String> findWords(char[][] board, String[] words) {
	        if(board.length == 0 || words.length == 0 || board[0].length == 0) return new ArrayList<String>();
	        int rows = board.length;
	        int cols = board[0].length;
	        boolean[][] visited = new boolean[rows][cols];
	        Set<String> set = new HashSet<>();

	        //create a trie with all of the input words   
	        Trie trie = new Trie();
	        trie.addWords(words);

	        //perform DFS from every point in the board
	        for(int i=0;i<rows;i++){
	            for(int j=0;j<cols;j++){
	                dfs(trie, set, i, j, board, "", visited);    
	            }
	        }        

	        //return the list
	        return new ArrayList<String>(set);
	    }
}
