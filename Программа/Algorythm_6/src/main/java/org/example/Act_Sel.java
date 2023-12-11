package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Act_Sel {

    static String results; // Главная строка, в неё будут сохраняться все выводы.

    public static void writing(String file_name){
        try (OutputStream outputStream = new FileOutputStream(file_name)) {
            outputStream.write(results.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void act_sel_control(int needed){
        List<int[]> numbers = new ArrayList<>();
        Random new_number = new Random();
        for (int i = 0; i < needed; i++) {
            int first = new_number.nextInt(900);
            int second = first + 1 + new_number.nextInt(1100 - (first + 1) + 1);
            numbers.add(new int[]{first, second});
        }
        StringBuilder results_builder = new StringBuilder();
        for (int[] element : numbers) {
            for (int border : element) {
                results_builder.append(Arrays.toString(new int[]{border})).append("; ");
            }
            results_builder.append("\n");
        }
        results = results_builder.toString().trim();
        writing("Act_Sel_Numbers.txt");
        // Плохой способ нахождения покрывающих отрезков.
        results_builder = new StringBuilder();
        for (int[] element : bad(numbers)) {
            for (int border : element) {
                results_builder.append(Arrays.toString(new int[]{border})).append("; ");
            }
            results_builder.append("\n");
        }
        results = results_builder.toString().trim();
        writing("Act_Sel_Bad_Results.txt");
        // Хороший способ нахождения покрывающих отрезков.
        results_builder = new StringBuilder();
        for (int[] element : good(numbers)) {
            for (int border : element) {
                results_builder.append(Arrays.toString(new int[]{border})).append("; ");
            }
            results_builder.append("\n");
        }
        results = results_builder.toString().trim();
        writing("Act_Sel_Good_Results.txt");
    }

    public static List<int[]> bad(List<int[]> borders) {
        List<int[]> results = new ArrayList<>();
        List<int[]> usefull_copy = new ArrayList<>(borders.size());
        usefull_copy.addAll(borders);
        long startTime = System.nanoTime();
        while (!usefull_copy.isEmpty()) {
            int m = Integer.MAX_VALUE;
            for (int[] x : usefull_copy) {
                m = Math.min(m, x[1]);
            }
            int minIndex = 0;
            for (int i = 0; i < usefull_copy.size(); i++) {
                if (m == usefull_copy.get(i)[1]) {
                    minIndex = i;
                    break;
                }
            }
            int[] d = usefull_copy.get(minIndex);
            results.add(d);
            int i = 0;
            while (i < usefull_copy.size()) {
                if (usefull_copy.get(i)[0] <= d[1]) {
                    usefull_copy.remove(i);
                } else {
                    i++;
                }
            }
        }
        long endTime = System.nanoTime();
        long spent_time = (endTime - startTime);  // divide by 1000000 to get milliseconds.
        System.out.println("Плохой вариант ActSel занял " + (spent_time / 1000000) + "," + ((spent_time / 10000) % 100));
        return results;
    }

    public static List<int[]> good(List<int[]> borders) {
        List<int[]> usefull_copy = new ArrayList<>(borders.size());
        List<int[]> results = new ArrayList<>();
        usefull_copy.addAll(borders);
        usefull_copy.sort(Comparator.comparingInt(x -> x[1]));
        long startTime = System.nanoTime();
        results.add(usefull_copy.get(0));
        for (int[] i : usefull_copy) {
            if (i[0] > results.get(results.size() - 1)[1]) {
                results.add(i);
            }
        }
        long endTime = System.nanoTime();
        long spent_time = (endTime - startTime);  // divide by 1000000 to get milliseconds.
        System.out.println("Хороший вариант ActSel занял " + (spent_time / 1000000) +
                "," + ((spent_time / 10000) % 100));
        return results;
    }
}
