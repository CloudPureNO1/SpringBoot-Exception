package priv.wangsm.exception.globalexception.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import priv.wangsm.exception.globalexception.bean.constant.ExceptionConstant;
import priv.wangsm.exception.globalexception.bean.info.ExceptionClassMsg;
import priv.wangsm.exception.globalexception.bean.info.ResultInfo;
import priv.wangsm.exception.globalexception.bean.localException.AppException;
import priv.wangsm.exception.globalexception.bean.localException.SysException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice// 通过使用@ControllerAdvice定义统一的异常处理类，而不是在每个Controller中逐个定义。
public class GlobalExceptionHandler {
    @Autowired
    private ExceptionClassMsg classMsg;
    /**
     * 但抛出的异常为：SysException时，调用次全局异处理，自动跳转到ExceptionConstant.GLOBAL_EXCPTION_VIEW_ERROR界面显示
     * @param request
     * @param @AppException e
     * @return view
     */
    @ExceptionHandler(value=SysException.class)
    public ModelAndView globalViewException(HttpServletRequest request, SysException e){

        ModelAndView mv=new ModelAndView();
        mv.addObject("errorName","系统统一界面异常处理");
        mv.addObject("exception",e);
        mv.addObject("exceptionUrl",request.getRequestURI());
        mv.setViewName(ExceptionConstant.GLOBAL_EXCPTION_VIEW_ERROR);
        return mv;
    }

    /**
     * controller  AppException 异常时的信息
     * @param request
     * @param @doc  AppException
     * @return ResultInfo<T>json
     */
    @ExceptionHandler(AppException.class)
    @ResponseBody
    public ResultInfo<List>appJSONException(HttpServletRequest request,AppException e){
        List<ExceptionClassMsg> list=new ArrayList<ExceptionClassMsg>();
        ResultInfo<List> resultInto=new ResultInfo<List>();
        resultInto.setResultCode(e.getCode());
        resultInto.setResultmsg(e.getMessage());
        StackTraceElement[]statckTraceElements=e.getStackTrace();
        for(StackTraceElement st:statckTraceElements){
            if(!st.getClassName().contains(ExceptionConstant.MODULE_BASE_PACAGE)) continue;
            classMsg.setClassName(st.getClassName());
            classMsg.setMethodName(st.getMethodName());
            classMsg.setLineNumber(st.getLineNumber());
            list.add(classMsg);
        }

        resultInto.setData(list);
        return resultInto;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultInfo<List> jsonException(HttpServletRequest request,Exception e){
        e.printStackTrace();
        List<ExceptionClassMsg> list=new ArrayList<ExceptionClassMsg>();
        StackTraceElement[] stackTraceElements=e.getStackTrace();
        for(StackTraceElement st:stackTraceElements){
            if(!st.getClassName().startsWith(ExceptionConstant.MODULE_BASE_PACAGE)) continue;
            System.out.println(">>>>>>>>>>>>>>>>>>类名："+st.getClassName());
            System.out.println(">>>>>>>>>>>>>>>>>>方法："+st.getMethodName());
            System.out.println(">>>>>>>>>>>>>>>>>>行号："+st.getLineNumber());
            System.out.println(">>>>>>>>>>>>>>>>>>文件名："+st.getFileName());
            classMsg.setClassName(st.getClassName());
            classMsg.setMethodName(st.getMethodName());
            classMsg.setLineNumber(st.getLineNumber());
            list.add(classMsg);
        }
        ResultInfo<List>resultInfo=new ResultInfo<List>();
        resultInfo.setResultmsg("系统未明确定义全局异常："+e.getMessage());
        resultInfo.setData(list);
        return resultInfo;
    }
}
