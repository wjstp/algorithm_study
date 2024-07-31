import java.util.*;

class Solution {
    
    static List<Node> nodes;
    static List<Integer> preOrder;
    static List<Integer> postOrder;
    static int[] visited;
    public static class Node implements Comparable<Node>{
        int number;
        int x;
        int y;

        public Node(int number, int x, int y) {
            this.number = number;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            if (o.y == this.y) {
                return this.x - o.x;
            }
            return o.y - this.y;    // 내림차순 정렬
        }
    }
    
    public static List<List<Integer>> solution(int[][] nodeinfo) {
        List<List<Integer>> answer = new ArrayList<>();
        // 정렬
        nodes = new ArrayList<>();
        for (int i = 0; i < nodeinfo.length ; i ++) {
            nodes.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
        }
        Collections.sort(nodes);

        // 전위순회
        preOrder = new ArrayList<>();
        visited = new int[nodes.size() + 1];
        visited[nodes.get(0).number - 1] = 1;
        getPreOrder(nodes.get(0), 100001, -1);

        // 후위순회
        Arrays.fill(visited, 0);
        postOrder = new ArrayList<>();
        getPostOrder(nodes.get(0), 100001, -1);
        for (var p : postOrder) {
            System.out.println(p);
        }
        
        answer.add(preOrder);
        answer.add(postOrder);
        return answer;
    }
    
    public static void getPreOrder(Node cur, int max, int min) {
        preOrder.add(cur.number);
        for (int i = 0; i < nodes.size(); i ++) {
            Node next = nodes.get(i);
            if (cur.y > next.y && visited[next.number - 1] == 0) {
                if (next.x < cur.x && min < next.x) {
                    getPreOrder(next, cur.x, min);
                    visited[next.number - 1] = 1;
                }
                else if (next.x > cur.x && next.x < max) {
                    getPreOrder(next, max, cur.x);
                    visited[next.number - 1] = 1;

                }
            }
        }
    }
    public static void getPostOrder(Node cur, int max, int min) {
        for (int i = 0; i < nodes.size(); i ++) {
            Node next = nodes.get(i);
            if (cur.y > next.y && visited[next.number - 1] == 0) {
                if (next.x < cur.x && min < next.x) {
                    getPostOrder(next, cur.x, min);
                    visited[next.number - 1] = 1;
                }
                else if (next.x > cur.x && next.x < max) {
                    getPostOrder(next, max, cur.x);
                    visited[next.number - 1] = 1;

                }
            }
        }
        postOrder.add(cur.number);
    }
}