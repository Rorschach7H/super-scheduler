package com.meixiaoxi.scheduler.core.task.domain;

public enum ExecuteState {

        WAIT_EXECUTE((byte) 0, "待执行"),
        SUCCESS((byte) 1, "执行成功"),
        FAIL((byte) 2, "执行失败"),
        CANCEL((byte) 3, "任务取消");

        private byte code;
        private String name;

        ExecuteState(byte code, String name) {
            this.code = code;
            this.name = name;
        }

        public byte getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }