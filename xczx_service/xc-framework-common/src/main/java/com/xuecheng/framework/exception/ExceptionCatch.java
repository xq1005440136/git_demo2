package com.xuecheng.framework.exception;


import com.google.common.collect.ImmutableMap;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice // 控制器增强
public class ExceptionCatch {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionCatch.class);
    // 定义map，配置 异常类型的所对应的的错误代码；
    private static ImmutableMap<Class<? extends Throwable>, ResultCode> Exceptions;

    // 定义map的构建对象，去构建ImmutableMap；
    private static ImmutableMap.Builder<Class<? extends Throwable>, ResultCode> builder = ImmutableMap.builder();

    static {
        // 定义异常类型所对应的的错误代码；
    builder.put(HttpMessageNotReadableException.class, CommonCode.INVALID_PARAM);
    }

    @ExceptionHandler(Exception.class)// 捕获CustomException此类异常
    @ResponseBody
    public ResponseResult exception(Exception exception) {
        // 记录日志信息；
        logger.error("catch exception：{}", exception.getMessage());
        if(Exceptions==null){
            Exceptions=builder.build();// Exceptions 构建成功；
        }
        // 从Exceptions找出异常类型所对应的的错误代码；如果找到了就返回给用户，没找到就返回99999；
        ResultCode resultCode = Exceptions.get(exception.getClass());

        if (resultCode!=null){
            return new ResponseResult(resultCode);


        }else{
         // 返回99999；
            return new ResponseResult(CommonCode.SERVER_ERROR);
        }


    }
}
