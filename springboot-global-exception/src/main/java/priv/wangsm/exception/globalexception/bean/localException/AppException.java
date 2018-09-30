package priv.wangsm.exception.globalexception.bean.localException;

public class AppException extends Exception {
    private String code;//异常code
    public AppException(){
        super();
    }
    public AppException(String message){
        super(message);
    }
    public AppException(String code,String message){
        super(message);
        this.code=code;
    }
    public AppException(String message,Exception cause){
        super(message,cause);
    }
    public AppException(String code,String message,Exception caulse){
        super(message,caulse);
        this.code=code;
    }
    @Override
    public String getMessage(){
        return super.getMessage();//Exception 继承自 Throwable
    }
    public String getCode(){
        return code;
    }
    public void setCode(String code){
        this.code=code;
    }
}
