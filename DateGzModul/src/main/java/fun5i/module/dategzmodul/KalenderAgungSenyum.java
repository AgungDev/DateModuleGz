package fun5i.module.dategzmodul;

import android.util.Log;

import java.util.Arrays;
import java.util.Calendar;

public class KalenderAgungSenyum extends Hari {

    private static final String TAG = "KalenderAgungSenyum";
    private int CURRENT_DAY;
    private int CURRENT_MONTH;
    private int CURRENT_YEAR;
    private long CURRENT_JAM;


    public KalenderAgungSenyum(Calendar current){
        super(current);
        CURRENT_DAY = current.get(Calendar.DAY_OF_WEEK);
        CURRENT_MONTH = current.get(Calendar.MONTH);
        CURRENT_YEAR = current.get(Calendar.YEAR);
        CURRENT_JAM = current.get(Calendar.HOUR);


        Log.d(TAG, "KalenderAgungSenyum:m "+CURRENT_MONTH);
        Log.d(TAG, "KalenderAgungSenyum:d "+CURRENT_DAY);
        Log.d(TAG, "KalenderAgungSenyum:y "+CURRENT_YEAR);
    }

    private int conversiKePositif(int val){
        return 0-(val);
    }

}
