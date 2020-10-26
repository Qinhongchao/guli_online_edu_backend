package com.atguigu.commomutils;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

//统一返回结果对象
@Data
public class ResponseBean {

    @ApiModelProperty(value="是否成功")
    private Boolean success;

    @ApiModelProperty(value="返回码")
    private Integer code;

    @ApiModelProperty(value="返回消息")
    private String message;

    @ApiModelProperty(value="返回数据")
    private Map<String,Object> data=new HashMap<String,Object>();

    private ResponseBean(){}

    public static ResponseBean ok(){
        ResponseBean r = new ResponseBean();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    public static ResponseBean error(){
        ResponseBean r = new ResponseBean();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public ResponseBean success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public ResponseBean message(String message){
        this.setMessage(message);
        return this;
    }

    public ResponseBean code(Integer code){
        this.setCode(code);
        return this;
    }

    public ResponseBean data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public ResponseBean data(Map<String, Object> map){
        this.setData(map);
        return this;
    }



}
