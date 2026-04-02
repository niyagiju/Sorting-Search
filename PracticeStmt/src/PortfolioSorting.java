import java.util.*;

class Asset {
    String name;
    double returnRate;
    double volatility;

    Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }
}

public class PortfolioSorting {

    // ---------- PRINT ----------
    static void printAssets(Asset[] arr) {
        for (Asset a : arr) {
            System.out.print(a.name + ":" + a.returnRate + "% ");
        }
        System.out.println();
    }

    // ---------- MERGE SORT (ASCENDING, STABLE) ----------
    static void merge(Asset[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        Asset[] L = new Asset[n1];
        Asset[] R = new Asset[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[l + i];
        for (int j = 0; j < n2; j++) R[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;

        while (i < n1 && j < n2) {
            if (L[i].returnRate <= R[j].returnRate) {
                arr[k++] = L[i++]; // stable
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    static void mergeSort(Asset[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    // ---------- QUICK SORT (DESC + VOLATILITY ASC) ----------
    static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].returnRate > pivot.returnRate ||
                    (arr[j].returnRate == pivot.returnRate &&
                            arr[j].volatility < pivot.volatility)) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // ---------- MAIN ----------
    public static void main(String[] args) {

        Asset[] arr1 = {
                new Asset("AAPL", 12, 5),
                new Asset("TSLA", 8, 7),
                new Asset("GOOG", 15, 4)
        };

        int n = arr1.length;

        // Copy for Quick Sort
        Asset[] arr2 = new Asset[n];
        for (int i = 0; i < n; i++) {
            arr2[i] = new Asset(arr1[i].name, arr1[i].returnRate, arr1[i].volatility);
        }

        // Merge Sort (Ascending)
        mergeSort(arr1, 0, n - 1);
        System.out.println("Merge Sort (ASC):");
        printAssets(arr1);

        // Quick Sort (Descending)
        quickSort(arr2, 0, n - 1);
        System.out.println("Quick Sort (DESC):");
        printAssets(arr2);
    }
}