package org.example;


public class DualPivotQuickSort {
    private InsertionSort insertionSort;

    public DualPivotQuickSort() {
        this.insertionSort = new InsertionSort();
    }

    public void swap(int[] numbers, int start, int end) {
        int temp = numbers[start];
        numbers[start] = numbers[end];
        numbers[end] = temp;
    }

    // 피봇을 2개로 분류한 후 정렬 시작
    public void dualPivotQuickSort(int[] numbers, int start, int end, int standardIndex) {
        if (start > end) return;
        if (start <= end) {
            // choose two pivot elements p1 and p2
            int pv1 = numbers[start];
            int pv2 = numbers[end];

            // P1 must be less then P2, otherwise they are swapped.
            if (pv1 > pv2) {
                swap(numbers, pv1, pv2);
            }

            // For small sub arrays, use the insertion sort algorithm
            // small sub array : the length (end - start) is less than 17 (n value) or some value
            if ((end - start) < standardIndex) {
                insertionSort.insertionSort(numbers);
                return;
            }

            int l = start + 1;
            int k = start + 1;
            int g = end - 1;

            while (k <= g) {
                if (numbers[k] < pv1) {
                    swap(numbers, k, l);
                    l++;
                } else {
                    if ((numbers[g] > pv2) && (k < g)) {
                        g--;
                    }

                    swap(numbers, k, g);
                    g--;

                    if (numbers[k] < pv1) {
                        swap(numbers, k, l);
                        l++;
                    }
                }
                k++;
            }

            l--;
            g++;

            swap(numbers, start, l);
            swap(numbers, end, g);

            dualPivotQuickSort(numbers, start, l - 1, standardIndex);
            dualPivotQuickSort(numbers, l + 1, g - 1, standardIndex);
            dualPivotQuickSort(numbers, g + 1, end, standardIndex);
        }
    }
}
