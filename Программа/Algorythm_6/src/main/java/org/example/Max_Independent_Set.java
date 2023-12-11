package org.example;

import java.util.*;

class Tree {
    private static final Random random = new Random();

    static List<Map<Node, List<Node>>> filling_tree(int levels) {
        List<Map<Node, List<Node>>> whole_tree = new ArrayList<>();
        for (int level = 0; level < levels; level++) {
            Map<Node, List<Node>> branch = new HashMap<>();
            // Рандомное числов узлов для этого уровня (от 2 to 6)
            int nodesInLevel = random.nextInt(5) + 2;
            for (int i = 0; i < nodesInLevel; i++) {
                Node newNode = new Node();
                branch.put(newNode, new ArrayList<>());
                // Связать новый узел с рандомным старым.
                if (level > 0) {
                    Node parent = getRandomEntry(whole_tree.get(level - 1)).getKey();
                    parent.addChild(newNode);
                }
            }
            whole_tree.add(branch);
        }
        return whole_tree;
    }

    static <K, V> Map.Entry<K, V> getRandomEntry(Map<K, V> map) {
        List<Map.Entry<K, V>> entryList = new ArrayList<>(map.entrySet());
        return entryList.get(random.nextInt(entryList.size()));
    }

    private static void printNodes(List<Node> nodes, int indent) {
        for (Node node : nodes) {
            printNode(node, indent);
            printNodes(node.children, indent + 2);
        }
    }

    static void printing_tree(List<Map<Node, List<Node>>> whole_tree) {
        for (int level = 0; level < whole_tree.size(); level++) {
            System.out.println("Уровень " + (level + 1) + ":");
            for (Map.Entry<Node, List<Node>> entry : whole_tree.get(level).entrySet()) {
                printNode(entry.getKey(), 0);
                printNodes(entry.getValue(), 2);
            }
            System.out.println();
        }
    }

    private static void printNode(Node node, int indent) {
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            indentation.append(" ");
        }
        System.out.println(indentation + "Узел: " + node.value);
    }
}

class Node {
    String value;
    List<Node> children;

    Node() {
        this.value = random_name();
        this.children = new ArrayList<>();
    }

    void addChild(Node child) {
        this.children.add(child);
    }

    static String random_name() {
        Random number = new Random();
        return String.valueOf(number.nextInt(500));
    }
}

public class Max_Independent_Set {
    public static void max_independent_control() {
        List<Map<Node, List<Node>>> tree = Tree.filling_tree(5);
        Tree.printing_tree(tree);
        Set<Node> maxIndependentSet = max_Independent_Set(tree);
        System.out.println("\nMax Independent Set:");
        for (Node node : maxIndependentSet) {
            System.out.println("Узел: " + node.value);
        }
    }

    public static Set<Node> max_Independent_Set(List<Map<Node, List<Node>>> wholeTree) {
        Set<Node> result = new HashSet<>();
        Set<Node> visited = new HashSet<>();
        for (Map<Node, List<Node>> level : wholeTree) {
            for (Map.Entry<Node, List<Node>> entry : level.entrySet()) {
                Node currentNode = entry.getKey();
                if (!visited.contains(currentNode) && isIndependent(currentNode, visited)) {
                    result.add(currentNode);
                }
            }
        }
        return result;
    }

    private static boolean isIndependent(Node node, Set<Node> visited) {
        if (node == null || visited.contains(node)) {
            return false;
        }
        for (Node child : node.children) {
            if (visited.contains(child) || visited.contains(node) || visited.containsAll(child.children)) {
                return false;
            }
        }
        visited.add(node);
        return true;
    }
}