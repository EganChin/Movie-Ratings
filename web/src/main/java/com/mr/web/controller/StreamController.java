package com.mr.web.controller;

import com.mr.scala.StreamAction;
import com.mr.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Egan
 * @date 2019/7/3 16:45
 **/
@RestController
@RequestMapping("stream")
public class StreamController {

    public final static StreamAction streamAction = new StreamAction();

    @RequestMapping("pop")
    public R getMaxRemarkNumMovie(){
        return R.ok().put("movie", streamAction.getMaxRemarkNumMovie());
    }

    @RequestMapping("record")
    public R getLastRatingRecords(){
        return R.ok().put("list", streamAction.getLastRatingRecords());
    }

    @RequestMapping("rating")
    public R addScore(@RequestParam String uid, @RequestParam String mid, @RequestParam String score){
        streamAction.writeByStream(uid,mid,score);
        return R.ok();
    }

}
