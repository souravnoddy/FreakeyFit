package com.freaky.fit.healthcheck;

import com.freaky.fit.entities.UserDetails;
import com.freaky.fit.reositories.UserDetailsrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@Component
public class SmokeTest {
    @Autowired
    private UserDetailsrepository userDetailsrepository;

    public void createDummyUserDetails() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        UserDetails userDetails = podamFactory.manufacturePojo(UserDetails.class);
        userDetailsrepository.save(userDetails);
    }
}
