/*package org.techtown.courage;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class AddRequest extends StringRequest {
    final static private String URL = "http://jisub3054.cafe24.com/CourseAdd.php";
    private Map<String, String> parameters;

    public AddRequest(String userID, String writeCat, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("writeCat", writeCat);
    }
    @Override
    public Map<String, String> getParams(){

        return parameters;
    }
}
*/