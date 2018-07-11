package learningsys.utils;

import com.baidu.aip.nlp.AipNlp;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AipNipClient {
    public static final String APP_ID = "11520213";
    public static final String API_KEY = "uLAEDHdQqd1AAZrS3xtgcGw7";
    public static final String SECRET_KEY = "UvSoAL4zHXyLaZyHORQRBRik0zX1U27N";


    public List<String>  appartString(String sentence){

        AipNlp client=new AipNlp(APP_ID, API_KEY, SECRET_KEY);
        List<String> result=new ArrayList<String>();
        JSONArray res=client.lexer(sentence, null).getJSONArray("items");
        for(int i=0;i<res.length();i++){
            result.add(res.getJSONObject(i).getString("item"));
        }
        return result;
    }
}
