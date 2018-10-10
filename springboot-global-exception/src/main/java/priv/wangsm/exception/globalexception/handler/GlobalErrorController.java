package priv.wangsm.exception.globalexception.handler;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义全局异常统一处理，主要处理HTTP状态请求的异常：比如404,500异常
 * 参照了：{@link org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController}
 * 参照了：{@link org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController}
 * @author 王源骏
 * @since 1.0.0
 */
@Controller
public class GlobalErrorController   implements ErrorController {
    private static final String ERROR_PATH="/error";  //错误拦截的路径,spirng boot 默认拦截这个路径,也可以在配置文件中重新配置路径



    @RequestMapping(value=ERROR_PATH)
    public String showErrorHtmlPage(HttpServletRequest request, HttpServletResponse response){


        HttpStatus status=null;
        //设置界面返回的信息：此处所有状态都在一个界面中处理，也可以建多个界面分页面处理，比如404,500等
        Map<String,Object> errorbody=new HashMap<String,Object>();
        Integer statusCode= (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode==null) {
            errorbody.put("error", "未知状态异常");
            errorbody.put("message", "发起请求的HTTP状态码为空");
        }else{
            try{
                status=HttpStatus.resolve(statusCode);
                response.setStatus(status.value());//获取状态码，设置response状态码
            }catch (Exception e){
                e.printStackTrace();
                errorbody.put("error", "HTTP状态码解析异常");
                errorbody.put("message",e.getMessage());
            }
            try {
                //取得枚举中定义的HTTP状态码对应的描述
                errorbody.put("error", HttpStatus.valueOf(statusCode).getReasonPhrase());
                errorbody.put("message", HttpStatus.valueOf(statusCode).getReasonPhrase());
            }catch (Exception ex) {
                ex.printStackTrace();
                errorbody.put("error","获取HTTP定义状态码描述发生未知异常");
                errorbody.put("message",ex.getMessage());
            }

        }

        errorbody.put("timestamp",new Date());//设置服务器时间
        errorbody.put("path",request.getRequestURI());//请求的路径
        errorbody.put("errorPath",request.getAttribute("javax.servlet.error.request_uri"));
        errorbody.put("httpStatusCode",status.value());

        request.setAttribute("errorBody",errorbody);
        return "/error/HttpStatusCode.html";

    }


    /**
     * 实现{@link ErrorController} 接口中的方法
     * @return  ERROR_PATH="/error" 错误拦截的路径
     */
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
