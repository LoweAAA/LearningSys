package learningsystest;

import learningsys.utils.Upload;
import org.junit.Test;

public class UploadTest {

    Upload upload = new Upload();

    @Test
    public void testDelete() {
        upload.delete("FpBXvn9-2Xpr3hgxm3CQRRc_-RyV");
    }

    @Test
    public void testString() {
        String url = "http://owmhe4011.bkt.clouddn.com/lnkZGgQpJU-5WANHkDwzIyC0yISj";
        System.out.println(url.substring(33, url.length()));
    }
}
