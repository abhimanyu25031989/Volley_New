package example.com.abhi.volleycallback;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.android.volley.VolleyError;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    IResult mResultCallback = null;
    VolleyService mVolleyService;
    private String password_to_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVolleyCallback();

        mVolleyService = new VolleyService(mResultCallback,this);
        mVolleyService.getDataVolley("GETCALL","http://staging.electrical.exchange/api/v2/brands");

        JSONObject sendObj = null;

        password_to_send = AppUtils.nextSessionId() + EleczoConstant.SALT_KEY + "123457";

        try {
            sendObj = new JSONObject();
            sendObj.put("mobile_number","9739736261");
            sendObj.put("password",password_to_send);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        mVolleyService.postDataVolley("POSTCALL", "http://testsell.eleczo.com/api-login", sendObj);
    }

    void initVolleyCallback() {
        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.i(TAG, "Volley requester " + requestType);
                Log.i(TAG, "Volley JSON post " + response);
            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.i(TAG, "Volley requester " + requestType);
                Log.i(TAG, "Volley JSON post " + "That didn't work!");
            }
        };

    }
}