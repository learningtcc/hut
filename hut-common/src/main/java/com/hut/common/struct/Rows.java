package com.hut.common.struct;


import com.github.pagehelper.Page;

import java.util.List;

/**
 * Created by BruceJ on 2016/5/25.
 *
 * @since 0.0.1
 */
public class Rows<T> {
	
	private long total;
	private List<T> list;
	private int pageNum;
	private int pageMax;
	
	public Rows(long total, List<T> list) {
		super();
		this.total = total;
		this.list = list;
	}

	public Rows() {
		super();
	}

	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageMax() {
		return pageMax;
	}

	public void setPageMax(int pageMax) {
		this.pageMax = pageMax;
	}

	public static <T> Rows<T> build(Page<T> page) {
		Rows<T> rows = new Rows<T>(page.getTotal(),page.getResult());
		rows.setPageMax(page.getPages());
		rows.setPageNum(page.getPageNum());
		return rows;
	}
}
