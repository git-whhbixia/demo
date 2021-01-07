package com.example.demo;

/**
 * Create by Hercules
 * 2021-01-07 10:44
 */
public enum TaskType {

    ORDER(1),PAY(2);

    private int index;

    TaskType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

}
