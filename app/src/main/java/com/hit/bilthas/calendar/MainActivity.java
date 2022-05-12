package com.hit.bilthas.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    private Button btn;
    private TextView shift;
    private CalendarView cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        btn = (Button) findViewById(R.id.button);
        shift = (TextView) findViewById(R.id.result);
        cal = (CalendarView) findViewById(R.id.calendarView);
//        btnClick();
        calClick();
    }

//    public void Welcome(View view){
//        Toast.makeText(this, "huanying", Toast.LENGTH_SHORT).show();
//    }

    public void btnClick(){
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "wula", Toast.LENGTH_SHORT).show();
//                shift.setText("今天是白班");
            }
        });
    }

    public void calClick(){

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {


            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                String str;
                month = month + 1;
                int state = getShift(year, month, dayOfMonth);
                switch(state){
                    case 0:
                        str = "今天是白班";
                        break;
                    case 1:
                        str = "今天是夜班";
                        break;
                    case 2:
                        str = "今天是下夜班";
                        break;
                    default:
                        str = "这部分还没实现";
                }
                shift.setText(str);
            }
        });
    }

    public int getShift(int year, int month, int dayOfMonth){
        int init_year = 2022;
        int init_month = 5;
        int init_day = 6;
        int state = 0; //默认大班
        int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (year == init_year) {
            if (month == init_month) {
                int diff = dayOfMonth - init_day;
                if (diff % 3 == 1) {
                    state = 1; //夜班
                }
                if (diff % 3 == 2) {
                    state = 2; //下夜班
                }
            }else if (month > init_month) {
                int diff = 25;
                for(int i = init_month; i < month-1; i++){
                    diff = diff + daysOfMonth[i];
                }
                diff = diff + dayOfMonth;
                if (diff % 3 == 1) {
                    state = 1; //夜班
                }
                if (diff % 3 == 2) {
                    state = 2; //下夜班
                }
            }else{
                state = 3;
            }
        }else{
            state = 3; //表示还未实现
        }

        return state;
    }
}