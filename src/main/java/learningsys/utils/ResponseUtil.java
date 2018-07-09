package learningsys.utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil extends HashMap {

    public static ResponseUtil error(String message) {
        ResponseUtil responseUtil = new ResponseUtil();
        responseUtil.put("status", 201);
        responseUtil.put("message", message);
        return responseUtil;
    }

    public static ResponseUtil success(Map<String, Object> map) {
        ResponseUtil r = ResponseUtil.success();
        r.putAll(map);
        return r;
    }

    public static ResponseUtil success() {
        ResponseUtil responseUtil = new ResponseUtil();
        responseUtil.put("status", 200);
        return responseUtil;
    }

    public static ResponseUtil success(String message) {
        ResponseUtil responseUtil = new ResponseUtil();
        responseUtil.put("status", 200);
        responseUtil.put("message",message);
        return responseUtil;
    }

    public ResponseUtil put(String key, Object value) {
        super.put(key, value);
        return ResponseUtil.this;
    }
}
