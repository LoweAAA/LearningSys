package learningsys.utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil extends HashMap {

    public static ResponseUtil error(String msg) {
        ResponseUtil responseUtil = new ResponseUtil();
        responseUtil.put("status", 201);
        responseUtil.put("msg", msg);
        return responseUtil;
    }

    public static ResponseUtil error(Integer status, String msg) {
        ResponseUtil responseUtil = new ResponseUtil();
        responseUtil.put("status", status);
        responseUtil.put("msg", msg);
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

    public static ResponseUtil success(String msg) {
        ResponseUtil responseUtil = new ResponseUtil();
        responseUtil.put("status", 200);
        responseUtil.put("msg", msg);
        return responseUtil;
    }

    public ResponseUtil put(String key, Object value) {
        super.put(key, value);
        return ResponseUtil.this;
    }
}
