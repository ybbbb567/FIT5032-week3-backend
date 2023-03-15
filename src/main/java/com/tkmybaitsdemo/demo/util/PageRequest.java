package com.tkmybaitsdemo.demo.util;

import io.swagger.annotations.ApiModelProperty;

/**
 * 分页请求
 */
public class PageRequest {
    /**
     * 当前页码
     */
    @ApiModelProperty(name = "pageNum",notes = "当前页码")
    private int pageNum = 1;
    /**
     * 每页数量
     */
    @ApiModelProperty(name = "pageSize",notes = "每页数量")
    private int pageSize = 10;

    public int getPageNum() {
        return pageNum;
    }
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}