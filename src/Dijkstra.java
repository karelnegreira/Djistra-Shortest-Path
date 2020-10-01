import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Dijkstra {
    public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
        source.setDistance(0);

        Set<Node> settleNode = new HashSet<>();
        Set<Node> unsettleNode = new HashSet<>();

        unsettleNode.add(source);

        while (unsettleNode.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettleNode);
            unsettleNode.remove(currentNode);
            for (Map.Entry<Node, Integer> adjacencyPair: currentNode.getAdjacentNodes().entrySet()) {
                Node adjacencyNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settleNode.contains(adjacencyNode)) {
                    calculateMinimumDistance(adjacencyNode, edgeWeight, currentNode);
                    unsettleNode.add(adjacencyNode);
                }
            }
            settleNode.add(currentNode);
        }
        return graph;
    }

    private static void calculateMinimumDistance(Node evaluationNode, Integer edgeWeight, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeight < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeight);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    private static Node getLowestDistanceNode(Set<Node> unsettleNode) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node: unsettleNode) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }
}
