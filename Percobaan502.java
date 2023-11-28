public class Percobaan502 {
    // Fungsi rekursif untuk menghitung deret Fibonacci
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
    public static void main(String[] args) {
        // Jumlah suku yang ingin dihitung
        int jumlahSuku = 10; // Ganti nilai sesuai kebutuhan

        System.out.println("Deret Fibonacci:");

        for (int i = 0; i < jumlahSuku; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }
}
