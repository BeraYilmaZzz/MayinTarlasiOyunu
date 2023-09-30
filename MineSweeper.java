import java.util.Random;
import java.util.Scanner;

public class MineSweeper { // DEĞERLENDİRME FORMU == 5 ==
    int rowNumber;
    int colNumber;
    String[][] map;
    String[][] board;
    int size; // matrisin büyüklüğü
    boolean game = true;
    Random random = new Random();
    Scanner scan = new Scanner(System.in);

    MineSweeper(int rowNumber, int colNumber) { // Mayın tarlası nesnemizi tanımladık.
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.map = new String[rowNumber][colNumber];
        this.board = new String[rowNumber][colNumber];
        this.size = rowNumber * colNumber;
    }

    public void play() { // DEĞERLENDİRME FORMU == 6 == OYUN BAŞLATMA metodumuz.
        int success = 0;

        prepareGame();
        System.out.println("!!! SATIR ve SÜTUN 0'dan başlamaktadır !!! ");
        print(map);
        System.out.println("   - OYUN BAŞLADI - ");

        while (game) {
            print(board); // DEĞERLENDİRME FORMU == 11 == oyun devam ettikçe oyuncunun oynadığı board dizisi ekrana yazdırılacak.
            for (int i = 0; i < colNumber * 4; i++) { // satır genişliği kadar " = " ifadesini dizinin altına koymak için yazılmış bir kod
                System.out.print("=");
            }
            System.out.println();
            int selectedRow, selectedCol; // kullanıcının hamleleri için tanımlama

            do { // DEĞERLENDİRME FORMU == 9 == ve FORM == 10 ==  // Kullanıcıdan satır sütun oynamasını isteme ve oynanan değerin alanın içerisinde olup olmadığını kontrol etme
                System.out.print("Satır giriniz = ");
                selectedRow = scan.nextInt();
                if(selectedRow < 0 || selectedRow >= rowNumber){
                    System.out.println("Lütfen SATIR için 0 ve "+ (rowNumber-1 )+ " arasında bir değer giriniz !!!");
                }
            }while (selectedRow < 0 || selectedRow >= rowNumber);

            do {
                System.out.print("Sütun giriniz = ");
                selectedCol = scan.nextInt();
                if(selectedCol < 0 || selectedCol >= colNumber){
                    System.out.println("Lütfen Sütun için 0 ve "+(colNumber-1)+ " arasında bir değer giriniz !!!");
                }
            }while (selectedCol < 0 || selectedCol >= colNumber);

            if (map[selectedRow][selectedCol] == " * ") { // DEĞERLENDİRME FORMU == 13 == // bombaya denk geldiğinde oyun bitiyor.
                game = false;
                System.out.println(" -- GAME OVER --");
            }else{
                checkMine(selectedRow, selectedCol);
                success++;
                if (success == (size - (size / 4))) { // DEĞERLENDİRME FORMU == 14 == // success başarılı oynama ölçü birimimiz bombasız alan sayısına ulaştığında oyun kazanılıyor.
                    print(board);
                    System.out.println("KAZANDINIZ !!!");
                    break;
                }
            }
        }
    }
    private void checkMine(int r, int c) { // DEĞERLENDİRME FORMU == 12 == // Mayın kontrolü ve seçilen diziye 0 veya varsa etraftaki mayın sayısını yazdırma

        if (map[r][c] == " - ") {
            int mine = 0;

            if (c < colNumber - 1 && map[r][c + 1] == " * ") { // Sağ
                mine++;
            }
            if (r < rowNumber - 1 && map[r + 1][c] == " * ") { // Alt
                mine++;
            }
            if (r > 0 && map[r - 1][c] == " * ") { // Üst
                mine++;
            }
            if (c > 0 && map[r][c - 1] == " * ") { // Sol
                mine++;
            }
            if (c < colNumber - 1 && r > 0 && map[r - 1][c + 1] == " * ") { // Sol Üst
                mine++;
            }
            if (c < colNumber - 1 && r < rowNumber - 1 && map[r + 1][c + 1] == " * ") { // Sağ Alt
                mine++;
            }
            if (c > 0 && r > 0 && map[r - 1][c - 1] == " * ") { // Sol Alt
                mine++;
            }
            if (c > 0 && r < rowNumber - 1 && map[r + 1][c - 1] == " * ") { // Sağ Üst
                mine++;
            }
            board[r][c] = String.valueOf(" "+mine+" "); // FORM == 12 == // Etraftaki mayın sayısı kadar mine/mayın değerini ekrana bastırıyoruz.
        }
    }
    public void prepareGame() { // DEĞERLENDİRME FORMU == 8 ==  Rastgele mayın atama
        int randomRow, randomCol, count = 0;

        while (count != (size / 4)) {
            randomRow = random.nextInt(rowNumber);
            randomCol = random.nextInt(colNumber);
            if (map[randomRow][randomCol] != " * ") { // Rastgele seçilen satır sütunda mayın olan " * " ifadesi yok ise mayın ekle.
                map[randomRow][randomCol] = " * ";
                count++;
            }
        }
    }
    public void print(String [][] arr) { // Dizi yazdırmak için bir metot

        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[0].length; c++) {
                if(arr[r][c] == null){
                    arr[r][c] = " - ";
                }
                System.out.print(arr[r][c] + "  ");
            }
            System.out.println();
        }
    }
}