package com.mr.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @author LiangYongjie
 * @date 2019-01-05
 */
public class PageUtils<T> {

	private Long pn;
	private Long ps;
	private Long total;
	private List<?> list;

	public PageUtils(IPage page) {
		this(page, page.getRecords());
	}

	public PageUtils(IPage page, List<?> list) {
		this.pn = page.getCurrent();
		this.ps = page.getSize();
		this.total = page.getTotal();
		this.list = list;
	}

	public PageUtils(Query query, List<?> list) {
		this.pn = query.getPn().longValue();
		this.ps = query.getPs().longValue();
		this.total = query.getTotal().longValue();
		this.list = list;
	}

	public Long getPn() {
		return pn;
	}

	public void setPn(Long pn) {
		this.pn = pn;
	}

	public Long getPs() {
		return ps;
	}

	public void setPs(Long ps) {
		this.ps = ps;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}
}
