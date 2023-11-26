import java.util.Scanner;
public class Kubus02 {
    public static int hitungVolume(int sisi) {
        int volume = sisi * sisi * sisi;
        return volume;
    }

    public static int hitungLuasPermukaan(int sisi) {
        int luasPermukaan = 6 * sisi * sisi;
        return luasPermukaan;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Masukkan panjang sisi kubus: ");
        int sisiKubus = input.nextInt();

        int volume = hitungVolume(sisiKubus);
        System.out.println("Volume kubus dengan sisi " + sisiKubus + " adalah: " + volume);

        int luasPermukaan = hitungLuasPermukaan(sisiKubus);
        System.out.println("Luas permukaan kubus dengan sisi " + sisiKubus + " adalah: " + luasPermukaan);

        input.close();
    }
}