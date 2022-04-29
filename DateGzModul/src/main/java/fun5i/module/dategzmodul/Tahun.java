package fun5i.module.dategzmodul;

import java.util.Calendar;
import java.util.Date;

public class Tahun{

    /**
     * Jumlah tahun dalam 1 tahun
     */
    private static final int TAHUN_BIASA = 365;

    /**
     * “Setiap 400 tahun sekali harus ada 3 hari yang dihilangkan”.
     *  Tiga hari yang dipilih itu adalah hari kabisat (29 Februari)
     *  pada tahun ke 100, 200 dan 300 dari tiap 400 tahun itu.
     *
     *  <b>+1 di bulan febuari, setiap 4 tahun</b>
     */
    private static final int TAHUN_KABISAT = 366;

    /**
     * Tahun ini secara default sudah di setting di constructor, atau sub class yang meng extends
     * class tahun
     *
     * @see #Tahun(Calendar)
     * @see #lihatTahun()
     */
    private int curentTahun;

    /**
     * <div style="font-size: 120%;">Constructor Class Tahun</div>
     * Karna ini adalah induk dari kelas bulan, dan hari, jadi tidak perlu memanggil method ini
     * karena class ini adalah super class dari Bulan dan class hari.
     * @param tahun
     */
    public Tahun(Calendar tahun){
        curentTahun = tahun.get(Calendar.YEAR);
    }

    /**
     * Jika Tahun ini adalah 2022
     * @return 2022
     */
    public int lihatTahun(){
        return curentTahun;
    }

    /**
     * Jika ini tahun 2021, maka <code>2021/4 = 505,25 modulus 1 adalah 0.25 == 0.0;</code>
     * yang artinya 2021 bukanlah tahun kabisat
     * Jika ini tahun 2024, maka <code>2024/4 = 506 modulus 1 adalah 0.0</code>,
     * yups ini tahun kabisat
     * @return true or false
     */
    public boolean getKabisat(){
        return (((float) curentTahun/4)%1 == 0.0)?true:false;
    }


}