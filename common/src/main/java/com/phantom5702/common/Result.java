package com.phantom5702.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    int code;
    String message;
    T data;

    public static Result createDefaultSuccess(){
        return new Result(200,"success",null);
    }
    public static Result createDefaultErrorMessage(String message) {
        return new Result(500,message,null);
    }
}
