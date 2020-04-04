package edu.bistu.cstp.domain.register;

import lombok.Data;

@Data
public class RegisterResult
{
    /**
     * 用户注册结果
     */

    private boolean result;
    private String msg;

    private RegisterResult(boolean result, String msg)
    {
        this.result = result;
        this.msg = msg;
    }

    public static final RegisterResult SUCCESS = new RegisterResult(true, null);
    public static final RegisterResult DUPLICATE = new RegisterResult(false, "用户名已存在");
    public static final RegisterResult ERROR = new RegisterResult(false, "发生未知错误");
}
