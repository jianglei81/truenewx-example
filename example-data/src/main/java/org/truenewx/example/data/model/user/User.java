package org.truenewx.example.data.model.user;

import org.truenewx.core.annotation.Caption;
import org.truenewx.data.model.CloneableForSession;

/**
 *
 * 用户
 *
 * @author jianglei
 * @since JDK 1.8
 */
@Caption("用户")
public class User extends AbstractUser implements CloneableForSession<User> {

    @Override
    public User cloneForSession() {
        final User user = new User();
        user.setEmail(getEmail());
        user.setMobilePhone(getMobilePhone());
        user.setFullname(getFullname());
        user.setSex(getSex());
        user.setDisabled(isDisabled());
        user.setCreateTime(getCreateTime());
        return user;
    }

}
