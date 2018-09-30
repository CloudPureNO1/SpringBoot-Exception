package priv.wangsm.exception.globalexception.bean.info;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ExceptionClassMsg implements java.io.Serializable{
    private String className;//类名
    private String methodName;//方法名
    private Integer lineNumber;//行号
}
