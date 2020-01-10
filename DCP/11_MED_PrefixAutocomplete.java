import java.util.*;

/*
Problem asked by Twitter

Implement an autocomplete system. Given a query string s & a set of all possible
query strings, return all strings in the set that have s as a prefix

e.g.: s = "de", possible = [dog, deer, deal] --> [deer, deel]

Hint: Try preprocessing the dictionary into a more efficient data structure
to speed up queries.

General Approach:
- Use a Trie to preprocess the input dictionary
- Build an addWord and autocomplete function within the Trie data structure

Steps:
- Define TrieNode class
  - Properties:
    - Character value
    - HashMap<Character, TrieNode> children
    - boolean endWordFlag
  - Constructor sets value to input value and endWordFlag to false, empty children
- Define Trie class
  - TrieNode root is a private variable of the class
  - Create constructor with init root at null character
  - Create addWord function
  - Create prefixExists function?
  - Create autocomplete function based on DFS
- Create autocomplete function based on inputs and outputs
  - Init new Trie
  - Loop through all words and call addWord function of Trie
  - Return the ArrayList result of calling autocomplete function from Trie on input string s


*/

class TrieNode {
  Character value;
  HashMap<Character, TrieNode> children;
  boolean endWord;

  public TrieNode(Character val) {
    this.value = val;
    this.children = new HashMap<>();
    this.endWord = false;
  }
}

class Trie {
  TrieNode root;
  public Trie() {
    this.root = new TrieNode('\u0000');
  }

  public void addWord(String word) {
    //Init pointer to root node to work with
    TrieNode curr = root;
    //Loop through characters of word
    for(Character ch: word.toCharArray()) {
      //if character not children of curr node,
      if(!curr.children.containsKey(ch)) {
        // create new child node with curr char
        // add new node to children of curr node
        curr.children.put(ch, new TrieNode(ch));
      }
      //set curr node to child node
      curr = curr.children.get(ch);
    }
    //set end word flag at current node to true
    curr.endWord = true;
  }

  private TrieNode getPrefixEndNode(String prefix) {
    //Init pointer to root node to work with
    TrieNode curr = root;
    //Loop through characters of word
    for(Character ch: prefix.toCharArray()) {
      //if character not children of curr node,
      if(!curr.children.containsKey(ch)) {
        return null;
      }
      //set curr node to child node
      curr = curr.children.get(ch);
    }
    return curr;
  }



  public List<String> autocomplete(String prefix) {
    TrieNode prefixEnd = getPrefixEndNode(prefix);
    ArrayList<String> result = new ArrayList<>();
    if(prefixEnd != null) {
      //Do DFS from prefix end node, with running string, and result arraylist
      dfs(prefixEnd, prefix, '\u0000', result);
    }
    return result;
  }

  private void dfs(TrieNode curr, String runStr, char currChar, ArrayList<String> result) {
    //Add currChar to runStr
    runStr += currChar;
    //Base case is endWord
    if(curr.endWord) result.add(runStr);
    //Loop dfs through children of curr
    for(TrieNode child: curr.children.values()){
      //dfs on child, runStr, child char, and result
      dfs(child, runStr, child.value, result);
    }
  }
}


class Main {
  public static List<String> autocomplete(String s, List<String> dictionary) {
    Trie trie = new Trie();
    for(String word: dictionary) {
      trie.addWord(word);
    }
    return trie.autocomplete(s);
  }

  public static void main(String[] args) {
    ArrayList<String> dictionary = new ArrayList<String>(Arrays.asList("dog","dear","deal"));
    System.out.println(autocomplete("de", dictionary));
  }
}
