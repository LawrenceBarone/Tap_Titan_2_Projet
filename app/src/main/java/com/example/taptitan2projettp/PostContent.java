//package com.example.taptitan2projettp;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class PostContent extends AppCompatActivity {
//    EditText e1;
//    Button b1;
//    String s;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_post_content);
//
//        b1 = findViewById(R.id.saveOnline);
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                s = "e1.getText().toString()";
//                String url = "http://2orm.com/Tamago/testsend.php";
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                Toast.makeText(PostContent.this,response.trim(),Toast.LENGTH_LONG).show();
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                Toast.makeText(PostContent.this,error.toString(),Toast.LENGTH_LONG).show();
//                            }
//                        }){
//                    @Override
//                    protected Map<String, String> getParams() {
//                        Map<String,String> params = new HashMap<String, String>();
//                        params.put("username",s);
//
//                        return params;
//                    }
//                };
//                RequestQueue requestQueue = Volley.newRequestQueue(PostContent.this);
//                requestQueue.add(stringRequest);
//            }
//        });
//
//    }
//}