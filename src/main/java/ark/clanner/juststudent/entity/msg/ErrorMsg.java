package ark.clanner.juststudent.entity.msg;

/**
 * Created by Clanner on 2018/5/3.
 */
public class ErrorMsg {
    private Integer errcode;
    private String errmsg;


    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
