package com.five.user.utils;


/**
 * Created by msi on 2017/6/23.
 */
public class CodeThread implements Runnable {
    private String username;

    public CodeThread(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        if (CodeUtil.codeMap.containsKey(username)) {
            CodeUtil.codeMap.remove(username);
        }
    }
}
