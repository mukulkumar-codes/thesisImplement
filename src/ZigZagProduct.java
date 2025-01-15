import java.util.Scanner;

public class ZigZagProduct {
    public static void main(String[] args) {

        int nodes, degree;
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of the nodes of the regular graph: ");
        nodes = input.nextInt();
        System.out.print("Enter the degree of the regular graph: ");
        degree = input.nextInt();


//        Graph adjacency matrix with 0 and 1 entries for non-edge and edges between nodes respectively
        int[][] graph = new int[nodes][nodes];
        System.out.println("Enter the adjacency matrix of the graph : ");
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                graph[i][j] = input.nextInt();
            }
        }
        System.out.println();


//        complete graph to generate the cloud graphs with number of nodes equal to the degree of the original graph (cloud can provide manually)
        int[][] cloud = new int[degree][degree];
        for (int i = 0; i < degree; i++) {
            for (int j = 0; j < degree; j++) {
                if (i != j){
                    cloud[i][j] = 1;
                }
            }
        }


//        replacement of cloud with the actual graph nodes and cloud nodes connection between another cloud nodes with corresponding graphs connections without cloud internal connections
        int dimension = nodes * degree;
        int[][] cloudMap = new int[dimension][dimension];
        int[] rowSum = new int[dimension];
        int[] colSum = new int[dimension];

        for (int i = 0; i < nodes; i++) {
            for (int j = i; j < nodes; j++) {
                if (graph[i][j] == 1){
                    for (int k = 0; k < degree; k++) {
                        if (rowSum[i * degree + k] == 0){
                            for (int l = 0; l < degree; l++) {
                                if (colSum[j * degree + l] == 0){
                                    cloudMap[i * degree + k][j * degree + l] = 1;
                                    cloudMap[j* degree + l][i * degree + k] = 1;
                                    rowSum[i * degree + k] = 1;
                                    colSum[i * degree + k] = 1;
                                    rowSum[j * degree + l] = 1;
                                    colSum[j * degree + l] = 1;
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }


//        Edges connection between the nodes after placing the cloud in the original graph with the help of the cloudMap matrix
        int[][] zigZagProduct = new int[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            int primaryCloud = i / degree;
            int primaryCloudNode = i % degree;
            for (int j = 0; j < degree; j++) {
                if (cloud[primaryCloudNode][j] == 1) {
                    for (int k = 0; k < dimension; k++) {
                        if (cloudMap[primaryCloud * degree + j][k] == 1) {
                            int secondaryCloud = k / degree;
                            int secondaryCloudNode = k % degree;

                            for (int l = 0; l < degree; l++) {
                                if (cloud[k % degree][l] == 1) {
                                    zigZagProduct[i][secondaryCloud * degree + l] = 1;
                                    zigZagProduct[secondaryCloud * degree + l][i] = 1;
                                }
                            }
                        }
                    }
                }
            }
        }


//        This is the output of the actual zigZag product of two graphs
        System.out.println("ZigZag product of the cloud and given graph: ");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                System.out.print(zigZagProduct[j][i] + " ");
            }
            System.out.println();
        }

    }
}