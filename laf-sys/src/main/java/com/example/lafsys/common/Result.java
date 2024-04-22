package com.example.lafsys.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接口统一返回包装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private String code;
    private String msg;
    private Object data;

    // 没有数据的成功
    public static Result success(){
        return new Result(Constants.CODE_200,"",null);
    }

    // 有数据的成功
    public static Result success(Object data){
        return new Result(Constants.CODE_200,"",data);
    }

    //
    public static Result error(String code, String msg){
        return new Result(code,msg,null);
    }

    //
    public static Result error(){
        return new Result(Constants.CODE_500,"系统错误",null);
    }
}
