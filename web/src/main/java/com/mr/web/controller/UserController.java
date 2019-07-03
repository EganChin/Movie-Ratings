package com.mr.web.controller;

import com.mr.scala.UsersAction;
import com.mr.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Egan
 * @date 2019/7/3 16:35
 **/
@RestController
@RequestMapping("user")
public class UserController {

    private final static UsersAction userAction = new UsersAction();

    @RequestMapping("record")
    public R getAllRemarkRecords(@RequestParam String uid){
        return R.ok().put("list", userAction.getAllRemarkRecords(uid));
    }

    @RequestMapping("info")
    public R getUserByID(@RequestParam String uid){
        return R.ok().put("info", userAction.getUserByID(uid));
    }
}
