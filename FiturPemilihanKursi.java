import java.util.Scanner;

public class FiturPemilihanKursi {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        boolean lanjutPilihKursi = true;
        char[][] kursi = new char[5][5]; // Studio dengan 5 baris dan 5 kolom
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                kursi[i][j] = '0'; // Semua kursi awalnya kosong ('0')
            }
        }

        while (lanjutPilihKursi) {
            System.out.println("____________________________________________________");
            System.out.println("|Studio         |      LAYAR      |                |");
            System.out.println("|The Premiere   ===================                |");
            System.out.println("|                                                  |");
            for (int i = 0; i < 5; i++) {
                char barisHuruf = (char) ('A' + i);
                System.out.print("|      ");
                for (int j = 0; j < 5; j++) {
                    System.out.print(" [" + kursi[i][j] + "]    ");
                }
                System.out.println("  |"  + barisHuruf + "|");
                System.out.println("|                                                  |");
            }
            System.out.println("|_______|1|_____|2|_____|3|_____|4|_____|5|________|");
            System.out.print("Pilih kolom Baris (A-E) dan 0 untuk cancel: ");
            
            char barisHurufInput = input.next().charAt(0);
            int baris = barisHurufInput - 'A' + 1;
            System.out.print("Pilih kolom Kursi (1-5) dan 0 untuk cancel: ");
            int kolom = input.nextInt();

            if (barisHurufInput == '0' || kolom == 0) {
                lanjutPilihKursi = false;
                System.out.println("Pemilihan kursi dibatalkan.");
            } else if (baris >= 1 && baris <= 5 && kolom >= 1 && kolom <= 5) {
                if (kursi[baris - 1][kolom - 1] == '0') {
                    kursi[baris - 1][kolom - 1] = 'X'; // Menandai kursi sebagai terisi ('X')
                    System.out.println("Anda telah memilih kursi Baris " + (char)('A' + baris - 1) + ", Kolom " + kolom);
                } else {
                    System.out.println("Kursi sudah terisi. Silakan pilih kursi yang lain.");
                }
            } else {
                System.out.println("Nomor baris atau kolom tidak valid.");
            }
        }
        System.out.println("Pilihan kursi Anda:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (kursi[i][j] == 'X') {
                    System.out.println("Baris " + (char)('A' + i) + ", Kolom " + (j + 1));
                }
            }
        }
        System.out.println("Terima kasih telah memilih kursi! Selamat menonton film.");
    }
}
