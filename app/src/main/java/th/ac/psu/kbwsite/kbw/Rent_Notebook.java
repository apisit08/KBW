package th.ac.psu.kbwsite.kbw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Rent_Notebook extends AppCompatActivity {

    EditText etGetfrom; // This will be a reference to our GitHub username input.
    Button btnGetSearch,btadmin;  // This is a reference to the "Get Repos" button.
    TextView tvDetail,tvTime;  // This will reference our repo list text box.
    RequestQueue requestQueue;  // This is our requests queue to process our HTTP requests.

    //string baseUrl = "http://kbwhub.psu.ac.th/its/getForm?doccode_budyear=0760%2F60";  // This is the API base URL (GitHub API)
    String baseUrl = "http://nfr.cc.psu.ac.th/getdb_appointlap_ws.php?code=";
    String url;  // This will hold the full URL which will include the username entered in the etGitHubUser.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent__notebook);
        btadmin = (Button)findViewById(R.id.bt_admin);
        this.etGetfrom = (EditText) findViewById(R.id.no_from);  // Link our github user text box.
        this.btnGetSearch = (Button) findViewById(R.id.bt_search);  // Link our clicky button.
        this.tvDetail = (TextView) findViewById(R.id.show_from);  // Link our repository list text output box.
        this.tvTime = (TextView)findViewById(R.id.show_time);
        btadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(Rent_Notebook.this, Login_Rent.class);
                startActivity(login);
            }
        });
        this.tvDetail.setMovementMethod(new ScrollingMovementMethod());  // This makes our text box scrollable, for those big GitHub contributors with lots of repos :)

        requestQueue = Volley.newRequestQueue(this);  // This setups up a new request queue which we will need to make HTTP requests.
    }
    private void clearRepoList() {
        // This will clear the repo list (set it as a blank string).
        this.tvDetail.setText("");
    }

    private void addToRepoList(String nbcode, String appoint) {
        // This will add a new repo to our list.
        // It combines the repoName and lastUpdated strings together.
        // And then adds them followed by a new line (\n\n make two new lines).
        String[] arr = appoint.split(" ");
        for(String a : arr){

        }
        String date = arr[0];
        String time = arr[1];

        //String currentText = tvRepoList.getText().toString();
        this.tvDetail.setText("วันกำหนดส่งคืน : "+ "\t" + dateThai(date));
        this.tvTime.setText("ก่อนเวลา : "+ "\t"+ time + "\t" + "น.");

    }

    private void setRepoListText(String str) {
        // This is used for setting the text of our repo list box to a specific string.
        // We will use this to write a "No repos found" message if the user doens't have any.
        this.tvDetail.setText(str);
    }
    private void getRepoList(String username) {
        // First, we insert the username into the repo url.
        // The repo url is defined in GitHubs API docs (https://developer.github.com/v3/repos/).
        this.url = this.baseUrl + etGetfrom.getText().toString() ;

        // Next, we create a new JsonArrayRequest. This will use Volley to make a HTTP request
        // that expects a JSON Array Response.
        // To fully understand this, I'd recommend readng the office docs: https://developer.android.com/training/volley/index.html
        JsonArrayRequest arrReq = new JsonArrayRequest(Request.Method.GET, url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Check the length of our response (to see if the user has any repos)
                        if (response.length() > 0) {
                            // The user does have repos, so let's loop through them all.
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    // For each repo, add a new line to our repo list.
                                    JSONObject jsonObj = response.getJSONObject(i);
                                    String nbcode = jsonObj.get("nbCode").toString();
                                    String appointlap = jsonObj.get("appointlap").toString();

                                    addToRepoList(nbcode, appointlap);
                                } catch (JSONException e) {
                                    // If there is an error then output this to the logs.
                                    Log.e("Volley", "Invalid JSON Object.");
                                }

                            }
                        } else {
                            // The user didn't have any repos.
                            setRepoListText("No from found.");
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // If there a HTTP error then add a note to our repo list.
                        setRepoListText("Error while calling REST API");
                        Log.e("Volley", error.toString());
                    }
                }
        );
        // Add the request we just defined to our request queue.
        // The request queue will automatically handle the request as soon as it can.
        requestQueue.add(arrReq);
    }
    public void getReposClicked(View v) {
        // Clear the repo list (so we have a fresh screen to add to)
        clearRepoList();
        // Call our getRepoList() function that is defined above and pass in the
        // text which has been entered into the etGitHubUser text input field.
        getRepoList(etGetfrom.getText().toString());
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
}
