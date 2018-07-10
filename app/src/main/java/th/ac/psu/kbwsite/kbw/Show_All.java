package th.ac.psu.kbwsite.kbw;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Show_All extends AppCompatActivity {

    //Button bt = (Button)findViewById(R.id.back);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__all);

        Button bt = (Button)findViewById(R.id.back);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent in2 = new Intent(Show_All.this, Control_Main.class);
                //startActivity(in2);
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String obj = bundle.getString("data");
            /*int star = bundle.getInt("star");
            boolean isSmart = bundle.getBoolean("smart");*/
            //String result = String.format("Name is %s",name);
            //Toast.makeText(this, "Name : " + result, Toast.LENGTH_SHORT).show();
            String result = "";

            TextView textData1 = (TextView)findViewById(R.id.data1);
            TextView textData2 = (TextView)findViewById(R.id.data2);
            TextView textData3 = (TextView)findViewById(R.id.data3);
            TextView textData4 = (TextView)findViewById(R.id.data4);
            TextView textData5 = (TextView)findViewById(R.id.data5);
            TextView textData6 = (TextView)findViewById(R.id.data6);
            TextView textData7 = (TextView)findViewById(R.id.data7);

            String[] arr = obj.split(",");

            String r1 = "",r2="",r3="",r4="",r5="",r6="",r7="";
            for(int i=0; i<arr.length;i++){
                switch (i) {
                    case 1: // round
                        String[] arr3 = arr[i].split("=");
                        r3 = arr3[1]; break;
                    case 2: // name test
                        String[] arr4 = arr[i].split("=");
                        r4 = arr4[1]; break;
                    case 6: // subject
                        String[] arr2 = arr[i].split("=");
                        r2 = formatname(arr2[1]); break;
                    case 7: // result
                        String[] arr7 = arr[i].split("=");
                        r7 = formatresult(arr7[1]); break; //
                    case 8: // name
                        String[] arr1 = arr[i].split("=");
                        r1 = arr1[1]; break;
                    case 9: // score
                        String[] arr6 = arr[i].split("=");
                        r6 = arr6[1]; break; //
                    case 11: // date enter
                        String[] arr5 = arr[i].split("=");
                        r5 = dateThai(arr5[1]); break;
                }
            }
            textData1.setText(r1);
            textData2.setText(r2);
            textData3.setText(r3);
            textData4.setText(r4);
            textData5.setText(r5);
            textData6.setText(r6);
            textData7.setText(r7);

        }

    }
    public static String dateThai(String strDate)
    {
        String Months[] = {
                "ม.ค", "ก.พ", "มี.ค", "เม.ย",
                "พ.ค", "มิ.ย", "ก.ค", "ส.ค",
                "ก.ย", "ต.ค", "พ.ย", "ธ.ค"};
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        int year=0,month=0,day=0;
        try {
            String[] data = strDate.split(" ");
            Date date = df.parse(strDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            year = c.get(Calendar.YEAR);

            month = c.get(Calendar.MONTH);

            day = c.get(Calendar.DATE);

        } catch (ParseException e) {

// TODO Auto-generated catch block

            e.printStackTrace();
        }
        return String.format("%s %s %s", day,Months[month],year+543);

    }
    public static String formatname(String strName)
    {
        String Name = "";
            if(strName.equals("w")){
                Name = "Word";
            }else if(strName.equals("p")){
                Name = "Point";
            }else if(strName.equals("e")){
                Name = "Excel";
            }

        return Name;
    }
    public static String formatresult(String strResult)
    {
        String Name = "";
        if(strResult.equals("F")){
            Name = "ไม่ผ่าน";
        }else if(strResult.equals("P")){
            Name = "ผ่าน";
        }else if(strResult.equals("G")){
            Name = "ดี";
        }else if(strResult.equals("E")){
            Name = "ดีเยี่ยม";
        }

        return Name;

    }

}
