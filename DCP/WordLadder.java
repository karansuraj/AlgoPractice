import java.util.*;


class Main {
  public static void main(String[] args) {
    String[] words = {"hot","dot","dog","lot","log","cog"};
    System.out.println(wordLadder(words, "hit", "cog"));
  }

  public static int wordLadder(String[] words, String start, String end) {
    //Build graph based on relationship between the end char of each word
    // matching the start char of every other word
    ArrayList<String> wordLst = new ArrayList<>();
    for(String word: words) wordLst.add(word);
    wordLst.add(start);

    //Build graph as map of word string to list of edge word strings
    HashMap<String, ArrayList<String>> graph = buildGraph(wordLst);
    System.out.println(graph);

    //Do BFS and increment count as we go through levels of graph
    int count = 1;
    Queue<String> q = new LinkedList<>();
    HashSet<String> seen = new HashSet<>();
    q.add(start);
    seen.add(start);
    String curr;
    ArrayList<String> edges;
    HashMap<String, Integer>  counts = new HashMap<>();
    for(String word: graph.keySet()) counts.put(word, 0);

    while(q.size() > 0) {
      curr = q.remove();
      count++;
      if(counts.get(curr) <= count) counts.put(curr, count);
      System.out.println(curr);
      // if(curr.equals(end)) return count;

      //Go through edges and add words to queue
      edges = graph.get(curr);
      System.out.println(edges);
      for(String edge: edges) {
        if(!seen.contains(edge)) {
          q.add(edge);
          seen.add(edge);
        }
      }
    }

    System.out.println(counts);
    return count;
  }

  public static HashMap<String, ArrayList<String>> buildGraph(ArrayList<String> words) {
    HashMap<String, ArrayList<String>> graph = new HashMap<>();

    ArrayList<String> currEdges;
    //For every word
    for(String word: words) {
      if(!graph.containsKey(word)) {
        graph.put(word, new ArrayList<String>());
      }
      currEdges = graph.get(word);
      //Iterate through every other word
      for(String other: words) {
        if(!word.equals(other) && diffByOne(word, other)) {
          currEdges.add(other);
        }
      }
    }
    return graph;
  }

  public static boolean diffByOne(String word1, String word2) {
    //Guaranteed same length, so just check when characters differ by more than one letter
    int count = 0;
    for(int i=0; i<word1.length(); i++) {
      if(word1.charAt(i) != word2.charAt(i)) count++;
    }
    return count == 1;
  }
}
