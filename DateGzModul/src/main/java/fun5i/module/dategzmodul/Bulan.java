package fun5i.module.dategzmodul;

import java.util.Calendar;
import java.util.Date;

public class Bulan extends Tahun{
    private static final String TAG = "Bulan";

    private static final int JANUARI    = 1;
    private static final int FEBUARI    = 2;
    private static final int MARET      = 3;
    private static final int APRIL      = 4;
    private static final int MEI        = 5;
    private static final int JUNI       = 6;
    private static final int JULI       = 7;
    private static final int AGUSTUS    = 8;
    private static final int SEPETEMBER = 9;
    private static final int OKTOBER    = 10;
    private static final int NOVEMBER   = 11;
    public static final int DESEMBER   = 12;

    private static final int COUNT_JANUARI = 31;
    private int COUNT_FEBUARI = 28; // +1 if kabisat
    private static final int COUNT_MARET = 31;
    private static final int COUNT_APRIL = 30;
    private static final int COUNT_MEI = 31;
    private static final int COUNT_JUNI = 30;
    private static final int COUNT_JULI = 31;
    private static final int COUNT_AGUSTUS = 31;
    private static final int COUNT_SEPTEMBER = 30;
    private static final int COUNT_OKTOBER = 31;
    private static final int COUNT_NOVEMBER = 30;
    private static final int COUNT_DESEMBER = 31;

    private int[] mounts = {
            JANUARI,
            FEBUARI,
            MARET,
            APRIL,
            MEI,
            JUNI,
            JULI,
            AGUSTUS,
            SEPETEMBER,
            OKTOBER,
            NOVEMBER,
            DESEMBER
    };

    private int[] getCountBulans = {
            COUNT_JANUARI,
            COUNT_FEBUARI,
            COUNT_MARET,
            COUNT_APRIL,
            COUNT_MEI,
            COUNT_JUNI,
            COUNT_JULI,
            COUNT_AGUSTUS,
            COUNT_SEPTEMBER,
            COUNT_OKTOBER,
            COUNT_NOVEMBER,
            COUNT_DESEMBER
    };

    /**
     * [0 - 11]
     * @see #lihatBulan()
     */
    private final String[] BULAN_TXT = {
            "Januari",
            "Febuari",
            "Maret",
            "April",
            "Mei",
            "Juni",
            "Juli",
            "Agustus",
            "September",
            "Oktober",
            "November",
            "Desember"
    };

    /**
     * Bulan ini secara default sudah di setting di constructor, atau sub class yang meng extends
     * class Bulan <code>1=Januari</code> dan <code>12=Desember</code>
     *
     * @see #Bulan (Calendar)
     * @see #lihatBulan()
     * @see #urutanBulan()
     */
    private int curentBulan;
    private int curentHari;
    private int curentWeek;

    private Calendar curent;

    /**
     * <div style="font-size: 120%;">Constructor Class Bulan</div>
     * Karna ini adalah induk dari kelas Hari, dan KalenderAgungSenyum, jadi tidak perlu memanggil method ini
     * karena class ini adalah super class dari Hari dan class KalenderAgungSenyum.
     * @param bulan
     */
    public Bulan(Calendar bulan){
        super(bulan);
        curent = bulan;
        if (getKabisat()) { COUNT_FEBUARI +=1; }
        curentBulan = bulan.get(Calendar.MONTH)+1;
        curentHari = bulan.get(Calendar.DAY_OF_MONTH); // tanggal
        curentWeek = convertCalender(bulan.get(Calendar.DAY_OF_WEEK)); //1-7
    }

    public int[][] getMonthToArray(){
        Calendar c = Calendar.getInstance();
        c.set(curent.get(Calendar.YEAR), curent.get(Calendar.MONTH), 1);

        int tgl = c.get(Calendar.DAY_OF_MONTH);
        int week = convertCalender(c.get(Calendar.DAY_OF_WEEK));
        int start = tgl-week;
        int end = jumlahHariOfMonth();

        int[][] minggu = new int[5][7];
        for (int i=0; i<5; i++){
            for (int a=0; a<7; a++){
                minggu[i][a] = start;
                start += 1;
                if (start==0)
                    start += 1;//skip

                if (start > end)
                    start = a-6;
            }
        }

        return minggu;
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
     * BULAN_TXT adalah array, <code>curentBulan-1</code>
     * @see #curentBulan
     * @see #BULAN_TXT
     * @return Januari - Desember
     */
    public String lihatBulan(){
        return BULAN_TXT[curentBulan-1];
    }

    /**
     * @see #curentBulan
     * @return 1 - 12
     */
    public int urutanBulan(){
        return curentBulan;
    }

    /**
     * jika bulan adalah februari
     * @see #curentBulan
     * @see #getCountBulans
     * @return 28 atau 29
     */
    public int jumlahHariOfMonth(){
        return getCountBulans[curentBulan-1]; // -1 = array 0 sampai 11; april = 3
    }

    /**
     * jika bulan adalah februari
     * @see #curentBulan
     * @see #getCountBulans
     * @return 28 atau 29
     */
    public int jumlahHariOfMonth(int month){
        return getCountBulans[month-1]; // -1 = array 0 sampai 11; april = 3
    }

    public int bulanIni(){
        return mounts[curentBulan-1]; // -1 = array 0 sampai 11; april = 3
    }

    public String namaBulanIni(){
        return BULAN_TXT[curentBulan];  // -1 = array 0 sampai 11; april = 3
    }


}