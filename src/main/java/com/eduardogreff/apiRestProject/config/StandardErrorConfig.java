package com.eduardogreff.apiRestProject.config;

import java.time.Instant;

public class StandardErrorConfig {

    private Instant moment;
    private Integer status;
    private String typeError;
    private String pathError;
    private String messageError;

    public StandardErrorConfig(Instant moment, Integer status, String typeError, String pathError, String messageError) {
        this.moment = moment;
        this.status = status;
        this.typeError = typeError;
        this.pathError = pathError;
        this.messageError = messageError;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTypeError() {
        return typeError;
    }

    public void setTypeError(String typeError) {
        this.typeError = typeError;
    }

    public String getPathError() {
        return pathError;
    }

    public void setPathError(String pathError) {
        this.pathError = pathError;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }
}
