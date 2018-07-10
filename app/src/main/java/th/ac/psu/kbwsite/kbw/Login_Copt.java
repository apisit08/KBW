package th.ac.psu.kbwsite.kbw;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Login_Copt extends AppCompatActivity {

    EditText user,pass;
    Button btlog,btset;

    String baseUrl = "http://psu-copt.psu.ac.th/psulogin/";
    String url;
    static  String session="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__copt);

        user = (EditText)findViewById(R.id.user);
        pass = (EditText)findViewById(R.id.pass);
        btlog = (Button)findViewById(R.id.btlog);
        btset = (Button)findViewById(R.id.btset);
        btlog.setOnClickListener(new View.OnClickListener() {
            @Override
            //public void onClick(View view) {
            public void onClick(View view) {
                session = user.getText().toString();
                url = baseUrl + user.getText().toString()+ "%7C" + pass.getText().toString();
                new SimpleTask().execute(url);
                    /*Intent serve = new Intent(Login_Copt.this, Control_Main.class);
                    startActivity(serve);*/
                
                    //Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();
                
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
    private class SimpleTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            // Create Show ProgressBar
        }

        protected String doInBackground(String... urls) {
            String result = "";
            try {

                HttpGet httpGet = new HttpGet(urls[0]);
                HttpClient client = new DefaultHttpClient();

                HttpResponse response = client.execute(httpGet);

                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode == 200) {
                    InputStream inputStream = response.getEntity().getContent();
                    BufferedReader reader = new BufferedReader
                            (new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result += line;
                    }
                }

            } catch (ClientProtocolException e) {

            } catch (IOException e) {

            }
            return result;

        }

        protected void onPostExecute(String jsonString) {
            // Dismiss ProgressBar
            //Toast.makeText(getApplicationContext(),jsonString,Toast.LENGTH_LONG).show();
                checkdata(jsonString);
        }
    }

    private void checkdata(String jsonString) {
        String status= "";
        String message= "";
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("login");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject c = jsonArray.getJSONObject(i);
                 status = c.getString("status");
                 message = c.getString("status_message");
            }
        if(status.equals("Y")){
            Intent intent = new Intent(Login_Copt.this,Show_All_Menu.class);
            intent.putExtra("user",session);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
        }
        } catch (JSONException e) {
        e.printStackTrace();
        }
    }

}
