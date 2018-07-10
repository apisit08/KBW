package th.ac.psu.kbwsite.kbw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Show_All_Menu extends AppCompatActivity {

    Button btnew,btold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__all__menu);
        Bundle bundle = getIntent().getExtras();
        final String obj = bundle.getString("user");

        btnew = findViewById(R.id.bt_new);
        btnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Show_All_Menu.this, Control_Main.class);
                intent.putExtra("user",obj);
                startActivity(intent);
            }
        });
    }
}
