package th.ac.psu.kbwsite.kbw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class IT_Service extends AppCompatActivity {
    EditText etGetfrom; // This will be a reference to our GitHub username input.
    Button btnGetSearch;  // This is a reference to the "Get Repos" button.
    TextView tvDetail,tvStatus;  // This will reference our repo list text box.
    RequestQueue requestQueue;  // This is our requests queue to process our HTTP requests.

    //string baseUrl = "http://kbwhub.psu.ac.th/its/getForm?doccode_budyear=0760%2F60";  // This is the API base URL (GitHub API)
    String baseUrl = "http://kbwhub.psu.ac.th/its/getForm?doccode_budyear=";
    String url;  // This will hold the full URL which will include the username entered in the etGitHubUser.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it__service);
        this.etGetfrom = (EditText) findViewById(R.id.no_from);  // Link our github user text box.
        this.btnGetSearch = (Button) findViewById(R.id.bt_search);  // Link our clicky button.
        this.tvDetail = (TextView) findViewById(R.id.show_from);  // Link our repository list text output box.
        this.tvStatus = (TextView)findViewById(R.id.show_status);
        this.tvDetail.setMovementMethod(new ScrollingMovementMethod());  // This makes our text box scrollable, for those big GitHub contributors with lots of repos :)

        requestQueue = Volley.newRequestQueue(this);  // This setups up a new request queue which we will need to make HTTP requests.
    }
    private void clearRepoList() {
        // This will clear the repo list (set it as a blank string).
        this.tvDetail.setText("");
    }

    private void addToRepoList(String fromNo, String fromY,String status) {
        // This will add a new repo to our list.
        // It combines the repoName and lastUpdated strings together.
        // And then adds them followed by a new line (\n\n make two new lines).
        String strRow = fromNo + " / " + fromY;
        String strRow2="";
        switch (status){
            case "0" :  strRow2 = "\t"+ "กำลังดำเนินการซ่อม"; break;
            case "1" :  strRow2 = "\t"+ "กำลังดำเนินการซ่อม"; break;
            case "2" :  strRow2 = "\t"+ "กำลังดำเนินการซ่อม"; break;
            case "3" :  strRow2 = "\t"+ "งานเสร็จ"; break;
            case "4" :  strRow2 = "\t"+ "งานเสร็จ"; break;
            case "5" :  strRow2 = "\t"+ "รับคืนแล้ว"; break;
            case "6" :  strRow2 = "\t"+ "รับคืนแล้ว"; break;
            case "7" :  strRow2 = "\t"+ "รออะไหล่"; break;
            case "99":  strRow2 = "\t"+ "ยังไม่ระบุ"; break;
            default: strRow2 = "\t"+ "ยังไม่ระบุ"; break;
        }
        /*if(status == "0" || status == "1" || status == "2"){
            strRow2 = "\t"+ "กำลังดำเนินการซ่อม";
        }else if(status == "3" || status == "4"){
            strRow2 = "\t"+ "งานเสร็จ";
        }else if(status == "5" || status == "6"){
            strRow2 = "\t"+ "รับคืนแล้ว";
        }else if(status == "7"){
            strRow2 = "\t"+ "รออะไหล่";
        }else {strRow2 = "\t"+ "ยังไม่ระบุ";}*/

        //String currentText = tvRepoList.getText().toString();
        this.tvDetail.setText("เลขฟอร์ม       : " + "\t" + strRow);
        this.tvStatus.setText("สถานะบริการ : " + "\t" + strRow2);
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
                                    String doccode = jsonObj.get("doccode").toString();
                                    String budyear = jsonObj.get("budyear").toString();
                                    String status = jsonObj.get("status").toString();
                                    addToRepoList(doccode, budyear,status);
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
}
