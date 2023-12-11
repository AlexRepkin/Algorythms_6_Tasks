package org.example;

public class Main {

    static int needed = 30;

    public static void main(String[] args) {
        Points_Cover.points_control(needed); // Покрыть все точки минимальным количеством прямых.
        Act_Sel.act_sel_control(needed); // Покрыть максимальным количеством непересекаемых отрезков точки.
        Max_Independent_Set.max_independent_control(); // Максимальное количество не соединёных вершин.
        Knap_Sack.knap_sack_control(); // Максималья цена за вещи разрешённого веса.
    }
}