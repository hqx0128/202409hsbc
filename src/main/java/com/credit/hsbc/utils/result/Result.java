package com.credit.hsbc.utils.result;

import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Locale;

/**
 * 操作消息提醒 统一返回封装
 * @param <T>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("all")
public class Result<T> implements Serializable {
    /**
     * 当前请求id
     */
    private String requestId= IdUtil.objectId().toUpperCase(Locale.CHINA);

    /**
     * 系统时间
     */

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8", shape =
            JsonFormat.Shape.STRING)
    private LocalDateTime systemTime =LocalDateTime.now();

    /**
     * 响应码
     * 000：成功  001：失败
     */

    private String code;
    /**
     * 响应消息
     * msg错误信息返回
     */

    private String msg;
    /**
     * 响应子码
     * 具体接口返回状态 000：成功  001：失败
     */

    private String subCode;
    /**
     * 响应子消息
     * 具体接口返回错误信息
     */

    private String subMsg;
    /**
     * 响应数据
     */
    private T data;

    public Result(String code, String msg, String subCode, String subMsg, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.subCode = subCode;
        this.subMsg = subMsg;
        this.data = data;
    }

    /**
     * 处理成功，返回响应数据
     *
     * @return 响应数据
     */
    public static Result success() {

        return new Result("000", "操作成功", null, null, null);
    }

    /**
     * 处理成功，返回响应数据
     *
     * @param data 响应数据
     * @param <T>  数据类型
     * @return 响应数据
     */
    public static <T> Result success(T data) {
        return new Result("000", "操作成功", null, null, data);
    }

    /**
     * 处理成功，返回响应数据
     *
     * @param subCode 子返回码
     * @param subMsg  子返回消息
     * @param <T>     数据类型
     * @return 响应数据
     */
    public static <T> Result success(String subCode, String subMsg) {
        return new Result("000", "操作成功", subCode, subMsg, null);
    }

    /**
     * 处理成功，返回响应数据
     *
     * @param subCode 子返回码
     * @param subMsg  子返回消息
     * @param data    响应数据
     * @param <T>     数据类型
     * @return 响应数据
     */
    public static <T> Result success(String subCode, String subMsg, T data) {
        return new Result("000", "操作成功", subCode, subMsg, data);
    }

    /**
     * 处理失败，返回响应数据
     *
     * @param subCode 错误码
     * @param subMsg  错误消息
     * @param <T>     数据类型
     * @return 响应数据
     */
    public static <T> Result fail(String subCode, String subMsg) {
        return new Result("001", "操作失败", subCode, subMsg, null);
    }





    /**
     * 处理失败，返回响应数据
     *
     * @param msg 错误提示
     * @return 响应数据
     */
    public static Result fail(String msg) {
        return fail("001", msg);
    }

    /**
     * 处理失败，返回默认响应数据
     *
     * @return 响应数据
     */
    public static Result fail() {
        return fail("操作失败");
    }



    /**
     * 请求是否成功
     * @return   成功true，否则false
     */
     public boolean isSuccess() {
         return true;
     }
}