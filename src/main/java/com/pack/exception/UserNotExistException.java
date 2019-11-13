package com.pack.exception;

import lombok.Data;

/**
 * @Author: liujinhui
 * @Date: 2019/11/9 11:00
 */
@Data
public class UserNotExistException extends RuntimeException {

    private static final long serialVersionUID = -6112780192479692859L;

    private String username;

    public UserNotExistException(String username) {
        super("user not exist");
        this.username = username;
    }


}
