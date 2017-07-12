package org.truenewx.example.data.model.user;

import org.truenewx.core.annotation.Caption;
import org.truenewx.core.enums.annotation.EnumValue;

/**
 *
 * 性别
 *
 * @author jianglei
 * @since JDK 1.8
 */
public enum Sex {

    @Caption("男")
    @EnumValue("M")
    MALE,

    @Caption("女")
    @EnumValue("F")
    FEMALE,

    @Caption("其它")
    @EnumValue("O")
    OTHER;

}
