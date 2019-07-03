package com.mr.web.controller;

import com.mr.scala.RatingsAction;
import com.mr.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Egan
 * @date 2019/7/3 16:35
 **/
@RestController
@RequestMapping("rating")
public class RatingController {

    private final static RatingsAction ratingAction = new RatingsAction();

    @RequestMapping("lastest")
    public R getLastest(){
        return R.ok().put("list", ratingAction.getRatingsNew20());
    }

}
