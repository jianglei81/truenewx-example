package org.truenewx.example.data.model.user;

import org.truenewx.core.Strings;
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
    @Caption(value = "Male", locale = Strings.LOCALE_EN)
    @EnumValue("M")
    MALE,

    @Caption("女")
    @Caption(value = "Female", locale = Strings.LOCALE_EN)
    @EnumValue("F")
    FEMALE,

    @Caption("其它")
    @Caption(value = "Other", locale = Strings.LOCALE_EN)
    @EnumValue("O")
    OTHER;

}
