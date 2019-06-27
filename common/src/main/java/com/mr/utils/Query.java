package com.mr.utils;


/**
 * @author LiangYongjie
 * @date 2019-01-05
 */
public class Query {

    private Integer pn = 1;

    private Integer ps = 10;

    private Integer offset;

    private Integer total;

    public Query(Integer pn, Integer ps) {
        this.setPn(pn);
        this.setPs(ps);
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getOffset() {
        return (this.pn - 1) * this.ps;
    }

    public Integer getPn() {
        return pn;
    }

    public void setPn(Integer pn) {
        if (pn != null && pn >= 1) {
            this.pn = pn;
        }
    }

    public Integer getPs() {
        return ps;
    }

    public void setPs(Integer ps) {
        if (ps != null && ps > 0 && ps <= 50) {
            this.ps = ps;
        }
    }
}
