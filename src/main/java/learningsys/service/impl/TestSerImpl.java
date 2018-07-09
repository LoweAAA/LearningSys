package learningsys.service.impl;

import learningsys.entity.Test;
import learningsys.repository.TestRes;
import learningsys.service.TestSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestSerImpl implements TestSer {

    @Autowired
    private TestRes testRes;

    public List<Test> getList(){
        return  testRes.findAll();
    }
}
