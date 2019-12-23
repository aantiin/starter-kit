package com.antin.kit.common.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * just for checking/test
 */
@Service
public class ApplicationService {
    @Value("${app.version}")
    private String version;

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }
}
