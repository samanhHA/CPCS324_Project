
package Algorithm;

/**
 *
 * @author roban
 */
import java.util.*;
public class bipartite {
    // applicantsarray and hospitals arrays as given
    String[] Applicants = {"Ahmed", "Mahmoud", "Eman", "Fatimah", "Kamel", "Nojood"};
    String[] Hospitals = {"King Abdelaziz University", "King Fahd", "East Jeddah", "King Fahad Armed Forces", "King Faisal Specialist", "Ministry of National Guard"};
    // add edges as ajc matrix
    int bipartiteGraph[][] = new int[][]{ //A graph with M applicant and N hospitals
        {1, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 1},
        {1, 0, 0, 1, 0, 0},
        {0, 0, 1, 0, 0, 0},
        {0, 0, 0, 1, 1, 0},
        {0, 0, 0, 0, 0, 1}
    };
    
    LinkedList<Integer> set = new LinkedList<>();

    int M = Applicants.length;
    int N = Hospitals.length;
    int[] applicantsarray = new int[N];
    /**
     * method to check if it is possible for matching 
     * @param u
     * @param visited
     * @param assign
     * @return true if a match is found
     */
    public boolean bipartiteMatch(int u, boolean visited[], int assign[]) {
        for (int v = 0; v < N; v++) {    //for all hospital 0 to N-1
            if (bipartiteGraph[u][v] == 1 && !visited[v]) {//when hospital v is not visited and i is interested
                visited[v] = true;//mark as hospital v is visited
                //when v is not assigned or previously assigned
                if (assign[v] < 0 || bipartiteMatch(assign[v], visited, assign)) {
                    assign[v] = u;//assign hospital v to applicant i
                    System.out.println(Applicants[u] + " assigned to " + Hospitals[v]);
                    set.add(u, v);// add the edge to matching set M
                    applicantsarray[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * maximum matching method
     * @return number of matching found in the graph
     */
    public int maxMatching() {
        int assign[] = new int[N];    //an array to track which jospital is assigned to which applicant
        for (int i = 0; i < N; i++) {
            assign[i] = -1;    //initially set all jobs are available
            set.add(-1);     //initialize the set M 
        }
        int matchCount = 0;

        for (int i = 0; i < M; i++) {    //for all applicantsarray
            boolean visited[] = new boolean[N];
            if (bipartiteMatch(i, visited, assign)){ //when i get a hospital
                System.out.println("Set:");
                for (int j = 0; j < set.size(); j++) {
                    if(set.get(j)>-1)
                    System.out.println(Applicants[j]+" - "+Hospitals[set.get(j)]);
                }
                System.out.println();
                matchCount++;
            }

        }
        
        return matchCount;
    }
    /**
     * main method
     * @param args 
     */
    public static void main(String[] args) {
        bipartite graph = new bipartite();
        System.out.println("Maximum number of applicants that assigned to hospitals: " + graph.maxMatching());
    }
}