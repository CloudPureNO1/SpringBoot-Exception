package priv.wangsm.exception.globalexception.service.imp;

import org.springframework.stereotype.Service;
import priv.wangsm.exception.globalexception.service.ExceptionService;

@Service
public class ExceptionServiceImp implements ExceptionService {
    @Override
    public int getResult(int i) {
        return i/0;
    }
}
