package com.server.workordersystem.service;

import com.server.workordersystem.dto.UserPhoneMsg;
import com.server.workordersystem.entity.Contact;
import com.server.workordersystem.entity.ContactMsg;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 全鸿润
 */
@Service
public interface UserService {

    Integer addContact(Contact contact);

    Integer deleteContact(Contact contact);

    List<ContactMsg> getContactList(String username);

    Integer updatePhone(UserPhoneMsg msg);
}
