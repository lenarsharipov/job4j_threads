package ru.job4j.pool;

public class Search {
    private static final int MIN_LENGTH = 10;
    public static int search(int[] array, int value) {
        return search(array, 0, array.length - 1, value);
    }

    private static int search(int[] array, int from, int to, int value) {
        if (to - from <= MIN_LENGTH) {
            int index = -1;
            for (int i = from; i <= to; i++) {
                if (array[i] == value) {
                    index = i;
                    break;
                }
            }
            return index;
        }

        int mid = (from + to) / 2;
        return merge(
                search(array, from, mid, value),
                search(array, mid + 1, to, value)
        );
    }

    public static int merge(int left, int right) {
        return Math.max(left, right);
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21};
        int index = search(array, 19);
        System.out.println(index);
    }
}
