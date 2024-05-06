package org.revature.service;

import org.revature.exception.EnvException;

public class EnvService {
    public String getCpuAllocation() throws EnvException {
        String value = System.getenv("max_cpu");
        if(value == null || value.isBlank()){
            throw new EnvException("The environment variable max_cpu was not found");
        }
        return value;
    }
}
