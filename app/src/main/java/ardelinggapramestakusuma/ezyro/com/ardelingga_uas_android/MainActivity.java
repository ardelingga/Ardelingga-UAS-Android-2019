package ardelinggapramestakusuma.ezyro.com.ardelingga_uas_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView textHasilJSON;
    private EditText edtId;
    private RequestQueue mQueue;
    public int edtInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtId = findViewById(R.id.edtID);
        mQueue = Volley.newRequestQueue(this);
        textHasilJSON = findViewById(R.id.textJSON);
        Button tombolJson = findViewById(R.id.btnJSON);

        //edtInput = Integer.parseInt(String.valueOf(edtId.getText()))-1;

        tombolJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtInput = Integer.parseInt(String.valueOf(edtId.getText()))-1;
                uraiJson(edtInput);
            }
        });
    }
    private void uraiJson(final int id){
        String url = "http://192.168.5.24/Ardelingga_UAS_Android_JSON/json.json";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                        JSONObject data = response.getJSONObject(id);

                        String id = data.getString("id");
                        String nama= data.getString("nama");
                        String asal_daerah = data.getString("asal_daerah");
                        String kamar = data.getString("kamar");

                        textHasilJSON.append("ID = "+id+"\n"+"Nama = "+nama+"\n"+"Asal Daerah = "
                                +asal_daerah+"\n"+"Kamar = "+kamar+"\n\n");

                }catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
        mQueue.add(request);
    }
}
