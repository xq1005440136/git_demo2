package com.xuecheng.framework.exception;


import com.xuecheng.framework.model.response.ResultCode;

/**
 * 抛异常
 */
public class ExceptionCast {



    public static  void cast(ResultCode resultCode){

     throw  new CustomException(resultCode);



    }
}
