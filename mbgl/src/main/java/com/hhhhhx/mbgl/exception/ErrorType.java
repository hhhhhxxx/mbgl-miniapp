package com.hhhhhx.mbgl.exception;

public interface ErrorType {
    /**
     * 返回code
     *
     * @return
     */
    Integer getCode();
    /**
     * 返回mesg
     *
     * @return
     */
    String getMessage();
}
