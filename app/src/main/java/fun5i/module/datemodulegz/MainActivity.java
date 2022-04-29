package fun5i.module.datemodulegz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telecom.StatusHints;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import fun5i.module.dategzmodul.KalenderAgungSenyum;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText tgl, bln, thn;
    private TextView tv, crTv;
    private Button btn;
    private RadioGroup list_opsi;
    private TextView tvBln;

    KalenderAgungSenyum kalender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initt();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        kalender = new KalenderAgungSenyum(calendar);

       /* "Bulan "+kalender.namaBulanIni()+" Tanggal "+
                kalender.getTanggalHariIni()+" hari "+kalender.getDayText()+
                "["+kalender.getPosisiHariPadaMinggu()+"]";
         crTv.append("\nSenin minggu ini tanggal "+kalender.seninMingguIniTanggal());
        crTv.append("\nMinggu "+Arrays.toString(kalender.tanggalMingguIni()));
         */

        String ttt = "Tahun "+kalender.lihatTahun()+
                " Kabisat ? "+((kalender.getKabisat())?"Yups":"Nooo")+
                "\n";
        String bbb = "Bulan "+kalender.lihatBulan()+
                " urutan "+kalender.urutanBulan()+
                " Jumlah("+kalender.jumlahHariOfMonth()+")"+
                "\n";
        String hhh = "Tgl(" +kalender.tanggal()+")"+
                " Hari("+kalender.getDayText()+") "+
                " Senin("+kalender.getSenin()+") "+
                " Minggu("+kalender.getMinggu(false)+")"+
                "\n";
        crTv.setText(ttt);
        crTv.append(bbb);
        crTv.append(hhh);


        thn.setText("2022");
        tgl.setText("25");
        bln.setText("4");



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar.setTime(new Date(
                        Integer.parseInt(thn.getText().toString())-1900,
                        Integer.parseInt(bln.getText().toString())-1,
                        Integer.parseInt(tgl.getText().toString())
                ));
                kalender = new KalenderAgungSenyum(calendar);

                String ttt = "Tahun "+kalender.lihatTahun()+
                        " Kabisat ? "+((kalender.getKabisat())?"Yups":"Nooo")+
                        "\n";
                String bbb = "Bulan "+kalender.lihatBulan()+
                        " urutan "+kalender.urutanBulan()+
                        " Jumlah("+kalender.jumlahHariOfMonth()+")"+
                        "\n";

                tv.setText(ttt);
                tv.append(bbb);


            }
        });

        list_opsi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //
                switch (checkedId){
                    case R.id.hari:
                        String hhh = "Tgl(" +kalender.tanggal()+")"+
                                " Hari("+kalender.getDayText()+") "+
                                " Senin("+kalender.getSenin()+") "+
                                " Minggu("+kalender.getMinggu(true)+")"+
                                "\n";
                        tvBln.setText(hhh);
                        break;
                    case R.id.minggu:
                        tvBln.setText("Sen, Sel, Rab, Kam, Jum, Sab, Min");
                        tvBln.append("\n");
                        for (int i2 = 0; i2<7; i2++){
                            int out = kalender.tanggalMingguIni(true)[i2];
                            if (out>9){
                                tvBln.append(
                                        "\t"+out+"\t\t"
                                );
                            }else{
                                tvBln.append(
                                        "\t"+out+"\t\t\t"
                                );
                            }
                        }
                        break;
                    case R.id.bulan:
                        int[][] dB = kalender.getMonthToArray();
                        tvBln.setText("Sen, Sel, Rab, Kam, Jum, Sab, Min");
                        for (int i=0; i<5; i++){
                            tvBln.append("\n");
                            for (int i2 = 0; i2<7; i2++){
                                int out = dB[i][i2];
                                if (out>9){
                                    tvBln.append(
                                            "\t"+out+"\t\t"
                                    );
                                }else{
                                    tvBln.append(
                                            "\t"+out+"\t\t\t"
                                    );
                                }

                            }
                        }
                        break;
                }
            }
        });
    }

    private void initt(){
        tgl = findViewById(R.id.tgl);
        bln = findViewById(R.id.bln);
        thn = findViewById(R.id.thn);
        tv = findViewById(R.id.tv);
        tvBln = findViewById(R.id.tvBln);
        crTv = findViewById(R.id.current);
        list_opsi = findViewById(R.id.opsi);
        btn = findViewById(R.id.btn);
    }

}