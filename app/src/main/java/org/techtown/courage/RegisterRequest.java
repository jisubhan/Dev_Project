package org.techtown.courage;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;



public class RegisterRequest extends StringRequest {
    final static private String URL = "http://jisub3054.cafe24.com/UserRegister.php";
    private Map<String, String> parameters;

    public RegisterRequest(String userID, String userPassword, String userGender, String userRegion, String userEmail, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userGender", userGender);
        parameters.put("userRegion", userRegion);
        parameters.put("userEmail", userEmail);
    }
    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}