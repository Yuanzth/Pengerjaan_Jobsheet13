import java.util.Scanner;

public class DasprKasaran {

    static String[] usernames = {"admin"};
    static String[] passwords = {"admin123"};
    static Scanner input = new Scanner(System.in);
    //>>>>>>>>>>>>>>>>>>>>>>>> Kebutuhan Untuk Pemilihan Film <<<<<<<<<<<<<<<<<<<<<<<<<<<//
    static int jdlinput = -1; // Inisialisasi dengan nilai default yang tidak valid
    static Scanner inputUntukJdlFilm = new Scanner(System.in);
    static String[] jdl_film = {"WISH", "The Marvels", "Napoleon"};
    static String filmTerpilih = "";
    //>>>>>>>>>>>>>>>>>>>>>>>> Kebutuhan Untuk Pemilihan Film <<<<<<<<<<<<<<<<<<<<<<<<<<<//

    //>>>>>>>>>>>>>>>>>>>>>>> Kebutuhan Untuk Pemilihan Studio <<<<<<<<<<<<<<<<<<<<<<<<<<//
    static Scanner inputUntukStudio = new Scanner(System.in);
    static int studioInput = -1; // Inisialisasi dengan nilai default yang tidak valid
    static String[] studioWISH = {"Deluxe"};
    static int[] hrg_stdWISH = {30000};
    static String[] studioTheMarvels = {"Deluxe", "IMAX"};
    static int[] hrg_stdTheMarvels = {30000,40000};
    static String[] studioNapoleon = {"The Premiere"};
    static int[] hrg_stdNapoleon = {50000};
    //>>>>>>>>>>>>>>>>>>>>>>> Kebutuhan Untuk Pemilihan Studio <<<<<<<<<<<<<<<<<<<<<<<<<<//

    // >>>>>>> Variabel dan fungsi untuk jumlah tiket dan pemilihan kursi <<<<<<<<
    static int jumlahTiket = 0;
    static String[] kursiTerpilih = new String[25]; // Menyimpan indeks kursi yang dipilih

    static void PemilihanJumlahTiket() {
        System.out.print("Masukkan jumlah tiket yang ingin dipesan: ");
        jumlahTiket = inputUntukJdlFilm.nextInt();
        if (jumlahTiket <= 0) {
            System.out.println("Jumlah tiket tidak valid.");
            PemilihanJumlahTiket();
        }
    }
    // >>>>>>> Variabel dan fungsi untuk jumlah tiket dan pemilihan kursi <<<<<<<<

    //>>>>>>>>>>>>>>>>>>>>>>>>>> Kebutuhan Untuk Kursi Studio <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<//
    static Scanner inputUntukPilihKursi = new Scanner(System.in);
    static char[][] StudioDeluxeWish = new char[5][5]; // StudioDeluxeWish dengan 5 baris dan 5 kolom
    static char[][] StudioIMAXTheMarvels = new char[5][5]; // StudioIMAXTheMarvels dengan 5 baris dan 5 kolom
    static char[][] StudioDeluxeTheMarvels = new char[5][5]; // StudioDeluxeTheMarvels dengan 5 baris dan 5 kolom
    static char[][] StudioThePremiereNapoleon = new char[5][5]; // StudioThePremiereNapoleon dengan 5 baris dan 5 kolom
    static int indexKursiTerpilih = 0; // Menunjukkan indeks terakhir kursi terpilih
    //>>>>>>>>>>>>>>>>>>>>>>>>>> Kebutuhan Untuk Kursi Studio <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<//


    public static void main(String[] args) {
        fillArrayWithZero(StudioDeluxeWish);
        fillArrayWithZero(StudioIMAXTheMarvels);
        fillArrayWithZero(StudioDeluxeTheMarvels);
        fillArrayWithZero(StudioThePremiereNapoleon);        

        boolean isLoggedIn = false;
        String loggedInUser = "";

        System.out.println("Selamat datang di Sistem Booking Tiket Bioskop");

        while (!isLoggedIn) {
            System.out.print("Masukkan nama pengguna: ");
            String username = input.nextLine();
            System.out.print("Masukkan kata sandi: ");
            String password = input.nextLine();
            int userIndex = -1;
            for (int i = 0; i < usernames.length; i++) {
                if (usernames[i].equals(username) && passwords[i].equals(password)) {
                    userIndex = i;
                    break;
                }
            }
            if (userIndex >= 0) {
                isLoggedIn = true;
                loggedInUser = username;
                System.out.println("Selamat datang, " + loggedInUser + "!");
            } else {
                System.out.println("Login gagal. Periksa kembali nama pengguna dan kata sandi.");
            }
        }
        PemilihanFILM();
        filmTerpilih = jdl_film[jdlinput];

        if (filmTerpilih.equalsIgnoreCase("WISH")) {
            PemilihanStudioWish();
            if (studioInput == 0) {
                PemilihanJumlahTiket();
                PemilihanKursiStudioDeluxeWish();
                tampilkanRingkasan();
                prosesPembayaran();
            }
        } else if (filmTerpilih.equalsIgnoreCase("The Marvels")) {
            PemilihanStudioTheMarvels();
            if (studioInput == 0) {
                PemilihanJumlahTiket();
                PemilihanKursiStudioDeluxeTheMarvels();
                tampilkanRingkasan();
                prosesPembayaran();
            } else if (studioInput == 1) {
                PemilihanJumlahTiket();
                PemilihanKursiStudioIMAXTheMarvels();
                tampilkanRingkasan();
                prosesPembayaran();
            }
        } else if (filmTerpilih.equalsIgnoreCase("Napoleon")) {
            PemilihanStudioNapoleon();
            if (studioInput == 0) {
                PemilihanJumlahTiket();
                PemilihanKursiStudioThePremiereNapoleon();
                tampilkanRingkasan();
                prosesPembayaran();
            }
        }
    }

    static void tampilkanRingkasan() {
        System.out.println("===== Ringkasan Pemesanan =====");
        System.out.println("Film: " + filmTerpilih);
        System.out.println("Studio: " + getStudioTerpilih());
        System.out.println("Jumlah Tiket: " + jumlahTiket);
        System.out.println("Pilihan Kursi: ");
        for (int i = 0; i < indexKursiTerpilih; i++) {
            System.out.println(kursiTerpilih[i]);
        }
        System.out.println("===============================");
    }

    static void prosesPembayaran() {
        System.out.println("===== Proses Pembayaran =====");
        System.out.println("Total Harga: " + hitungTotalHarga());
        System.out.println("Pilih metode pembayaran:");
        System.out.println("1. Tunai");
        System.out.println("2. Kartu Kredit");
        int metodePembayaran = input.nextInt();
        if (metodePembayaran == 1) {
            System.out.println("Pembayaran berhasil. Tiket telah terpesan!");
        } else if (metodePembayaran == 2) {
            System.out.println("Masukkan nomor kartu kredit:");
            String nomorKartuKredit = input.next();
            System.out.println("Pembayaran berhasil. Tiket telah terpesan!");
        } else {
            System.out.println("Metode pembayaran tidak valid.");
        }
    }

    static int hitungTotalHarga() {
        int hargaTiket = 0;
        if (filmTerpilih.equalsIgnoreCase("WISH")) {
            hargaTiket = hrg_stdWISH[studioInput] * jumlahTiket;
        } else if (filmTerpilih.equalsIgnoreCase("The Marvels")) {
            if (studioInput == 0) {
                hargaTiket = hrg_stdTheMarvels[0] * jumlahTiket;
            } else if (studioInput == 1) {
                hargaTiket = hrg_stdTheMarvels[1] * jumlahTiket;
            }
        } else if (filmTerpilih.equalsIgnoreCase("Napoleon")) {
            hargaTiket = hrg_stdNapoleon[studioInput] * jumlahTiket;
        }
        return hargaTiket;
    }

    static String getStudioTerpilih() {
        if (filmTerpilih.equalsIgnoreCase("WISH")) {
            return studioWISH[studioInput];
        } else if (filmTerpilih.equalsIgnoreCase("The Marvels")) {
            return studioTheMarvels[studioInput];
        } else if (filmTerpilih.equalsIgnoreCase("Napoleon")) {
            return studioNapoleon[studioInput];
        } else {
            return "";
        }
    }

//=======================================================================================================////
    static void PemilihanFILM(){
        // Pilih Film
        System.out.println("Daftar Film yang Tersedia:");
        for (int i = 0; i < jdl_film.length; i++) {
            System.out.println((i + 1) + ". " + jdl_film[i]);
        }
        System.out.print("Pilih nomor film yang ingin Anda tonton: ");
        jdlinput = inputUntukJdlFilm.nextInt();
        if (jdlinput >= 1 && jdlinput <= jdl_film.length) {
            jdlinput--; // Mengurangi 1 karena array dimulai dari 0
            System.out.println("Film yang Anda pilih: " + jdl_film[jdlinput]);
        } else {
            System.out.println("Nomor film yang Anda masukkan tidak valid.");
        }       
    }
    static void PemilihanStudioWish(){
            // Pilih Studio
            System.out.println("Daftar Studio yang Tersedia:");
            for (int i = 0; i < studioWISH.length; i++) {
                System.out.println((i + 1) + ". " + studioWISH[i] + " (Harga: " + hrg_stdWISH[i] + ")");
            }
            System.out.print("Pilih nomor studio: ");
            studioInput = inputUntukStudio.nextInt();
            if (studioInput >= 1 && studioInput <= studioWISH.length) {
                studioInput--; // Mengurangi 1 karena array dimulai dari 0
                System.out.println("Studio yang Anda pilih: " + studioWISH[studioInput]);
            } 
            else {
                System.out.println("Nomor studio yang Anda masukkan tidak valid.");
            }
    }
    static void PemilihanStudioTheMarvels(){
            // Pilih Studio
            System.out.println("Daftar Studio yang Tersedia:");
            for (int i = 0; i < studioTheMarvels.length; i++) {
                System.out.println((i + 1) + ". " + studioTheMarvels[i] + " (Harga: " + hrg_stdTheMarvels[i] + ")");
            }
            System.out.print("Pilih nomor studio: ");
            studioInput = inputUntukStudio.nextInt();
            if (studioInput >= 1 && studioInput <= studioTheMarvels.length) {
                studioInput--; // Mengurangi 1 karena array dimulai dari 0
                System.out.println("Studio yang Anda pilih: " + studioTheMarvels[studioInput]);
            } 
            else {
                System.out.println("Nomor studio yang Anda masukkan tidak valid.");
            }
    }
    static void PemilihanStudioNapoleon(){
            // Pilih Studio
            System.out.println("Daftar Studio yang Tersedia:");
            for (int i = 0; i < studioNapoleon.length; i++) {
                System.out.println((i + 1) + ". " + studioNapoleon[i] + " (Harga: " + hrg_stdNapoleon[i] + ")");
            }
            System.out.print("Pilih nomor studio: ");
            studioInput = inputUntukStudio.nextInt();
            if (studioInput >= 1 && studioInput <= studioNapoleon.length) {
                studioInput--; // Mengurangi 1 karena array dimulai dari 0
                System.out.println("Studio yang Anda pilih: " + studioNapoleon[studioInput]);
            } 
            else {
                System.out.println("Nomor studio yang Anda masukkan tidak valid.");
            }
    }
    static void fillArrayWithZero(char[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = '0';
            }
        }
    }
    static void PemilihanKursiStudioDeluxeWish(){
        System.out.println("____________________________________________________");
        System.out.println("|Studio         |      LAYAR      |                |");
        System.out.println("|Deluxe         ===================                |");
        System.out.println("|                                                  |");
        for (int i = 0; i < 5; i++) {
            char barisHuruf = (char) ('A' + i);
            System.out.print("|      ");
            for (int j = 0; j < 5; j++) 
            {
                System.out.print(" [" + StudioDeluxeWish[i][j] + "]    ");
            }
            System.out.println("  |"  + barisHuruf + "|");
            System.out.println("|                                                  |");
        }
        System.out.println("|_______|1|_____|2|_____|3|_____|4|_____|5|________|");
        System.out.println("----------------------------------------------------");
        System.out.println("Silahkan memilih Kursi untuk Studio Deluxe: ");
        for (int i = 0; i < jumlahTiket; i++)
        {     
            System.out.println("Kursi ke-" + (i + 1) + ". ");
            System.out.print("Pilih kolom Baris (A-E) dan 0 untuk cancel: ");
            char barisHurufInput = inputUntukPilihKursi.next().charAt(0);
            int baris = barisHurufInput - 'A' + 1;
            System.out.print("Pilih kolom Kursi (1-5) dan 0 untuk cancel: ");
            int kolom = inputUntukPilihKursi.nextInt();

            if (barisHurufInput == '0' || kolom == 0) 
            {
                System.out.println("Pemilihan kursi dibatalkan.");
                break; // Jika pembatalan, keluar dari loop
            } 
            else if (baris >= 1 && baris <= 5 && kolom >= 1 && kolom <= 5) 
            {
                if (StudioDeluxeWish[baris - 1][kolom - 1] == '0') 
                {
                    StudioDeluxeWish[baris - 1][kolom - 1] = 'X'; // Menandai kursi sebagai terisi ('X')
                    kursiTerpilih[indexKursiTerpilih] = "Baris " + barisHurufInput + ", Kolom " + kolom;
                    indexKursiTerpilih++;
                    System.out.println("Anda telah memilih kursi Baris " + barisHurufInput + ", Kolom " + kolom);
                } 
                else 
                {
                    System.out.println("Kursi sudah terisi. Silakan pilih kursi yang lain.");
                    i--; // Mengulang input untuk kursi yang sudah terisi
                }
            } 
            else 
            {
                System.out.println("Nomor baris atau kolom tidak valid. Silakan coba lagi.");
                i--; // Mengulang input untuk nomor baris atau kolom yang tidak valid
            }
        }
        System.out.println("Pilihan kursi Anda:");
        for (int i = 0; i < indexKursiTerpilih; i++) {
            System.out.println(kursiTerpilih[i]);
        }
    }
    static void PemilihanKursiStudioIMAXTheMarvels(){
        System.out.println("____________________________________________________");
        System.out.println("|Studio         |      LAYAR      |                |");
        System.out.println("|IMAX           ===================                |");
        System.out.println("|                                                  |");
        for (int i = 0; i < 5; i++) 
        {
            char barisHuruf = (char) ('A' + i);
            System.out.print("|      ");
            for (int j = 0; j < 5; j++) 
            {
                System.out.print(" [" + StudioIMAXTheMarvels[i][j] + "]    ");
            }
            System.out.println("  |"  + barisHuruf + "|");
            System.out.println("|                                                  |");
        }
        System.out.println("|_______|1|_____|2|_____|3|_____|4|_____|5|________|");
        System.out.println("Silahkan memilih Kursi untuk Studio IMAX: ");
        for (int i = 0; i < jumlahTiket; i++)
        {     
            System.out.println("Kursi ke-" + (i + 1) + ". ");
            System.out.print("Pilih kolom Baris (A-E) dan 0 untuk cancel: ");
            char barisHurufInput = inputUntukPilihKursi.next().charAt(0);
            int baris = barisHurufInput - 'A' + 1;
            System.out.print("Pilih kolom Kursi (1-5) dan 0 untuk cancel: ");
            int kolom = inputUntukPilihKursi.nextInt();

            if (barisHurufInput == '0' || kolom == 0) 
            {
                System.out.println("Pemilihan kursi dibatalkan.");
                break; // Jika pembatalan, keluar dari loop
            } 
            else if (baris >= 1 && baris <= 5 && kolom >= 1 && kolom <= 5) 
            {
                if (StudioIMAXTheMarvels[baris - 1][kolom - 1] == '0') 
                {
                    StudioIMAXTheMarvels[baris - 1][kolom - 1] = 'X'; // Menandai kursi sebagai terisi ('X')
                    kursiTerpilih[indexKursiTerpilih] = "Baris " + barisHurufInput + ", Kolom " + kolom;
                    indexKursiTerpilih++;
                    System.out.println("Anda telah memilih kursi Baris " + barisHurufInput + ", Kolom " + kolom);
                } 
                else 
                {
                    System.out.println("Kursi sudah terisi. Silakan pilih kursi yang lain.");
                    i--; // Mengulang input untuk kursi yang sudah terisi
                }
            } 
            else 
            {
                System.out.println("Nomor baris atau kolom tidak valid. Silakan coba lagi.");
                i--; // Mengulang input untuk nomor baris atau kolom yang tidak valid
            }
        }
        System.out.println("Pilihan kursi Anda:");
        for (int i = 0; i < indexKursiTerpilih; i++) {
            System.out.println(kursiTerpilih[i]);
        }
    }
    static void PemilihanKursiStudioDeluxeTheMarvels(){
        System.out.println("____________________________________________________");
        System.out.println("|Studio         |      LAYAR      |                |");
        System.out.println("|Deluxe         ===================                |");
        System.out.println("|                                                  |");
        for (int i = 0; i < 5; i++) 
        {
            char barisHuruf = (char) ('A' + i);
            System.out.print("|      ");
            for (int j = 0; j < 5; j++) 
            {
                System.out.print(" [" + StudioDeluxeTheMarvels[i][j] + "]    ");
            }
            System.out.println("  |"  + barisHuruf + "|");
            System.out.println("|                                                  |");
        }
        System.out.println("|_______|1|_____|2|_____|3|_____|4|_____|5|________|");
        System.out.println("Silahkan memilih Kursi untuk Studio Deluxe: ");
        for (int i = 0; i < jumlahTiket; i++)
        {     
            System.out.println("Kursi ke-" + (i + 1) + ". ");
            System.out.print("Pilih kolom Baris (A-E) dan 0 untuk cancel: ");
            char barisHurufInput = inputUntukPilihKursi.next().charAt(0);
            int baris = barisHurufInput - 'A' + 1;
            System.out.print("Pilih kolom Kursi (1-5) dan 0 untuk cancel: ");
            int kolom = inputUntukPilihKursi.nextInt();

            if (barisHurufInput == '0' || kolom == 0) 
            {
                System.out.println("Pemilihan kursi dibatalkan.");
                break; // Jika pembatalan, keluar dari loop
            } 
            else if (baris >= 1 && baris <= 5 && kolom >= 1 && kolom <= 5) 
            {
                if (StudioDeluxeTheMarvels[baris - 1][kolom - 1] == '0') 
                {
                    StudioDeluxeTheMarvels[baris - 1][kolom - 1] = 'X'; // Menandai kursi sebagai terisi ('X')
                    kursiTerpilih[indexKursiTerpilih] = "Baris " + barisHurufInput + ", Kolom " + kolom;
                    indexKursiTerpilih++;
                    System.out.println("Anda telah memilih kursi Baris " + barisHurufInput + ", Kolom " + kolom);
                } 
                else 
                {
                    System.out.println("Kursi sudah terisi. Silakan pilih kursi yang lain.");
                    i--; // Mengulang input untuk kursi yang sudah terisi
                }
            } 
            else 
            {
                System.out.println("Nomor baris atau kolom tidak valid. Silakan coba lagi.");
                i--; // Mengulang input untuk nomor baris atau kolom yang tidak valid
            }
        }
        System.out.println("Pilihan kursi Anda:");
        for (int i = 0; i < indexKursiTerpilih; i++) {
            System.out.println(kursiTerpilih[i]);
        }
    }
    static void PemilihanKursiStudioThePremiereNapoleon(){
        System.out.println("____________________________________________________");
        System.out.println("|Studio         |      LAYAR      |                |");
        System.out.println("|The Premiere   ===================                |");
        System.out.println("|                                                  |");
        for (int i = 0; i < 5; i++) {
            char barisHuruf = (char) ('A' + i);
            System.out.print("|      ");
            for (int j = 0; j < 5; j++) {
                System.out.print(" [" + StudioThePremiereNapoleon[i][j] + "]    ");
            }
            System.out.println("  |"  + barisHuruf + "|");
            System.out.println("|                                                  |");
        }
        System.out.println("|_______|1|_____|2|_____|3|_____|4|_____|5|________|");
        System.out.println("Silahkan memilih Kursi untuk Studio The Premiere: ");
        for (int i = 0; i < jumlahTiket; i++)
        {     
            System.out.println("Kursi ke-" + (i + 1) + ". ");
            System.out.print("Pilih kolom Baris (A-E) dan 0 untuk cancel: ");
            char barisHurufInput = inputUntukPilihKursi.next().charAt(0);
            int baris = barisHurufInput - 'A' + 1;
            System.out.print("Pilih kolom Kursi (1-5) dan 0 untuk cancel: ");
            int kolom = inputUntukPilihKursi.nextInt();

            if (barisHurufInput == '0' || kolom == 0) 
            {
                System.out.println("Pemilihan kursi dibatalkan.");
                break; // Jika pembatalan, keluar dari loop
            } 
            else if (baris >= 1 && baris <= 5 && kolom >= 1 && kolom <= 5) 
            {
                if (StudioThePremiereNapoleon[baris - 1][kolom - 1] == '0') 
                {
                    StudioThePremiereNapoleon[baris - 1][kolom - 1] = 'X'; // Menandai kursi sebagai terisi ('X')
                    kursiTerpilih[indexKursiTerpilih] = "Baris " + barisHurufInput + ", Kolom " + kolom;
                    indexKursiTerpilih++;
                    System.out.println("Anda telah memilih kursi Baris " + barisHurufInput + ", Kolom " + kolom);
                } 
                else 
                {
                    System.out.println("Kursi sudah terisi. Silakan pilih kursi yang lain.");
                    i--; // Mengulang input untuk kursi yang sudah terisi
                }
            } 
            else 
            {
                System.out.println("Nomor baris atau kolom tidak valid. Silakan coba lagi.");
                i--; // Mengulang input untuk nomor baris atau kolom yang tidak valid
            }
        }
        System.out.println("Pilihan kursi Anda:");
        for (int i = 0; i < indexKursiTerpilih; i++) {
            System.out.println(kursiTerpilih[i]);
        }
    }








    static void TempatSampah(){
        int totalHarga = 0; // Inisialisasi variabel totalHarga

        // Data master tipe studio
        String[] nama_studio = {"Deluxe", "Dolby Atmos", "IMAX", "The Premiere"};
        int[] harga_studio = {30000, 40000, 50000, 60000};

        // Data booking
        String[][] booking = new String[jdl_film.length][nama_studio.length];
        boolean loopSemesta = true;
        while (loopSemesta) {
            System.out.println("Menu:");
            System.out.println("1. Pilih Film");
            System.out.println("2. Pilih Studio");
            System.out.println("3. Pilih Kursi");
            System.out.println("4. Metode Pembayaran");
            System.out.println("5. Pelaporan Harian");
            System.out.println("6. Pelaporan Bulanan");
            System.out.println("7. Cetak Struk Booking");
            System.out.println("8. Cek Stok Film");
            System.out.println("9. Keluar");
            System.out.print("Pilihan Anda: ");
            int menu = input.nextInt();

            switch (menu) {
                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    // Pilih Kursi
                    if (jdlinput == -1 || studioInput == -1) {
                        System.out.println("Pilih film dan studio terlebih dahulu.");
                        break;
                    }
                    char[][] kursi = new char[5][5]; // Misalnya ada studio dengan 5 baris dan 5 kolom
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            kursi[i][j] = '0';
                        }
                    }
                    boolean lanjutPilihKursi = true;
                    while (lanjutPilihKursi) {
                        System.out.println("Status Kursi (X: Terisi, O: Kosong):");
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 5; j++) {
                                System.out.print(" | " + kursi[i][j] + " | ");
                            }
                            System.out.println();
                        }
                        System.out.print("Pilih baris (1-5) kursi atau ketik '0' untuk selesai: ");
                        int baris = input.nextInt();
                        System.out.print("Pilih kolom (1-5) kursi atau ketik '0' untuk selesai: ");
                        int kolom = input.nextInt();
                        if (baris == 0 || kolom == 0) {
                            lanjutPilihKursi = false;
                            System.out.println("Pemilihan kursi dibatalkan.");
                        } else if (baris >= 1 && baris <= 5 && kolom >= 1 && kolom <= 5) {
                            if (kursi[baris - 1][kolom - 1] == '0') {
                                kursi[baris - 1][kolom - 1] = 'X';
                                booking[jdlinput][studioInput] = "Baris " + baris + ", Kolom " + kolom;
                            } else {
                                System.out.println("Kursi sudah terisi.");
                            }
                        } else {
                            System.out.println("Nomor baris atau kolom tidak valid.");
                        }
                    }
                    break;

                case 4:
                    // Metode Pembayaran
                    if (jdlinput == -1 || studioInput == -1) {
                        System.out.println("Pilih film dan studio terlebih dahulu.");
                        break;
                    }
                    System.out.println("Film yang Anda pesan: " + jdl_film[jdlinput]);
                    System.out.println("Studio yang Anda pilih: " + nama_studio[studioInput]);
                    System.out.println("Kursi yang Anda pilih: " + booking[jdlinput][studioInput]);
                    //totalHarga = harga_film[jdlinput] + harga_studio[studioInput];
                    System.out.println("Harga: " + totalHarga);
                    System.out.print("Masukkan jumlah uang: ");
                    int uangPembayaran = input.nextInt();
                    if (uangPembayaran >= totalHarga) {
                        int kembalian = uangPembayaran - totalHarga;
                        System.out.println("Pembayaran berhasil. Kembalian: " + kembalian);
                    } else {
                        System.out.println("Pembayaran tidak mencukupi.");
                    }
                    break;

                case 5:
                    // Pelaporan Harian
                    System.out.println("Pelaporan Harian:");
                    // Coming Soon
                    break;

                case 6:
                    // Pelaporan Bulanan
                    System.out.println("Pelaporan Bulanan:");
                    // Coming Soon
                    break;

                case 7:
                    // Cetak Struk Booking
                    if (jdlinput == -1 || studioInput == -1) {
                        System.out.println("Pilih film, studio, dan kursi terlebih dahulu.");
                        break;
                    }
                    System.out.println("==== Struk Booking ====");
                    System.out.println("Film: " + jdl_film[jdlinput]);
                    System.out.println("Studio: " + nama_studio[studioInput]);
                    System.out.println("Kursi: " + booking[jdlinput][studioInput]);
                    System.out.println("Harga: " + totalHarga);
                    System.out.println("Terima kasih telah melakukan booking.");
                    break;

                case 8:
                    // Cek Stok Film
                    System.out.println("Cek Stok Film:");
                    for (int i = 0; i < jdl_film.length; i++) {
                        //System.out.println(jdl_film[i] + " (Stok: " + stok_film[i] + ")");
                    }
                    break;

                case 9:
                    System.out.println("Terima kasih telah menggunakan sistem booking tiket bioskop.");
                    loopSemesta = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    break;
            }
        }
    }
    /*static void PemilihanKursi(char[][] studio, String studioName) {
        System.out.println("Pemilihan Kursi " + studioName + ":");
        for (int i = 0; i < jumlahTiket; i++) {
            if (studioName.equals("DeluxeWish")) {
                PemilihanKursiStudioDeluxeWish();
            } else if (studioName.equals("IMAXTheMarvels")) {
                PemilihanKursiStudioIMAXTheMarvels();
            } else if (studioName.equals("DeluxeTheMarvels")) {
                PemilihanKursiStudioDeluxeTheMarvels();
            } else if (studioName.equals("ThePremiereNapoleon")) {
                PemilihanKursiStudioThePremiereNapoleon();
            }        
            System.out.println("Kursi ke-" + (i + 1) + ". ");
            System.out.print("Pilih kolom Baris (A-E) dan 0 untuk cancel: ");
            char barisHurufInput = inputUntukJdlFilm.next().charAt(0);
            int baris = barisHurufInput - 'A' + 1;
            System.out.print("Pilih kolom Kursi (1-5) dan 0 untuk cancel: ");
            int kolom = inputUntukJdlFilm.nextInt();

            if (barisHurufInput == '0' || kolom == 0) {
                System.out.println("Pemilihan kursi dibatalkan.");
                break; // Jika pembatalan, keluar dari loop
            } else if (baris >= 1 && baris <= 5 && kolom >= 1 && kolom <= 5) {
                if (studio[baris - 1][kolom - 1] == '0') {
                    studio[baris - 1][kolom - 1] = 'X'; // Menandai kursi sebagai terisi ('X')
                    kursiTerpilih[i] = (baris - 1) * 5 + (kolom - 1); // Menyimpan indeks kursi yang dipilih
                    System.out.println("Anda telah memilih kursi Baris " + barisHurufInput + ", Kolom " + kolom);
                } else {
                    System.out.println("Kursi sudah terisi. Silakan pilih kursi yang lain.");
                    i--; // Mengulang input untuk kursi yang sudah terisi
                }
            } else {
                System.out.println("Nomor baris atau kolom tidak valid. Silakan coba lagi.");
                i--; // Mengulang input untuk nomor baris atau kolom yang tidak valid
            }
        }
    }*/    
}
