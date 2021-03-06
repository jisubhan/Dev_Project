package org.techtown.courage;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class Parser extends AsyncTask<Void, Integer, Integer>{

    Context c;
    ListView lv;
    String data;
    ProgressDialog pd;

    ArrayList<String> players =new ArrayList<>();

    public Parser(Context c, String data, ListView lv) {
        this.c = c;
        this.data = data;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("Parser");
        pd.setMessage("Parser... Please wait");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return this.parse();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        if(integer == 1){
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(c, android.R.layout.simple_list_item_1, players);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Snackbar.make(view, players.get(position), Snackbar.LENGTH_SHORT).show();
                }
            });

        }else{
            Toast.makeText(c,"Unable to Parse",Toast.LENGTH_SHORT).show();
        }
        pd.dismiss();
    }

    //PARSE RECEIVED DATA
    private int parse(){
        try{

            //ADD THAT DATA TO JSON ARRAY FIRST
            JSONArray ja = new JSONArray(data);
            //CEREATE JO OBJ TO HOLD A SINGLE ITEM
            JSONObject jo = null;


            players.clear();
            //LOOP THRU ARRAY
            for(int i=0; i<ja.length(); i++){
                jo=ja.getJSONObject(i);
                String name=jo.getString("Name");
                //ADD IT TO OUR ARRAYLIST
                players.add(name);
            }

            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
