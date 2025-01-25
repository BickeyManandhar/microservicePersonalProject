package com.personal.project.accounts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
public record AccountsContactInfoDto(String message, Map<String, String> contactDetails,String env, List<String> onCallSupport) {
    //fields in record are final so there will only be getters no setters
}
