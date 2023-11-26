import java.util.Scanner;

public class NilaiMahasiswa {
    static int[][] nilai;
    static String[] mahasiswa;
    static int minggu;

    public static void main(String[] args) {
        inputDataMahasiswa();
        tampilkanNilaiSemua();
        cariMingguTerbaik();
        tampilkanMahasiswaDenganNilaiTertinggi();
    }

    // Fungsi untuk meninputkan data nilai mahasiswa
    static void inputDataMahasiswa() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan jumlah mahasiswa: ");
        int jumlahMahasiswa = scanner.nextInt();

        System.out.print("Masukkan jumlah minggu/tugas: ");
        minggu = scanner.nextInt();

        nilai = new int[jumlahMahasiswa][minggu];
        mahasiswa = new String[jumlahMahasiswa];

        for (int i = 0; i < jumlahMahasiswa; i++) {
            System.out.print("Masukkan nama mahasiswa: ");
            mahasiswa[i] = scanner.next();

            System.out.println("Masukkan nilai untuk " + mahasiswa[i] + " pada Minggu 1 sampai " + minggu + ":");
            for (int j = 0; j < minggu; j++) {
                System.out.print("Masukkan nilai untuk Minggu " + (j + 1) + ": ");
                nilai[i][j] = scanner.nextInt();
            }
        }
    }

    // Fungsi untuk menampilkan seluruh nilai mahasiswa
    static void tampilkanNilaiSemua() {
        System.out.println("\nMenampilkan seluruh nilai:");
        System.out.printf("%-10s", "Mahasiswa");
        for (int i = 0; i < minggu; i++) {
            System.out.printf("%-10s", "Minggu " + (i + 1)+" ");
        }
        System.out.println();

        for (int i = 0; i < nilai.length; i++) {
            System.out.printf("%-10s", mahasiswa[i]);
            for (int j = 0; j < minggu; j++) {
                System.out.printf("%-10d", nilai[i][j]);
            }
            System.out.println();
        }
    }

    // Fungsi untuk mencari pada hari keberapakah terdapat nilai tertinggi dibanding hari lain dari keseluruhan mahasiswa
    static void cariMingguTerbaik() {
        int[] totalNilai = new int[minggu];

        for (int i = 0; i < minggu; i++) {
            for (int j = 0; j < nilai.length; j++) {
                totalNilai[i] += nilai[j][i];
            }
        }

        int mingguTerbaik = 0;
        int nilaiTertinggi = totalNilai[0];

        for (int i = 1; i < minggu; i++) {
            if (totalNilai[i] > nilaiTertinggi) {
                nilaiTertinggi = totalNilai[i];
                mingguTerbaik = i;
            }
        }

        System.out.println("\nMinggu terbaik adalah Minggu " + (mingguTerbaik + 1) + " dengan total nilai " + nilaiTertinggi);
    }

    // Fungsi untuk menampilkan mahasiswa yang memiliki nilai tertinggi
    static void tampilkanMahasiswaDenganNilaiTertinggi() {
        int nilaiTertinggi = 0;
        int indeksMahasiswa = 0;
        int indeksMinggu = 0;

        for (int i = 0; i < nilai.length; i++) {
            for (int j = 0; j < minggu; j++) {
                if (nilai[i][j] > nilaiTertinggi) {
                    nilaiTertinggi = nilai[i][j];
                    indeksMahasiswa = i;
                    indeksMinggu = j;
                }
            }
        }
        System.out.println("\nMahasiswa dengan nilai tertinggi adalah " + mahasiswa[indeksMahasiswa] +
        " dengan nilai " + nilaiTertinggi + " pada Minggu" + (indeksMinggu + 1));
    }
}
