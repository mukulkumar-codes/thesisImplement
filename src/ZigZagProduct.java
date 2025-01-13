import java.util.Scanner;

public class ZigZagProduct {
    public static void main(String[] args) {

        int nodes, degree;
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of the nodes of the regular graph: ");
        nodes = input.nextInt();
        System.out.print("Enter the degree of the regular graph: ");
        degree = input.nextInt();


//        Graph adjacency matrix with 0 and 1 entries for non-edge and edges respectively
        int[][] graph = new int[nodes][nodes];
        System.out.println("Enter the adjacency matrix of the graph : ");
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                graph[i][j] = input.nextInt();
            }
        }
        System.out.println();


//        Cycle graph to generate the cloud graphs with number of nodes equal to the degree of the actual graph
        int[][] cloud = new int[degree][degree];
        for (int i = 0; i < degree; i++) {
            cloud[i][(i + 1) % degree] = 1;
            cloud[(i + 1) % degree][i] = 1;
        }


//        replacement of cloud with the actual graph nodes and cloud nodes connection between another cloud nodes with corresponding graphs connections without cloud internal connections
        int dimension = nodes * degree;
        int[][] cloudMap = new int[dimension][dimension];
        int[] rowSum = new int[dimension];
        int[] colSum = new int[dimension];

        for (int i = 0; i < nodes; i++) {
            for (int j = i; j < nodes; j++) {
                if (graph[i][j] == 1) {
                    for (int k = 0; k < degree; k++) {
                        if (rowSum[i * degree + k] != 1) {
                            if (colSum[j * degree + k] == 1) {
                                for (int l = degree - 1; l >= 0; l--) {
                                    if (colSum[j * degree + l] != 1) {
                                        cloudMap[i * degree + k][j * degree + l] = 1;
                                        cloudMap[j * degree + l][i * degree + k] = 1;
                                        rowSum[i * degree + k] = 1;
                                        rowSum[j * degree + k] = 1;
                                        colSum[i * degree + k] = 1;
                                        colSum[j * degree + k] = 1;
                                        break;
                                    }
                                }
                                break;
                            }
                            cloudMap[i * degree + k][j * degree + k] = 1;
                            cloudMap[j * degree + k][i * degree + k] = 1;
                            rowSum[i * degree + k] = 1;
                            rowSum[j * degree + k] = 1;
                            colSum[i * degree + k] = 1;
                            colSum[j * degree + k] = 1;
                            break;
                        }
                    }
                }
            }
        }

//        Cloud internal connection to form the complete graph with cloud instead of nodes in actual graph.
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < degree; j++) {
                for (int k = 0; k < degree; k++) {
                    cloudMap[i*degree + j][i*degree + k] = cloud[j][k];
                }
            }
        }

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                System.out.print(cloudMap[i][j] + " ");
            }
            System.out.println();
        }


    }
}
