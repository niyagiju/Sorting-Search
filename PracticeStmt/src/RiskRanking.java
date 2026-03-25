import java.util.*;

class Client {
    String name;
    int riskScore;
    int accountBalance;

    Client(String name, int riskScore, int accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    public String toString() {
        return name + ":" + riskScore;
    }
}

public class RiskRanking {

    // Bubble Sort (Ascending riskScore)
    static void bubbleSort(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        System.out.println("Bubble Sort (Asc): " + Arrays.toString(arr));
        System.out.println("Swaps: " + swaps);
    }


    static void insertionSort(Client[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 && compare(arr[j], key) < 0) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }

        System.out.println("Insertion Sort (Desc): " + Arrays.toString(arr));
    }

    // Comparator: risk DESC, then balance DESC
    static int compare(Client a, Client b) {
        if (a.riskScore != b.riskScore)
            return a.riskScore - b.riskScore; // ascending base

        return a.accountBalance - b.accountBalance;
    }

    // Top 10 highest risk
    static void topRisk(Client[] arr) {
        System.out.print("Top Risks: ");
        for (int i = 0; i < Math.min(10, arr.length); i++) {
            System.out.print(arr[i].name + "(" + arr[i].riskScore + ") ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 2000),
                new Client("clientB", 50, 3000)
        };

        bubbleSort(clients);     // Ascending
        insertionSort(clients);  // Descending
        topRisk(clients);
    }
}