package org.techtown.courage;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;



public class NoticeRequest extends StringRequest{
    final static private String URL ="http://jisub3054.cafe24.com/WriteRegister1.php";
    private Map<String, String> parameters;

    public NoticeRequest(String noticeConetnt, String noticeName, String noticeDate, Response.Listener<String> listener){

        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("noticeConetnt", noticeConetnt);
        parameters.put("noticeName", noticeName);
        parameters.put("noticeDate", noticeDate);

    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
