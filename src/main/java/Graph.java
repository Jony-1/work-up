import java.util.*;

public class Graph {
    private Map<String, List<Edge>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    // Método para agregar un nodo al grafo
    public void addNode(String nodeId) {
        adjacencyList.putIfAbsent(nodeId, new ArrayList<>());
    }

    public String getShortestPathMessage(String start, String end) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        Set<String> visited = new HashSet<>();

        // Inicializar distancias
        for (String vertex : adjacencyList.keySet()) {
            distances.put(vertex, vertex.equals(start) ? 0 : Integer.MAX_VALUE);
            priorityQueue.add(vertex);
        }

        while (!priorityQueue.isEmpty()) {
            String current = priorityQueue.poll();
            if (current.equals(end)) {
                List<String> shortestPath = new ArrayList<>();
                while (previous.containsKey(current)) {
                    shortestPath.add(current);
                    current = previous.get(current);
                }
                shortestPath.add(start);
                Collections.reverse(shortestPath);

                // Crear el mensaje del camino más corto
                StringBuilder message = new StringBuilder("Camino más corto desde ");
                message.append(start).append(" hasta ").append(end).append(": ");
                for (String node : shortestPath) {
                    message.append(node).append(" -> ");
                }
                message.delete(message.length() - 4, message.length()); // Eliminar el último " -> "
                return message.toString();
            }

            if (distances.get(current) == Integer.MAX_VALUE) {
                break; // No hay camino hacia el destino
            }

            for (Edge neighbor : getNeighbors(current)) {
                int newDistance = distances.get(current) + neighbor.getWeight();
                if (newDistance < distances.get(neighbor.getDestination())) {
                    distances.put(neighbor.getDestination(), newDistance);
                    previous.put(neighbor.getDestination(), current);
                    priorityQueue.add(neighbor.getDestination());
                }
            }

            visited.add(current);
        }

        return "No se encontró un camino desde " + start + " hasta " + end;
    }


    // Método para agregar una arista (conexión) entre dos nodos con un peso dado
    public void addEdge(String sourceNodeId, String destinationNodeId, int weight) {
        // Verificar si los nodos de origen y destino existen en el grafo
        if (!adjacencyList.containsKey(sourceNodeId)) {
            addNode(sourceNodeId);
        }
        if (!adjacencyList.containsKey(destinationNodeId)) {
            addNode(destinationNodeId);
        }

        // Agregar la arista entre los nodos de origen y destino
        adjacencyList.get(sourceNodeId).add(new Edge(destinationNodeId, weight));
        adjacencyList.get(destinationNodeId).add(new Edge(sourceNodeId, weight)); // Si el grafo es no dirigido
    }


    // Método para obtener los vecinos (nodos adyacentes) de un nodo dado
    public List<Edge> getNeighbors(String nodeId) {
        return adjacencyList.getOrDefault(nodeId, Collections.emptyList());
    }

    // Método para encontrar el camino más corto entre dos nodos usando el algoritmo de Dijkstra
    public List<String> getShortestPath(String start, String end) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        Set<String> visited = new HashSet<>();

        // Inicializar distancias
        for (String vertex : adjacencyList.keySet()) {
            distances.put(vertex, vertex.equals(start) ? 0 : Integer.MAX_VALUE);
            priorityQueue.add(vertex);
        }

        while (!priorityQueue.isEmpty()) {
            String current = priorityQueue.poll();
            if (current.equals(end)) {
                List<String> shortestPath = new ArrayList<>();
                while (previous.containsKey(current)) {
                    shortestPath.add(current);
                    current = previous.get(current);
                }
                shortestPath.add(start);
                Collections.reverse(shortestPath);
                return shortestPath;
            }

            if (distances.get(current) == Integer.MAX_VALUE) {
                break; // No hay camino hacia el destino
            }

            for (Edge neighbor : getNeighbors(current)) {
                int newDistance = distances.get(current) + neighbor.getWeight();
                if (newDistance < distances.get(neighbor.getDestination())) {
                    distances.put(neighbor.getDestination(), newDistance);
                    previous.put(neighbor.getDestination(), current);
                    priorityQueue.add(neighbor.getDestination());
                }
            }

            visited.add(current);
        }

        return Collections.emptyList(); // No se encontró camino hacia el destino
    }

    // Clase interna para representar una arista
    static class Edge {
        private String destination;
        private int weight;

        public Edge(String destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        public String getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }
    }
}
