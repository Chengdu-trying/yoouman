package com.yoouman.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页的javaBean
 * @author Administrator
 *
 * @param <T>	分页数据的类型
 */
public class Page<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4994634047624937440L;
	//每页显示的数据量
	private int pageCount;
	//总的数据量
	private int count;
	//最大页码
	private int maxPage;
	//当前页码
	private int pageIndex;
	//显示信息的集合
	private List<T> list = new ArrayList<T>();

	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		//计算最大页码值
		if(count % pageCount==0){
			maxPage = count / pageCount;
		}else{
			maxPage = count / pageCount +1;
		}
		this.count = count;
	}
	public int getMaxPage() {
		return maxPage;
	}
	/*public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}*/
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		//对页面的合法性进行判断
		if(pageIndex <=0){
			this.pageIndex =1;
		}else{
			if(pageIndex > maxPage){
				this.pageIndex = this.maxPage;
			}else{
				this.pageIndex = pageIndex;
			}
		}
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Page() {
		super();
	}
	
}
