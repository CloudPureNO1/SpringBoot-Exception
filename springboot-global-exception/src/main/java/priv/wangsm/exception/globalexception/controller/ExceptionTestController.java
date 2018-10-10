package priv.wangsm.exception.globalexception.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.wangsm.exception.globalexception.bean.localException.AppException;
import priv.wangsm.exception.globalexception.bean.localException.SysException;
import priv.wangsm.exception.globalexception.service.ExceptionService;
import priv.wangsm.exception.globalexception.service.imp.ExceptionServiceImp;

@Controller
@RequestMapping("/exception")
public class ExceptionTestController {

    @Autowired
    private ExceptionService exceptionService;


    @GetMapping("/test")
    @ResponseBody
    public String exceptionTest() throws Exception {
        throw new Exception("测试异常");
    }

    @GetMapping("/msg")
    @ResponseBody
    public String showMsg(){

        int i=1/0;

        return "Hello,World!";
    }

    @GetMapping("/getMsg")
    @ResponseBody
    public int getMsg(){
        return  exceptionService.getResult(10);
    }

    @GetMapping("/appException")
    public String testException() throws AppException{
        throw new AppException("1005","AppException异常测试");
    }

    @GetMapping("/sysException")
    public String testSysException() throws SysException{
        throw new SysException("1001","测试SysException");
    }

}
