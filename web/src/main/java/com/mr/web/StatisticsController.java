package com.mr.web;

import com.mr.scala.SQLAction;
import com.mr.utils.R;
import org.apache.spark.sql.Row;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scala.collection.Seq;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Egan
 * @date 2019/6/29 16:36
 **/
@RestController
@RequestMapping("statistics")
public class StatisticsController {

    private static final SQLAction action = new SQLAction();

    /**
     * 各性别打分情况
     **/
    @RequestMapping("gender")
    public R statisticalRatingByGender() {
        return R.ok().put("list", getList(action.getAverRatingGender()));
    }

    /**
     * 各类电影打分情况
     **/
    @RequestMapping("type")
    public R statisticalRatingByType() {
        return R.ok().put("list", getList(action.getAverRatingByType()));
    }

    /**
     * 各类电影和性别打分情况
     **/
    @RequestMapping("typeAndGender")
    public R statisticalRatingByTypeAndGender() {
        return R.ok().put("list", getList(action.getAverRatingByTypeAndGender()));
    }

    /**
     * 各职业最喜爱的电影类型
     **/
    @RequestMapping("occupation")
    public R statisticalTypeByOccupation() {
        return R.ok().put("list", getList(action.getLoveTypeByOccupation()));
    }

    private List<Seq<Object>> getList(Row[] rows){
        List<Seq<Object>> list = new ArrayList<>();
        for (Row row : rows)
            list.add( row.toSeq());
        return list;
    }

}
