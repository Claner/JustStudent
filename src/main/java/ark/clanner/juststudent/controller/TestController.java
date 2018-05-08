package ark.clanner.juststudent.controller;

import ark.clanner.juststudent.base.BaseController;
import ark.clanner.juststudent.base.Response;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Clanner on 2018/5/2.
 */
@RestController
@Scope("session")
public class TestController extends BaseController {

    @GetMapping("/test")
    public Response test(){
        return success("部署成功");
    }
}
