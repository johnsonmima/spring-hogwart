package com.johnsonmima.school.service;

import com.johnsonmima.school.controller.utils.ContactConstant;
import com.johnsonmima.school.model.Contact;
import com.johnsonmima.school.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ContactService {

    private final ContactRepository contactRepository;
    @Autowired
    public ContactService(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    // save to database
    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = false;
        contact.setStatus(ContactConstant.STATUS);
        contact.setCreatedAt(LocalDateTime.now());
        contact.setCreatedBy(ContactConstant.CREATED_BY);
//        contact.setUpdatedAt(LocalDateTime.now());
//        log.info(contact.toString());
        int result = contactRepository.saveContactMsg(contact);

        if(result > 0){
            isSaved = true;
        }

        return  isSaved;

    }

    // findMsgsWithOpenStatus

    public List<Contact> findMsgsWithOpenStatus(){
        List<Contact> contactMsgs =
                contactRepository.findMsgsWithStatus(ContactConstant.OPEN_STATUS);
        return contactMsgs;
    }

    // update
    // updateMsgStatus
    public boolean updateMsgStatus(int id, String updatedBy){
        boolean isUpdated = false;

        int result = contactRepository.updateMsgStatus(id,
                ContactConstant.CLOSE_STATUS, updatedBy);

        if(result > 0){
            isUpdated = true;
        }
        return isUpdated;
    }
}
