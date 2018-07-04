package th.ac.psu.kbwsite.kbw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Eval extends AppCompatActivity {

    EditText user,pass;
    Button btlog,btset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__eval);

        user = (EditText)findViewById(R.id.user);
        pass = (EditText)findViewById(R.id.pass);
        btlog = (Button)findViewById(R.id.btlog);
        btset = (Button)findViewById(R.id.btset);
        btlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user.getText().toString().equals("admin")&&pass.getText().toString().equals("1234")){
                    Intent serve = new Intent(Login_Eval.this, Evaluation.class);
                    startActivity(serve);
                }else {
                    Toast.makeText(getApplicationContext(),"รหัสผ่านไม่ถูกต้อง",Toast.LENGTH_LONG).show();
                }
            }
        });
        btset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setText("");
                pass.setText("");
            }
        });
    }
}
