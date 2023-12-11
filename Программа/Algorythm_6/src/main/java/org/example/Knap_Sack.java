package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Knap_Sack {
    public static void knap_sack_control() {
        Random amount = new Random();
        // Первая цифра - номер вещи,вторая - цена, третья - вес.
        int[][] items_prepared = {{1, 20, 4}, {2, 18, 3}, {3, 14, 2}};
        int capacity = 7;
        System.out.println("\nКогда в магазине три предмета, как в примере, то:");
        calculating(items_prepared, capacity);
        capacity = amount.nextInt( 50);
        int[][] items = new int[amount.nextInt(12) + 2][3];
        for (int i = 0; i < items.length; i++) {
            items[i][0] = i + 1;
            items[i][1] = amount.nextInt(100) + 1;  // Цена от 1 до 100.
            items[i][2] = amount.nextInt(capacity) + 1;  // Рандомный вес.
        }
        System.out.println("Рандомное количество предметов, рандомные цены и веса:");
        for (int[] thing : items) System.out.println("Продукт " + thing[0]
                + " стоит " + thing[1] + " и весит " +thing[2]);
        calculating(items, capacity);
    }

    static void calculating(int[][] items, int capacity){
        /* Сортировка двумерного массива цен\веса относительно специального компаратора.
            cost - цена, weight - вес. Сортировка происходит относительно сравнения цены/вес.
         */
        Arrays.sort(items, (cost, weight) -> Integer.compare(weight[1] / weight[2], cost[1] / cost[2]));
        Map<Integer, Integer> price_and_amount = new HashMap<>();
        int max_money = 0;
        int max_weight = 0;
        for (int[] item : items) {
            int remaining_capacity = capacity - max_weight;
            if (remaining_capacity / item[2] >= 1) {
                price_and_amount.put(item[0], item[2]);
                max_weight += item[2];
                max_money += item[1];
            } else {
                price_and_amount.put(item[0], remaining_capacity);
                max_money += (double) item[1] / item[2] * remaining_capacity;
                break;
            }
        }
        for (Map.Entry<Integer, Integer> element : price_and_amount.entrySet()) {
            System.out.println("Количество элемента " + element.getKey() + " = " + element.getValue());
        }
        System.out.println("Потраченные деньги - " + max_money);
    }
}
