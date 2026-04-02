import java.util.*;

public class RiskLookup {

    // ---------- LINEAR SEARCH (UNSORTED) ----------
    static void linearSearch(int[] arr, int target) {
        int comparisons = 0;
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Linear: Found at index " + i);
                found = true;
                break;
            }
        }

        if (!found)
            System.out.println("Linear: Not found");

        System.out.println("Comparisons: " + comparisons);
        System.out.println("Time Complexity: O(n)\n");
    }

    // ---------- BINARY SEARCH INSERTION POINT ----------
    static int insertionPoint(int[] arr, int target, int[] comp) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            comp[0]++;
            int mid = (low + high) / 2;

            if (arr[mid] == target)
                return mid;
            else if (arr[mid] < target)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return low; // insertion position
    }

    // ---------- FLOOR ----------
    static int floorValue(int[] arr, int target, int[] comp) {
        int low = 0, high = arr.length - 1;
        int floor = -1;

        while (low <= high) {
            comp[0]++;
            int mid = (low + high) / 2;

            if (arr[mid] <= target) {
                floor = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return floor;
    }

    // ---------- CEILING ----------
    static int ceilingValue(int[] arr, int target, int[] comp) {
        int low = 0, high = arr.length - 1;
        int ceil = -1;

        while (low <= high) {
            comp[0]++;
            int mid = (low + high) / 2;

            if (arr[mid] >= target) {
                ceil = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ceil;
    }

    // ---------- MAIN ----------
    public static void main(String[] args) {

        int[] unsorted = {50, 10, 100, 25};
        int target = 30;

        // Linear Search
        linearSearch(unsorted, target);

        // Sort for Binary Search
        int[] sorted = unsorted.clone();
        Arrays.sort(sorted);
        System.out.println("Sorted Risks: " + Arrays.toString(sorted));

        int[] comp = {0};

        int pos = insertionPoint(sorted, target, comp);
        int floor = floorValue(sorted, target, comp);
        int ceil = ceilingValue(sorted, target, comp);

        System.out.println("\nBinary Search:");
        System.out.println("Insertion Point: index " + pos);
        System.out.println("Floor: " + floor);
        System.out.println("Ceiling: " + ceil);
        System.out.println("Comparisons: " + comp[0]);
        System.out.println("Time Complexity: O(log n)");
    }
}