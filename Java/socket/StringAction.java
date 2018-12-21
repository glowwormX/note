package com.hlkj.baojin;

/**
 * @author 徐其伟
 * @Description:
 * @date 18-12-21 下午2:09
 */
public class StringAction implements Server.ObjectAction {
    @Override
    public Object doAction(Object rev) {
        return ((String)rev) + " after do action";
    }
}
