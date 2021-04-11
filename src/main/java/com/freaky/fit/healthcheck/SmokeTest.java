package com.freaky.fit.healthcheck;

import com.freaky.fit.entities.UserDetails;
import com.freaky.fit.reositories.UserDetailsrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmokeTest {
    @Autowired private UserDetailsrepository userDetailsrepository;

    public void createDummyUserDetails(){

    }
}
