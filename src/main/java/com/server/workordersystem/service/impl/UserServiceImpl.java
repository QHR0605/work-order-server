package com.server.workordersystem.service.impl;

import com.server.workordersystem.config.SpringContextConfig;
import com.server.workordersystem.dto.UserPhoneMsg;
import com.server.workordersystem.entity.Contact;
import com.server.workordersystem.entity.ContactMsg;
import com.server.workordersystem.mapper.UserMapper;
import com.server.workordersystem.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 全鸿润
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper = SpringContextConfig.getBean(UserMapper.class);

    @Override
    public Integer addContact(Contact contact) {
        Integer row;
        try {
            row = userMapper.addContact(contact);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return row;
    }

    @Override
    public Integer deleteContact(Contact contact) {
        Integer row;
        try {
            row = userMapper.deleteContact(contact);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return row;
    }

    @Override
    public List<ContactMsg> getContactList(String username) {
        List<ContactMsg> contactList;
        try {
            contactList = userMapper.getContact(username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return contactList;
    }

    @Override
    public Integer updatePhone(UserPhoneMsg msg) {
        Integer row;
        try {
            row = userMapper.updateUserPhone(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return row;
    }
}
