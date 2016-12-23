package com.hut.common.struct;

/**
 * Created by BruceJ on 2016/5/25.
 *
 * @since 0.0.1
 */
public class PageForm {
	
	private int page=1;
	private int maxSize=10;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
}
