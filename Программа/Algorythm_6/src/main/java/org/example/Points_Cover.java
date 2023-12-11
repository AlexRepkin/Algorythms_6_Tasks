package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Points_Cover {

    static String results; // Главная строка, в неё будут сохраняться все выводы.

    public static void writing(String file_name){
        try (OutputStream outputStream = new FileOutputStream(file_name)) {
            outputStream.write(results.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void points_control(int needed){
        List<Double> numbers = new ArrayList<>();
        Random new_number = new Random();
        for (int i = 0; i < needed; i++) numbers.add(new_number.nextDouble() * 51);
        Collections.sort(numbers);
        StringBuilder results_builder = new StringBuilder();
        for (Double element : numbers) {
            results_builder.append(element).append("; ");
            results_builder.append("\n");
        }
        results = results_builder.toString().trim();
        writing("PointsCover_Numbers.txt");
        // Плохой способ нахождения покрывающих отрезков.
        results_builder = new StringBuilder();
        for (List<Double> line : Points_Cover.bad(numbers)) {
            for (Double border : line) {
                results_builder.append(border).append("; "); // К каждому элементу вложенного списка добавляется "; "
            }
            results_builder.append("\n"); // После каждой пары (Границ отрезка) ставится новая строка
        }
        results = results_builder.toString().trim();
        writing("PointsCover_Not_Good_Results.txt");
        // Хороший способ нахождения покрывающих отрезков.
        results_builder = new StringBuilder();
        for (List<Double> line : Points_Cover.good(numbers)) {
            for (Double border : line) {
                results_builder.append(border).append("; ");
            }
            results_builder.append("\n");
        }
        results = results_builder.toString().trim();
        writing("PointsCover_Good_Results.txt");
    }

    public static List<List<Double>> bad(List<Double> numbers) {
        List<Double> usefull_copy = new ArrayList<>(numbers.size());
        usefull_copy.addAll(numbers);
        List<List<Double>> results = new ArrayList<>();
        long startTime = System.nanoTime();
        while (!usefull_copy.isEmpty()) {
            double xm = Collections.min(usefull_copy);
            results.add(List.of(xm, xm + 1));
            int i = 0;
            while (i < usefull_copy.size()) {
                if (results.get(results.size() - 1).get(0) <= usefull_copy.get(i) &&
                        usefull_copy.get(i) <= results.get(results.size() - 1).get(1)) {
                    usefull_copy.remove(i);
                } else {
                    i++;
                }
            }
        }
        long endTime = System.nanoTime();
        long spent_time = (endTime - startTime);  // divide by 1000000 to get milliseconds.
        System.out.println("Плохой вариант PointsCover занял " + (spent_time / 1000000) + ","
                + ((spent_time / 10000) % 100));
        return results;
    }

    public static List<List<Double>> good(List<Double> numbers) {
        List<List<Double>> results = new ArrayList<>();
        long startTime = System.nanoTime();
        int i = 0;
        while (i < numbers.size()) {
            double xm = numbers.get(i);
            results.add(List.of(xm, xm + 1));
            i++;
            while (i < numbers.size() && numbers.get(i) <= xm + 1) {
                i++;
            }
        }
        long endTime = System.nanoTime();
        long spent_time = (endTime - startTime);  // divide by 1000000 to get milliseconds.
        System.out.println("Хороший вариант PointsCover занял " + (spent_time / 1000000) +
                "," + ((spent_time / 10000) % 100));
        return results;
    }
}
