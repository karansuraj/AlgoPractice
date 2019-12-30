import java.util.*;

class Main {
  public static void main(String[] args) {
    String[] words = {"chair", "racket", "touch", "height", "tunic"};
    System.out.println(circleWords(words));
    String[] words2 = {"chair", "racket", "tight","tunic","touch", "height"};
    System.out.println(circleWords(words2));
    String[] words3 = {"tight"};
    System.out.println(circleWords(words3));

  }

  //Global vars
  private static HashMap<String, ArrayList<String>> graph;
  private static HashSet<String> seen;
  private static String root;

  public static boolean circleWords(String[] words) {
    //PROBLEM: Determine if list of words forms a circle
    //  (last char of one word is first of another)
    if(words.length == 0) return false;
    //Init graph and seen set
    graph = new HashMap<>();
    seen = new HashSet<>();
    //Create graph
    createGraph(words);
    root = words[0];
    //return call on recursive function
    return recurse(0, root, "");
  }

  public static void createGraph(String[] words) {
    for(String word: words) {
      if(!graph.containsKey(word)) {
        ArrayList<String> edges = new ArrayList<String>();
        if(word.length() > 0) {
          for(String otherWord: words) {
            //As long as otherWord length is > 0, check if last char of word is first of otherWord
            //Add to list if so
            if(otherWord.length() > 0 && word.charAt(word.length()-1) == otherWord.charAt(0))
              edges.add(otherWord);
          }
        }
        graph.put(word, edges);
      }
    }
  }

  public static boolean recurse(int count, String currWord, String path) {
    //Incr count
    count++;
    //Add word to seen
    seen.add(currWord);
    if(!path.equals("")) path += " -> ";
    path += currWord;
    // System.out.println(path);
    boolean res = false;
    //Get edges
    ArrayList<String> edges = graph.get(currWord);
    //Loop edges
    for(String edgeWord: edges) {
      //Check if count is size of graph
      if(count == graph.size()) {
        //Don't recurse on edges, but just check if root, and return true if so
        if(edgeWord.equals(root)) {
          res |= true;
          // System.out.println(path + " => All "+graph.size()+" elements reached in cycle!");
        }
      }
      //else traverse if not seen (res != each recurse on edge)
      else {
        if(!seen.contains(edgeWord)) {
          res |= recurse(count, edgeWord, path);
        }
      }
    }

    seen.remove(currWord);
    return res;
  }
}
