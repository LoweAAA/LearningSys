package learningsys.service;


import learningsys.repository.TestRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestSer {
    public List getList();
}
