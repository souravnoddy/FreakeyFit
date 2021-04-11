package com.freaky.fit.services;

import com.freaky.fit.constants.ErrorMessages;
import com.freaky.fit.entities.UserDetails;
import com.freaky.fit.enums.Status;
import com.freaky.fit.exception.BusinessException;
import com.freaky.fit.reositories.UserDetailsrepository;
import com.freaky.fit.utils.StackTraceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsService {
    @Autowired
    private UserDetailsrepository userDetailsrepository;

    public ResponseEntity<UserDetails> addUser(UserDetails userDetails) {
        try {
            return ResponseEntity.ok(userDetailsrepository.save(userDetails));
        } catch (Exception ex) {
            log.error("Error While Creating User {}", StackTraceUtil.getStackTrace(ex));
            throw new BusinessException(HttpStatus.BAD_REQUEST, StackTraceUtil.getStackTrace(ex));
        }
    }

    public ResponseEntity<UserDetails> updateUser(UserDetails userDetails) {
        UserDetails userDetailExisting = userDetailsrepository.findById(userDetails.getId()).orElse(null);
        if (userDetailExisting == null) {
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessages.USER_NOT_FOUND);
        }

        if (constantFieldUpdateRequested(userDetailExisting, userDetails)) {
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessages.FIELDS_CANNOT_BE_UPDATED);
        }

        try {
            return ResponseEntity.ok(userDetailsrepository.save(userDetails));
        } catch (Exception ex) {
            log.error("Error While updating User {}", StackTraceUtil.getStackTrace(ex));
            throw new BusinessException(HttpStatus.BAD_REQUEST, StackTraceUtil.getStackTrace(ex));
        }

    }

    private boolean constantFieldUpdateRequested(UserDetails userDetailExisting, UserDetails userDetails) {
        // TODO: 11/4/21 mandatoryField updaterequest steps to be added here

        return userDetailExisting.getEmail().equalsIgnoreCase(userDetails.getEmail());
    }

    public ResponseEntity<?> deleteUser(String userId) {
        UserDetails userDetailExisting = userDetailsrepository.findById(userId).orElse(null);
        if (userDetailExisting == null) {
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessages.USER_NOT_FOUND);
        }
        userDetailExisting.setStatus(Status.INACTIVE);
        userDetailsrepository.save(userDetailExisting);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<UserDetails> getUser(String userId) {
        UserDetails userDetails = userDetailsrepository.findById(userId).orElse(null);
        if (userDetails == null) {
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessages.USER_NOT_FOUND);
        }
        return ResponseEntity.ok(userDetails);
    }
}
