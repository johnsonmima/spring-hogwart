package com.johnsonmima.school.service;

import com.johnsonmima.school.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactService {

    // save to database
    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = true;
        log.info(contact.toString());
        return  isSaved;

    }
}
