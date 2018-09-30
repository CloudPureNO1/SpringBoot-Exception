package priv.wangsm.exception.globalexception.bean.info;

import lombok.Data;

@Data
public class ResultInfo<T> implements java.io.Serializable{
    private static final long serialVersionUID = -1L;
    private String resultCode;//结果状态
    private String resultmsg;//结果信息
    private T data;//结果详细内容
}
