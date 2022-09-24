package tup.inde;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SortNumbers {
    @Autowired
    ISortAlgo bubbleSort;

    public void sortNumbers(int[] data) {
        printNumbers(bubbleSort.sort(data));
    }

    private static void printNumbers(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
            if (i != data.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("\n");
    }
}