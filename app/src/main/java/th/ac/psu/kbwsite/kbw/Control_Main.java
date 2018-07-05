package th.ac.psu.kbwsite.kbw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
import java.util.ArrayList;
import java.util.HashMap;

public class Control_Main extends AppCompatActivity implements AdapterView.OnItemClickListener{
    public static String bURL = "http://psu-copt.psu.ac.th/practical/scores_json/";
    static String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control__main);
        Bundle bundle = getIntent().getExtras();
        String obj = bundle.getString("user");
        URL = bURL + obj;
        new SimpleTask().execute(URL);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        //Toast.makeText(MainActivity.this, "CLICK: " + item, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Control_Main.this, Show_All.class);
        intent.putExtra("data",item);
        startActivity(intent);
        finish();
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
            showData(jsonString);
        }
    }

    private void showData(String jsonString) {
        Post posts = new Post();
        final ListView lisView1 = (ListView) findViewById(R.id.listview);
        ArrayList<HashMap<String, String>> returnMyArrList;
        if(jsonString != null){
            try{

                returnMyArrList = GetDataList(jsonString);
                SimpleAdapter sAdap;
                sAdap = new SimpleAdapter(Control_Main.this, returnMyArrList, R.layout.post,
                        new String[]{"ID", "Name", "Score", "Result"}, new int[]{R.id.post_tno, R.id.post_tname, R.id.post_tscore, R.id.post_tresult});

                lisView1.setAdapter(sAdap);

                lisView1.setOnItemClickListener(this);

            }catch (final JSONException e){
                Toast.makeText(getApplicationContext(),"Json Parsing error", Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(getApplicationContext(),"Couldn't Json from server", Toast.LENGTH_LONG).show();
        }
    }

    public ArrayList<HashMap<String, String>> GetDataList(String jsonString) throws JSONException {
        Post posts = new Post();
        ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();

        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jsonArray = jsonObject.getJSONArray("score");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject c = jsonArray.getJSONObject(i);
            posts.setId(c.getString("id"));
            posts.setModuleNameAbbr(c.getString("moduleNameAbbr"));
            posts.setTotalScore(c.getString("totalScore"));
            posts.setExamResult(c.getString("examResult"));
            posts.setExamineeID(c.getString("examineeID"));
            posts.setExamineeAccount(c.getString("examineeAccount"));
            posts.setExamScheduleID(c.getString("examScheduleID"));
            posts.setIsEnterScore(c.getString("isEnterScore"));
            posts.setEnterScorePeople(c.getString("enterScorePeople"));
            posts.setEnterScoreDate(c.getString("enterScoreDate"));
            posts.setCreated_at(c.getString("created_at"));
            posts.setUpdated_at(c.getString("updated_at"));

            int no = i;
            no++;
            String tno = "" + no;
            HashMap<String, String> map;
            map = new HashMap<String, String>();
            map.put("No", posts.getId());
            map.put("ID", tno);
            map.put("Name", posts.getModuleNameAbbr());
            map.put("Score", posts.getTotalScore());
            map.put("Result", posts.getExamResult());
            map.put("examID", posts.getExamineeID());
            map.put("examAccount", posts.getExamineeAccount());
            map.put("examSche",posts.getExamScheduleID());
            map.put("Isenter",posts.getIsEnterScore());
            map.put("enterScoreD",posts.getEnterScoreDate());
            map.put("enterScoreP",posts.getEnterScorePeople());
            map.put("createA", posts.getCreated_at());
            map.put("upA", posts.getUpdated_at());
            MyArrList.add(map);
        }
        return MyArrList;
    }
}
