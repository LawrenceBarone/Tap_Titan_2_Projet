//package com.example.taptitan2projettp;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.android.volley.RequestQueue;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.Map;
//
//public class InviteTama extends AppCompatActivity {
//
//    ListView listView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_invite_tama);
//
//        listView = findViewById(R.id.listView);
//
//        getJSON("http://2orm.com/Tamago/test.php"); //change link en local ip:port/dossier php
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String selectedFromList = (listView.getItemAtPosition(position).toString());
//
//                Intent returnIntent = new Intent();
//                returnIntent.putExtra("result",selectedFromList);
//                setResult(Activity.RESULT_OK,returnIntent);
//                finish();
//            }
//        });
//
//    }
//
//    private void getJSON(final String urlWebService) {
//        /*
//         * As fetching the json string is a network operation
//         * And we cannot perform a network operation in main thread
//         * so we need an AsyncTask
//         * The constrains defined here are
//         * Void -> We are not passing anything
//         * Void -> Nothing at progress update as well
//         * String -> After completion it should return a string and it will be the json string
//         * */
//        class GetJSON extends AsyncTask<Void, Void, String> {
//
//            //this method will be called before execution
//            //you can display a progress bar or something
//            //so that user can understand that he should wait
//            //as network operation may take some time
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//            }
//
//            //this method will be called after execution
//            //so here we are displaying a toast with the json string
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
//                try {
//                    loadIntoListView(s);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            //in this method we are fetching the json string
//            @Override
//            protected String doInBackground(Void... voids) {
//                try {
//                    //creating a URL
//                    URL url = new URL(urlWebService);
//
//                    //Opening the URL using HttpURLConnection
//                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
//
//                    //StringBuilder object to read the string from the service
//                    StringBuilder sb = new StringBuilder();
//
//                    //We will use a buffered reader to read the string from service
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//
//                    //A simple string to read values from each line
//                    String json;
//
//                    //reading until we don't find null
//                    while ((json = bufferedReader.readLine()) != null) {
//
//                        //appending it to string builder
//                        sb.append(json + "\n");
//                    }
//
//                    //finally returning the read string
//                    return sb.toString().trim();
//                } catch (Exception e) {
//                    return null;
//                }
//            }
//        }
//
//        //creating asynctask object and executing it
//        GetJSON getJSON = new GetJSON();
//        getJSON.execute();
//    }
//
//    private void loadIntoListView(String json) throws JSONException {
//        //creating a json array from the json string
//        JSONArray jsonArray = new JSONArray(json);
//
//        //creating a string array for listview
//        String[] heroes = new String[jsonArray.length()];
//
//        //looping through all the elements in json array
//        for (int i = 0; i < jsonArray.length(); i++) {
//
//            //getting json object from the json array
//            JSONObject obj = jsonArray.getJSONObject(i);
//
//            //getting the name from the json object and putting it inside string array
//            heroes[i] = obj.getString("name");
//        }
//
//        //the array adapter to load data into list
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, heroes);
//
//        //attaching adapter to listview
//        listView.setAdapter(arrayAdapter);
//    }
//
//
//
//}
