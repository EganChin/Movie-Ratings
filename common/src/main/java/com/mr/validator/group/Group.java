package com.mr.validator.group;

import javax.validation.GroupSequence;

/**
 * @author LiangYongjie
 * @date 2019-01-06
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {
}
