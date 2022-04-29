package fun5i.module.dategzmodul;

import android.util.Log;

import java.util.Calendar;

public class Hari extends Bulan {
    private static final String TAG = "Hari";

    public static final int SENIN    = 1;
    public static final int SELASA   = 2;
    public static final int RABU     = 3;
    public static final int KAMIS    = 4;
    public static final int JUMAAT   = 5;
    public static final int SABTU    = 6;
    public static final int MINGGU   = 7;

    private String[] HARI_TXT = {
            "SENIN",
            "SELASA",
            "RABU",
            "KAMIS",
            "JUMAAT",
            "SABTU",
            "MINGGU"
    };

    private int DEFAULT_DAY;
    private int DAY_OF_MOTNH;



    public Hari(Calendar current) {
        super(current);
        DEFAULT_DAY = convertCalender( current.get(Calendar.DAY_OF_WEEK) );
        DAY_OF_MOTNH = current.get(Calendar.DAY_OF_MONTH);
    }



    /**
     * @return Tanggal Hari
     */
    public int tanggal(){
        return DAY_OF_MOTNH;
    }

    private int convertCalender(int fromCalender){
        int trueDay;
        if (fromCalender == 1){
            trueDay = 7; //minggu
        }else{
            trueDay = fromCalender-1; // senin - sabtu ; 1-6
        }
        return trueDay;
    }

    /**
     * <div style="color:#FFD82A2A; font-size: 120%;">PERBAIKI NANTI</div>
     * @return urutan(senin())
     */
    public int getSenin(){
        int tgl = tanggal() - urutanHari()+1; // tgl-minggu; 23-(6+1) = 18
        if (tgl <= 0){
            tgl += (-1);// -tgl hari adalah senin di bulan sebelumnya -1+(-1) = -2
        }
        return tgl;
    }

    /**
     * Get Minggu in current tanggal()
     * Jika tanggal 16 atau 30 pada bulan januari(31), hari kamis
     * @see #tanggal()
     * @see #jumlahHariOfMonth()
     * @see #urutanHari()
     * @return 19, or -2
     */
    public int getMinggu(boolean nextMount){
        int minggu = tanggal();
        if (urutanHari() < MINGGU){
            int besar = MINGGU - urutanHari();
            minggu = tanggal()+besar;
            if (minggu > jumlahHariOfMonth()){
                minggu = (jumlahHariOfMonth()-minggu);
                if (nextMount){
                    minggu = ~(minggu - 1);
                }
            }
        }
        return minggu;
    }

    /**
     * Get Minggu in current tanggal()
     * Jika tanggal 16 atau 30 pada bulan januari(31), hari kamis
     * @see #tanggal()
     * @see #jumlahHariOfMonth()
     * @see #urutanHari()
     * @return 19, or -2
     */
    public int getMinggu(){
        int minggu = tanggal();
        if (urutanHari() < MINGGU){
            int besar = MINGGU - urutanHari();
            minggu = tanggal()+besar;
            if (minggu > jumlahHariOfMonth()){
                minggu = (jumlahHariOfMonth()-minggu);
            }
        }
        return minggu;
    }

    public int[] tanggalMingguIni(){
        int[] tanggal = new int[7];
        int start = getSenin();
        int newAngka = 0; //
        for (int i=1; i<=7; i++){
            if (start >= 0){
                //bilangan bulat positif
                start += 1; // 17 + 1
                if (start >= jumlahHariOfMonth()+2){
                    // 30, 31, 1, 2, 3 etc..
                    newAngka += 1; // 0 + 1
                    tanggal[i-1] = newAngka;
                }else{
                    tanggal[i-1] = start-1;
                }
            }else{
                tanggal[i-1] = start;
                start += 1;//incement start
                if (start == 0)
                    start +=1;//skip 0
            }

        }
        return tanggal;
    }

    public int[] tanggalMingguIni(boolean lastAndNextMonth){
        int[] tanggal = new int[7];
        int start = getSenin();
        int newAngka = 0; //
        for (int i=1; i<=7; i++){
            if (start >= 0){
                //bilangan bulat positif
                start += 1; // 17 + 1
                if (start >= jumlahHariOfMonth()+2){
                    // 30, 31, 1, 2, 3 etc..
                    newAngka += 1; // 0 + 1
                    tanggal[i-1] = newAngka;
                }else{
                    tanggal[i-1] = start-1;
                }
            }else{
                //lastMonth
                if (lastAndNextMonth){
                    if (urutanBulan() > 1){
                        tanggal[i-1] = 1+(jumlahHariOfMonth(urutanHari()-1)+start);
                    }else if (urutanBulan() == 1){
                        tanggal[i-1] = jumlahHariOfMonth(DESEMBER)+start;
                    }
                }
                start += 1;//incement start
                if (start == 0)
                    start +=1;//skip 0
            }

        }
        return tanggal;
    }

    /**
     * @return 1-7
     */
    public int urutanHari(){
        return DEFAULT_DAY;
    }

    public String getDayText(){
        return HARI_TXT[DEFAULT_DAY-1];// karna array, makanya di kurang 1
    }

    public String getDayText(int day){
        return HARI_TXT[day-1];// karna array, makanya di kurang 1
    }
}