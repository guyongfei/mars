package com.witshare.mars.pojo.dto;

import org.quartz.Trigger;

import java.math.BigInteger;

public class JobAndTrigger {
    private String jobName;
    private String jobGroup;
    private String jobClassName;
    private String triggerName;
    private String description;
    private String triggerGroup;
    private BigInteger repeatInterval;
    private BigInteger timesTriggered;
    private String cronExpression;
    private String triggerState;
    private Object jobData;
    private Trigger trigger;

    private JobAndTrigger() {
    }

    public static JobAndTrigger newInstance() {
        return new JobAndTrigger();
    }

    public String getJobName() {
        return jobName;
    }

    public JobAndTrigger setJobName(String jobName) {
        this.jobName = jobName;
        return this;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public JobAndTrigger setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
        return this;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public JobAndTrigger setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
        return this;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public JobAndTrigger setTriggerName(String triggerName) {
        this.triggerName = triggerName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public JobAndTrigger setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public JobAndTrigger setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
        return this;
    }

    public BigInteger getRepeatInterval() {
        return repeatInterval;
    }

    public JobAndTrigger setRepeatInterval(BigInteger repeatInterval) {
        this.repeatInterval = repeatInterval;
        return this;
    }

    public BigInteger getTimesTriggered() {
        return timesTriggered;
    }

    public JobAndTrigger setTimesTriggered(BigInteger timesTriggered) {
        this.timesTriggered = timesTriggered;
        return this;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public JobAndTrigger setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
        return this;
    }

    public String getTriggerState() {
        return triggerState;
    }

    public JobAndTrigger setTriggerState(String triggerState) {
        this.triggerState = triggerState;
        return this;
    }

    public Object getJobData() {
        return jobData;
    }

    public JobAndTrigger setJobData(Object jobData) {
        this.jobData = jobData;
        return this;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public JobAndTrigger setTrigger(Trigger trigger) {
        this.trigger = trigger;
        return this;
    }
}
