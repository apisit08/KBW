package th.ac.psu.kbwsite.kbw;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
                Intent in2 = new Intent(Show_All.this, Control_Main.class);
                startActivity(in2);
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

            TextView textData = (TextView)findViewById(R.id.tdata);

            String[] arr = obj.split(",");
            for(int i=0; i<arr.length;i++){
                if(i==0){
                    result += arr[i];
                }else{
                    result += "\n"+arr[i];
                }
            }
            textData.setText(result);

        }

    }

}
