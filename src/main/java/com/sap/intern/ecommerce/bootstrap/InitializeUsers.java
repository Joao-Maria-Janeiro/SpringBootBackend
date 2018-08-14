package com.sap.intern.ecommerce.bootstrap;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InitializeUsers implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ImportUserData importUserData;

    @Override
    public void onApplicationEvent(@NonNull final ContextRefreshedEvent event) {
        importUserData.importData();
    }

}
