import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random random = new Random();
    // uzaklık matrisinin oluşturulması
    public static Double[][] uzaklikMatrisi(Random random) {
        Double[][] matris = new Double[40][40];

        for (int i = 0; i < 40; ++i) {
            for (int j = 0; j < 40; ++j) {
                if (i == j) {
                    matris[i][j] = 0.0;
                } else if (j > i) {
                    matris[i][j] = random.nextDouble(0, 10);
                    matris[j][i]=matris[i][j];
                }
            }
        }
        return matris;
    }
    // Yolcuları yerleştirme methodu
    public static void yolculariYerlestir(ArrayList<String> yolcular, Double[][] matris){
        String[][] yerlesmisYolcular = new String[10][4];
        int[] yerlesmisIndeksler = new int[40];
        Double[][] toplamUzakliklar = new Double[10][4];

        for (int i=0;i<10;++i){
            for (int j=0; j<4;++j){
                if (i==0 & j==0){ // İlk yolcuyu yerleştirme
                    int ilkSira = random.nextInt(40);
                    yerlesmisYolcular[i][j]=yolcular.get(ilkSira);
                    yerlesmisIndeksler[j]=ilkSira;
                    toplamUzakliklar[i][j]=0.0;
                }
                else if (i==0 & (j==1 || j==2 || j==3)){ // İlk dört sıradaki yolcuyu yerleştirme
                    double[] uzakliklar = new double[40];
                    for (int a=0;a<40;++a){
                        for (int b=0;b<j;++b){
                            if (matris[yerlesmisIndeksler[j-1]][a]==0 || yerlesmisIndeksler[b]==a){
                                uzakliklar[a]=11;
                            }
                            else if (uzakliklar[a]==0){
                                uzakliklar[a]=matris[yerlesmisIndeksler[j-1]][a];
                            }
                        }
                    }
                    double min=100;
                    int index=0;
                    for (int x=0; x<40;++x){
                        if (uzakliklar[x]<min){
                            min=uzakliklar[x];
                            index=x;
                        }
                    }
                    yerlesmisYolcular[i][j]=yolcular.get(index);
                    yerlesmisIndeksler[j]=index;
                    toplamUzakliklar[i][j]=min;

                }
                if (i!=0 & j%4==0){
                    double[] uzakliklar1= new double[40]; // Birinci yolcunun altındaki yolcuları yerleştirme kısmı
                    double[] uzakliklar2= new double[40];// Üzerinde gezindiğim matrisin ilgili satırını listeye atarak işlem yapmayı kolaylaştırıyorum
                    for (int a=0; a<40;++a) {
                        for (int b = 0; b <4*i; ++b) {
                            if (matris[yerlesmisIndeksler[4*i-4]][a]==0 || yerlesmisIndeksler[b]==a){
                                uzakliklar1[a]=11;
                            }
                            else if (uzakliklar1[a]==0){
                                uzakliklar1[a]=matris[yerlesmisIndeksler[4*i-4]][a];
                            }
                            if (matris[yerlesmisIndeksler[4*i-3]][a]==0 || yerlesmisIndeksler[b]==a){
                                uzakliklar2[a]=11;
                            }
                            else if (uzakliklar2[a]==0){
                                uzakliklar2[a]=matris[yerlesmisIndeksler[4*i-3]][a];
                            }
                        }
                    }
                    double minDeger=100;
                    int index=0;
                    for (int x=0; x<40;++x){
                        if ((uzakliklar1[x]+uzakliklar2[x])<minDeger){
                            minDeger=uzakliklar1[x]+uzakliklar2[x];
                            index=x;
                        }
                    }
                    yerlesmisYolcular[i][j]= yolcular.get(index);
                    yerlesmisIndeksler[4*i]=index;
                    toplamUzakliklar[i][j]=minDeger;

                }
                else if (i!=0 & j%4==1){ // İkinci yolcunun altındaki yolcuları yerleştirme kısmı
                    double[] uzakliklar1 = new double[40];
                    double[] uzakliklar2 = new double[40];
                    double[] uzakliklar3 = new double[40];
                    double[] uzakliklar5 = new double[40];

                    for (int a=0; a<40;++a){
                        for (int b=0; b<4*i+1;++b){
                            if (matris[yerlesmisIndeksler[4*i-4]][a]==0 || yerlesmisIndeksler[b]==a){
                                uzakliklar1[a]=11;
                            }
                            else if (uzakliklar1[a]==0){
                                uzakliklar1[a]=matris[yerlesmisIndeksler[4*i-4]][a];
                            }
                            if (matris[yerlesmisIndeksler[4*i-3]][a]==0 || yerlesmisIndeksler[b]==a){
                                uzakliklar2[a]=11;
                            }
                            else if (uzakliklar2[a]==0){
                                uzakliklar2[a]=matris[yerlesmisIndeksler[4*i-3]][a];
                            }
                            if (matris[yerlesmisIndeksler[4*i-2]][a]==0 || yerlesmisIndeksler[b]==a){
                                uzakliklar3[a]=11;
                            }
                            else if (uzakliklar3[a]==0){
                                uzakliklar3[a]=matris[yerlesmisIndeksler[4*i-2]][a];
                            }
                            if (matris[yerlesmisIndeksler[4*i]][a]==0 || yerlesmisIndeksler[b]==a){
                                uzakliklar5[a]=11;
                            }
                            else if (uzakliklar5[a]==0){
                                uzakliklar5[a]=matris[yerlesmisIndeksler[4*i]][a];
                            }
                        }
                    }
                    double minDeger=100;
                    int index=0;
                    for (int x=0; x<40;++x){
                        if ((uzakliklar1[x] + uzakliklar2[x] + uzakliklar3[x] + uzakliklar5[x])<minDeger){
                            minDeger=uzakliklar1[x] + uzakliklar2[x] + uzakliklar3[x] + uzakliklar5[x];
                            index=x;
                        }
                    }
                    yerlesmisYolcular[i][j]= yolcular.get(index);
                    yerlesmisIndeksler[4*i+1]=index;
                    toplamUzakliklar[i][j]=minDeger;

                }
                else if (i!=0 & j%4==2){ // Üçüncü yolcunun altındaKİ yolcuları yerleştime kısmı
                    double[] uzakliklar2 = new double[40];
                    double[] uzakliklar3 = new double[40];
                    double[] uzakliklar4 = new double[40];
                    double[] uzakliklar6 = new double[40];
                    for (int a=0; a<40; ++a){
                        for (int b=0; b<4*i+2;++b){
                            if (matris[yerlesmisIndeksler[4*i-3]][a]==0 || yerlesmisIndeksler[b]==a){
                                uzakliklar2[a]=11;
                            }
                            else if (uzakliklar2[a]==0){
                                uzakliklar2[a]=matris[yerlesmisIndeksler[4*i-3]][a];
                            }
                            if (matris[yerlesmisIndeksler[4*i-2]][a]==0 || yerlesmisIndeksler[b]==a){
                                uzakliklar3[a]=11;
                            }
                            else if (uzakliklar3[a]==0){
                                uzakliklar3[a]=matris[yerlesmisIndeksler[4*i-2]][a];
                            }
                            if (matris[yerlesmisIndeksler[4*i-1]][a]==0 || yerlesmisIndeksler[b]==a){
                                uzakliklar4[a]=11;
                            }
                            else if (uzakliklar4[a]==0){
                                uzakliklar4[a]=matris[yerlesmisIndeksler[4*i-1]][a];
                            }
                            if (matris[yerlesmisIndeksler[4*i+1]][a]==0 || yerlesmisIndeksler[b]==a){
                                uzakliklar6[a]=11;
                            }
                            else if (uzakliklar6[a]==0){
                                uzakliklar6[a]=matris[yerlesmisIndeksler[4*i+1]][a];
                            }
                        }
                    }
                    double minDeger=100;
                    int index=0;
                    for (int x=0; x<40;++x){
                        if ((uzakliklar2[x]+uzakliklar3[x]+uzakliklar4[x]+uzakliklar6[x])<minDeger){
                            minDeger=uzakliklar2[x]+uzakliklar3[x]+uzakliklar4[x]+uzakliklar6[x];
                            index=x;
                        }
                    }
                    yerlesmisYolcular[i][j]= yolcular.get(index);
                    yerlesmisIndeksler[4*i+2]=index;
                    toplamUzakliklar[i][j]=minDeger;


                }else if (i!=0 & j%4==3){ // Dördüncü yolcunun altındaki yolcuları yerleştirme kısmı
                    double[] uzakliklar3 = new double[40];
                    double[] uzakliklar4 = new double[40];
                    double[] uzakliklar7 = new double[40];

                    for (int a=0;a<40;++a){
                        for (int b=0; b<4*i+3;++b){
                            if (matris[yerlesmisIndeksler[4*i-2]][a]==0 || yerlesmisIndeksler[b]==a){
                                uzakliklar3[a]=11;
                            }
                            else if (uzakliklar3[a]==0){
                                uzakliklar3[a]=matris[yerlesmisIndeksler[4*i-2]][a];
                            }
                            if (matris[yerlesmisIndeksler[4*i-1]][a]==0 || yerlesmisIndeksler[b]==a){
                                uzakliklar4[a]=11;
                            }
                            else if (uzakliklar4[a]==0){
                                uzakliklar4[a]=matris[yerlesmisIndeksler[4*i-1]][a];
                            }
                            if (matris[yerlesmisIndeksler[4*i+2]][a]==0 || yerlesmisIndeksler[b]==a){
                                uzakliklar7[a]=11;
                            }
                            else if (uzakliklar7[a]==0){
                                uzakliklar7[a]=matris[yerlesmisIndeksler[4*i+2]][a];
                            }
                        }
                    }
                    double minDeger=100;
                    int index=0;
                    for (int x=0; x<40; ++x){
                        if ((uzakliklar4[x]+uzakliklar3[x]+uzakliklar7[x])<minDeger){
                            minDeger=uzakliklar4[x]+uzakliklar3[x]+uzakliklar7[x];
                            index=x;
                        }
                    }
                    yerlesmisYolcular[i][j]= yolcular.get(index);
                    yerlesmisIndeksler[4*i+3]=index;
                    toplamUzakliklar[i][j]=minDeger;
                }
            }
        }
        // Yolcuları ekrana koltuk numaraları ile yazdırma kısmı
        System.out.println();
        System.out.println("Yerleşmiş yolcuların isimleri ve koltuk numaraları:");
        System.out.println();
        for(int i=0;i<10;++i){
            for (int j=0;j<4;++j){
                System.out.printf("%-2s %-25s",((j+1)+(4*i+1)-1),yerlesmisYolcular[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        // Yolcuların diğer yolculara olan uzaklığını yazdırma
        System.out.println("Yolcuların diğer yolculara olan uzaklığı:");
        System.out.println();
        for (int i=0;i<10;++i){
            for (int j=0;j<4;++j){
                System.out.printf("%-2s %-1s %-15s",((j+1)+(4*i+1)-1),":",String.format("%,.2f",toplamUzakliklar[i][j]));

            }
            System.out.println();
        }
        System.out.println();
        // Toplam uzaklığı yazdırma kısmı
        double toplam=0;
        for (int i=0;i<10;++i){
            for (int j=0;j<4;++j){
                toplam+=toplamUzakliklar[i][j];
            }
        }
        System.out.println("Toplam uzaklıklar: "+String.format("%,.2f",toplam ));
    }

    public static void main(String[] args) {
        Double[][] m =uzaklikMatrisi(random);
        ArrayList<String> yolcular = new ArrayList<>();

        /*for (int i=0;i<40;++i){
            for (int j=0; j<40;++j){
                System.out.print("|"+String.format("%,.2f",m[i][j])+" ");
            }
            System.out.println();
        }

         */

        // Dosya okuma ile yolcu listesi alma
        try {
            Scanner reader = new Scanner(new FileReader("yolcular.txt"));

            while (reader.hasNextLine()){
                yolcular.add(reader.nextLine());
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Dosya bulunumadı...");
        }
        yolculariYerlestir(yolcular,m);// Yolcuları yerleştir fonksiyonun çağrılması
    }
}