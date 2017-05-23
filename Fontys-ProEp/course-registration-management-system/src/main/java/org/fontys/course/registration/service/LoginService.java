package org.fontys.course.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Phoenix on 17-May-17.
 */
@Service
public class LoginService {

    @Autowired
    private UtilService utilService;

    public boolean LoginUser(Integer pcn, String pass) throws Exception
    {
        return utilService.CheckUsernameAndPassword(pcn, pass);
    };

}
