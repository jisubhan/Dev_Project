package org.techtown.courage;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class bookrequest extends StringRequest {
    static private String URL = "http://jisub3054.cafe24.com/bookadd.php";
    private Map<String, String> parameters;

    public bookrequest(String userID, String  Phone, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("Phone", Phone);
    }



    @Override
    public Map<String, String> getParams(){

        return parameters;
    }
}
