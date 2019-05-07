package com.demo.thread;

import java.util.HashMap;
import java.util.Map;

public class BatchMessageTask implements Runnable {

    private String ip;
    private String msg;

    public BatchMessageTask() {
    }

    public BatchMessageTask(String ip, String msg) {
        this.ip = ip;
        this.msg = msg;
    }

    public void run() {
        try {
            Map<String, Object> param = new HashMap();
            param.put("id", id);
            param.put("msg", msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

