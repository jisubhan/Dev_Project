package org.techtown.courage;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



public class Fragment1 extends Fragment {

    private List<UserItem> userItemList;
    private ListView userItemListView;
    private UserItemListAdapter adapter1;

    EditText mobile;
    EditText userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {




        //listView = (ListView) view.findViewById(R.id.listView);

        View view = inflater.inflate(R.layout.fragment1, container, false);
        userItemListView = (ListView) view.findViewById(R.id.listView);
        userItemList = new ArrayList<UserItem>();

        adapter1 = new UserItemListAdapter(getActivity().getApplicationContext(), userItemList, this);
        userItemListView.setAdapter(adapter1);

       userId = (EditText)view.findViewById(R.id.editText);
        mobile = (EditText)view.findViewById(R.id.editText2);

        Button aaddButton = (Button) view.findViewById(R.id.aaddbutton);

        aaddButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                Response.Listener<String> responseListener = new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                AlertDialog dialog = builder.setMessage("전화번호를 추가하였습니다!")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                AlertDialog dialog = builder.setMessage("읽기 실패.")
                                        .setNegativeButton("확인", null)
                                        .create();
                                dialog.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                String userID = userId.getText().toString();
                String Phone = mobile.getText().toString();
                bookrequest bookrequest = new bookrequest (userID, Phone, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(bookrequest);
            }
        });
        new BackgroundTask().execute();
        return view;
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            target = "http://jisub3054.cafe24.com/book.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }

        @Override
        public void onPostExecute(String result) {
            //userItemListView.setAdapter(adapter1);
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String userID, Phone;

                while (count < jsonArray.length()) {

                    JSONObject object = jsonArray.getJSONObject(count);

                    userID = object.getString("userID");
                    Phone = object.getString("Phone");

                    UserItem userItem = new UserItem(userID, Phone);
                    userItemList.add(userItem);
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }


}
