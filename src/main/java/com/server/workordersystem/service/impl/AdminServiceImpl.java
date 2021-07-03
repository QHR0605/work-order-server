package com.server.workordersystem.service.impl;

import com.server.workordersystem.config.SpringContextConfig;
import com.server.workordersystem.dto.NewUserMessage;
import com.server.workordersystem.entity.Log;
import com.server.workordersystem.entity.User;
import com.server.workordersystem.mapper.AdminMapper;
import com.server.workordersystem.service.AdminService;
import com.server.workordersystem.util.http.CookieUtils;
import com.server.workordersystem.util.http.HttpUtil;
import com.server.workordersystem.util.idGenerator.IdGenerator;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author 全鸿润
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper = SpringContextConfig.getBean(AdminMapper.class);
    private Log log;

    public String getUsername() {
        String username = null;
        try {
            Cookie cookie = CookieUtils.findCookie(HttpUtil.getRequest().getCookies(), "username");
            if (cookie != null) {
                username = cookie.getValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }

    public void createLog(String operation, Boolean state, String reason) {
        log = new Log()
                .logId(IdGenerator.getId())
                .username(getUsername())
                .operation(operation)
                .time(new Timestamp(System.currentTimeMillis()))
                .state(state)
                .reason(reason)
                .build();
    }

    @Override
    public Integer auth(List<String> usernames, Integer authType) {
        Integer row = null;
        StringBuilder content = new StringBuilder();
        for (String username : usernames
        ) {
            content.append(username).append(" ");
        }
        if (authType == 0) {
            createLog("授权<" + content + ">的权限为普通用户", true, null);
        } else if (authType == 1) {
            createLog("授权<" + content + ">的权限为管理员", true, null);
        }

        try {
            row = adminMapper.updateUserAuthorization(usernames, authType);
        } catch (Exception e) {
            e.printStackTrace();
            log.setState(false);
            log.setReason(e.getMessage());
            try {
                adminMapper.addLog(log);
            } catch (Exception exception) {
                exception.printStackTrace();
                return row;
            }
            return row;
        }
        try {
            adminMapper.addLog(log);
        } catch (Exception e) {
            e.printStackTrace();
            return row;
        }
        return row;
    }

    @Override
    public Integer createUser(NewUserMessage message) {

        User user = new User()
                .username(message.getUsername())
                .password(message.getPassword())
                .accountType(message.getAccountType());
        Integer row = null;
        createLog("创建账户:" + message.getUsername(), true, null);
        try {
            row = adminMapper.insertNewUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            log.setState(false);
            log.setReason(e.getMessage());
            try {
                adminMapper.addLog(log);
            } catch (Exception exception) {
                exception.printStackTrace();
                return row;
            }
            return row;
        }
        try {
            adminMapper.addLog(log);
        } catch (Exception e) {
            e.printStackTrace();
            return row;
        }
        return row;
    }

    @Override
    public Integer deleteUsersByUsername(List<String> usernames) {
        Integer row = null;
        StringBuilder content = new StringBuilder();
        for (String username : usernames
        ) {
            content.append(username).append(" ");
        }
        createLog("删除账户<" + content + ">", true, null);
        try {
            row = adminMapper.deleteUsers(usernames);
        } catch (Exception e) {
            e.printStackTrace();
            log.setState(false);
            log.setReason(e.getMessage());
            try {
                adminMapper.addLog(log);
            } catch (Exception exception) {
                exception.printStackTrace();
                return row;
            }
            return row;
        }
        try {
            adminMapper.addLog(log);
        } catch (Exception e) {
            e.printStackTrace();
            return row;
        }
        return row;
    }

    @Override
    public Integer updateUsersLogState(List<String> usernames, Boolean logState) {

        Integer row = null;
        StringBuilder content = new StringBuilder();
        for (String username : usernames
        ) {
            content.append(username).append(" ");
        }
        if (logState) {
            createLog("注销账户:<" + content + ">", true, null);
        } else {
            createLog("恢复账户:<" + content + ">", true, null);
        }

        try {
            row = adminMapper.updateUserLogState(usernames, logState);
        } catch (Exception e) {
            e.printStackTrace();
            log.setState(false);
            log.setReason(e.getMessage());
            try {
                adminMapper.addLog(log);
            } catch (Exception exception) {
                exception.printStackTrace();
                return row;
            }
            return row;
        }
        try {
            adminMapper.addLog(log);
        } catch (Exception e) {
            e.printStackTrace();
            return row;
        }
        return row;
    }

    @Override
    public Integer updateUsersType(List<String> usernames, Integer type) {
        Integer row = null;
        StringBuilder content = new StringBuilder();
        for (String username : usernames
        ) {
            content.append(username).append(" ");
        }
        if (type == 0) {
            createLog("修改账户:<" + content + ">的用户类型为普通用户", true, null);
        } else if (type == 1) {
            createLog("修改账户:<" + content + ">的用户类型为管理员", true, null);
        }
        try {
            row = adminMapper.updateUserType(usernames, type);
        } catch (Exception e) {
            e.printStackTrace();
            log.setState(false);
            log.setReason(e.getMessage());
            try {
                adminMapper.addLog(log);
            } catch (Exception exception) {
                exception.printStackTrace();
                return row;
            }
            try {
                adminMapper.addLog(log);
            } catch (Exception exception) {
                exception.printStackTrace();
                return row;
            }
            return row;
        }
        return row;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try {
            users = adminMapper.selectAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return users;
        }
        return users;
    }

}
