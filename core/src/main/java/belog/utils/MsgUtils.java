package belog.utils;


import belog.pojo.Msg;

/**
 * Created by Beldon
 */
public class MsgUtils {

    /**
     * 返回错误信息
     *
     * @param msg 自定义信息
     * @return
     */
    public static Msg error(String msg) {
        return new Msg(-1, msg, false);
    }


    /**
     * 返回错误信息
     *
     * @return
     */
    public static Msg error() {
        return error("error");
    }

    /**
     * 返回成功信息
     *
     * @param msg
     * @return
     */
    public static Msg success(String msg) {
        return new Msg(0, msg, true);
    }

    /**
     * 返回成功信息
     *
     * @return
     */
    public static Msg success() {
        return success("success");
    }
}
