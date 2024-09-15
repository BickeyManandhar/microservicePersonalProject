package com.personal.project.cards.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

//this class is used to populate the logged in user who makes the changes
@Component("auditAwareImplCards")
public class AuditAwareImpl implements AuditorAware<String> {//createdBy and updatedBy are of type String hence passed String
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("CARDS_MS"); // TODO: for now we hardcoded but later we will show logged in user when doing Spring Security
    }
}
