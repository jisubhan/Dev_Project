package org.techtown.courage;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;



public class MiningRequest extends StringRequest {
    static private String URL = "http://jisub3054.cafe24.com/ResultAdd.php";
    private Map<String, String> parameters;

    public MiningRequest(String userID, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
    }
    @Override
    public Map<String, String> getParams(){

        return parameters;
    }
}
