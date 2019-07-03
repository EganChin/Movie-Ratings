package com.mr.web.controller;

import com.mr.scala.TagsAction;
import com.mr.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Egan
 * @date 2019/7/3 16:27
 **/
@RestController
@RequestMapping("tag")
public class TagController {

    private final static TagsAction tagAction = new TagsAction();

    @RequestMapping("lastest")
    public R getLastest(@RequestParam String mid){
        return R.ok().put("list", tagAction.getMoviesLastTag(mid));
    }

}
