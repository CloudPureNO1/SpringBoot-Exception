package priv.wangsm.exception.globalexception.bean.localException;

/**
 * @Description：要用于系统里抛出SysException异常
 * @Author wangsm
 * @Date 2018-09-20
 */
public class SysException extends Exception {
    private String code;//异常code
    public SysException(){
        super();
    }

    public SysException(String message){
        super(message);
    }
    public SysException(String code,String message){
        super(message);
        this.code=code;
    }

    public SysException(String message,Exception cause){
        super(message,cause);
    }

    public SysException(String code,String message,Exception cause){
        super(message,cause);
        this.code=code;
    }

    public String getCode(){
        return this.code;
    }

    public void setCode(String code){
        this.code=code;
    }
}
