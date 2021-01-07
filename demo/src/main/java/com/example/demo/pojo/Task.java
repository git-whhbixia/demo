package com.example.demo.pojo;

/**
 * Create by Hercules
 * 2021-01-07 10:43
 */
public class Task {

    private String taskId;

    private String taskName;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", taskName='" + taskName + '\'' +
                '}';
    }
}
