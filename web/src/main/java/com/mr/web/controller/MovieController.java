package com.mr.web.controller;

import com.mr.scala.MoviesAction;
import com.mr.utils.PageUtils;
import com.mr.utils.Query;
import com.mr.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


/**
 * @author Egan
 * @date 2019/6/27 19:35
 **/
@RestController
@RequestMapping("/movie")
public class MovieController {

    private static final MoviesAction action = new MoviesAction();

    @RequestMapping("/byId")
    public R getMovieById(@RequestParam String param, Query query) {


        return R.ok().put("page", getPage(action.getMovieById(param), query));
    }

    @RequestMapping("/byName")
    public R getMovieBySongName(@RequestParam String param, Query query) {

        return R.ok().put("page", getPage(action.getMovieByObsName(param), query));
    }

    @RequestMapping("/byPreName")
    public R getMovieByPreSongName(@RequestParam String param, Query query) {

        return R.ok().put("page", getPage(action.getMovieByPreObsName(param), query));
    }

    @RequestMapping("/byPostName")
    public R getMovieByPostSongName(@RequestParam String param, Query query) {

        return R.ok().put("page", getPage(action.getMovieByPostObsName(param), query));
    }

    @RequestMapping("/byType")
    public R getMovieByType(@RequestParam String param, Query query) {

        return R.ok().put("page", getPage(action.getMovieByType(param), query));
    }

    private PageUtils getPage(String[] result, Query query) {
        int begin = (query.getPn() - 1) * query.getPs();
        int end = query.getPn() * query.getPs();
        List<String> list = Arrays.asList(result);
        return new PageUtils<>(query, list.subList(begin, end >= list.size() ? list.size() : end));
    }
}
