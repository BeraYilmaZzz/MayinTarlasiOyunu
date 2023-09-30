import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row, col;

        System.out.println("MAYIN TARLASINA HOŞGELDİNİZ ");

        // FORM  == 7 == // Oluşturlacak olan matris boyutunu kullanıcıdan alma ( 0 dan büyük mü kontrolü yapılarak )
        do {
            System.out.print("Satır sayısını giriniz: ");
            row = scanner.nextInt();
            if(row<0) {
                System.out.println("Lütfen 0 ' dan büyük bir SATIR sayısı giriniz !");
            }
        } while (row < 1);

        do {
            System.out.print("Sütun sayısını giriniz: ");
            col = scanner.nextInt();
            if(col<0) {
                System.out.println("Lütfen 0 ' dan büyük bir SÜTUN sayısı giriniz !");
            }
        } while (col < 1);

        MineSweeper game = new MineSweeper(row, col);
        game.play();
        scanner.close();
    }
}
