package com.feng.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mr.Feng
 * @date 11/30/2021 10:31 PM
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CommonResult<T> {
    private Integer id;
    private String msg;
    private T data;

    public CommonResult(Integer code, String msg){
        this(code, msg, null);
    }
}
