package learningsystest;

import com.baidu.aip.nlp.AipNlp;
import learningsys.utils.AipNipClient;
import org.junit.Test;

public class AipTest {

    AipNipClient aipNipClient=new AipNipClient();

    @Test
    public void test(){
        String tt="百度是一家公司";
        System.out.println(aipNipClient.appartString(tt).toString());

    }
}
