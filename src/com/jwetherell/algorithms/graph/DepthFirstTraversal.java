package com.jwetherell.algorithms.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.jwetherell.algorithms.data_structures.Graph;
import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;

/* Depth First Travesal in given "Directed graph" (Adjacany matrix) */
public class DepthFirstTraversal {

    @SuppressWarnings("unchecked")
    public static final <T extends Comparable<T>> Graph.Vertex<T>[] depthFirstTraversal(Graph<T> graph, Graph.Vertex<T> source) {
        // use for look-up via index
        final ArrayList<Vertex<T>> vertices = new ArrayList<Vertex<T>>();
        vertices.addAll(graph.getVertices());

        // used for look-up via vertex
        final int n = vertices.size();
        final Map<Vertex<T>,Integer> vertexToIndex = new HashMap<Vertex<T>,Integer>();
        for (int i=0; i<n; i++) {
            final Vertex<T> v = vertices.get(i);
            vertexToIndex.put(v,i);
        }

        // adjacency matrix
        final byte[][] adj = new byte[n][n];
        for (int i=0; i<n; i++) {
            final Vertex<T> v = vertices.get(i);
            final int idx = vertexToIndex.get(v);
            final byte[] array = new byte[n];
            adj[idx] = array;
            final List<Edge<T>> edges = v.getEdges();
            for (Edge<T> e : edges)
                array[vertexToIndex.get(e.getToVertex())] = 1;
        }

        // visited array
        final byte[] visited = new byte[n];
        for (int i = 0; i < visited.length; i++)
            visited[i] = -1;

        // for holding results
        final Graph.Vertex<T>[] arr = new Graph.Vertex[n];

        // start at the source
        Vertex<T> element = source;
        int c = 0;
        int i = vertexToIndex.get(element);
        visited[i] = 1;   
        int k = 0;
        arr[k] = element;
        k++;

        final Stack<Vertex<T>> stack = new Stack<Vertex<T>>();
        stack.push(source);
        while (!stack.isEmpty()) {    
            element = stack.peek();
            c = vertexToIndex.get(element);
            i = 0;    
            while (i < n) {
                if (adj[c][i] == 1 && visited[i] == -1) {
                    stack.push(element);
                    visited[i] = 1;
                    element = vertices.get(i);
                    arr[k] = element;
                    k++;
                    c = vertexToIndex.get(element);
                    i = 0;
                    continue;
                }
                i++;
            }
            stack.pop();    
        }
        return arr;
    }
}
