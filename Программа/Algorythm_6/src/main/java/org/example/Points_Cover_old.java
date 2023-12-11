//package org.example;
//
//import java.util.*;
//
//public class Points_Cover_old {
//
//    public static StringBuilder WorsePointsCover(List<Integer> numbers, int needed) {
//        StringBuilder results = new StringBuilder();
//        long startTime = System.nanoTime();
//        int i = 0;
//        while (i < needed) {
//            int found_element = numbers.get(i);
//            results.append(found_element).append(";").append(found_element + 1).append("\n");
//            i++;
//            while (i < needed && numbers.get(i) <= found_element + 1) {
//                i++;
//            }
//        }
//        long endTime = System.nanoTime();
//        long spent_time = (endTime - startTime);  // divide by 1000000 to get milliseconds.
//        System.out.println("Bad variant of PointsCover took " + (spent_time / 1000000) + "," + ((spent_time / 10000) % 100));
//        return results;
//    }
//
//    public static StringBuilder BetterPointsCover(List<Integer> numbers) {
//        List<int[]> results = new ArrayList<>();
//        long startTime = System.nanoTime();
//        int i = 0;
//        StringBuilder resultStringBuilder = new StringBuilder();
//        while (i < numbers.size()) {
//            int found_element = numbers.get(i);
//            int j = i + 1;
//            while (j < numbers.size() && numbers.get(j) == (found_element + 1)) {
//                found_element = numbers.get(j); // Update found_element with the current j value
//                j++;
//            }
//            if (j < numbers.size()) {
//                results.add(new int[]{found_element, numbers.get(j)});
//                resultStringBuilder.append(found_element).append(";").append(numbers.get(j)).append("\n");
//            }
//            i = j;
//        }
//        long endTime = System.nanoTime();
//        long spent_time = (endTime - startTime);  // divide by 1000000 to get milliseconds.
//        System.out.println("Better variant of PointsCover took " + (spent_time / 1000000) + "," + ((spent_time / 10000) % 100));
//        return resultStringBuilder;
//    }
//}
