package learningsys.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class Upload {

    private Auth auth = Auth.create("MJSiM19VwA8I62yT92vKk2nxIGjyc8FMjYfTSPsK", "reKiLulS_QCg2npf9VhGYse43Rth7VVHfMb9it21");
    private UploadManager uploadManager = new UploadManager();

    private String getUpToken() {
        return auth.uploadToken("hduzjh");
    }

    public String upload(File file) {
        try {
            // 调用put方法上传
            Response res = uploadManager.put(file, null, getUpToken());
            DefaultPutRet putRet = new Gson().fromJson(res.bodyString(), DefaultPutRet.class);
            return "http://owmhe4011.bkt.clouddn.com/" + putRet.hash;
        } catch (QiniuException e) {
            return null;
        }
    }

}
