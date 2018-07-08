package learningsys.controller;

import learningsys.model.Test;
import learningsys.service.TestSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class testcontroller {

    @Autowired
    private TestSer testSer;

    @RequestMapping("/select")
    public List<Test> selectAll() {
        return testSer.getList();
    }

}
