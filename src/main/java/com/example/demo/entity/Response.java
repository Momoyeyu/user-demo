package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Response{
    private Integer code;
    private String errorMsg;
    private Object data;

    public static Response success(Object data) {
        return new Response(200, "success", data);
    }

    public static Response success() {
        return new Response(200, "success", null);
    }

    public static Response fail(Object data) {
        return new Response(404, "fail", data);
    }

    public static Response fail() {
        return new Response(404, "fail", null);
    }


}
