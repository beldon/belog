package me.beldon.belog.install.bean;

/**
 * 与前端交互的信息实体类
 *
 * @author Beldon
 */
public class Msg {
    /**
     * 错误码
     */
    private int errCode;
    /**
     * 错误信息
     */
    private String errMsg;
    /**
     * 状态
     */
    private boolean status;

    public Msg() {
    }

    public Msg(String errMsg) {
        this.errMsg = errMsg;
        this.errCode = 0;
        this.status = true;
    }

    public Msg(int errCode, String errMsg, boolean status) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.status = status;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
