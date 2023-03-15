package com.tkmybaitsdemo.demo.util;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 分页返回结果
 */
public class PageResult {
    /**
     * 当前页码
     */
    @ApiModelProperty(name = "pageNum", notes = "当前页码")
    private int pageNum;
    /**
     * 每页数量
     */
    @ApiModelProperty(name = "pageSize", notes = "每页数量")
    private int pageSize;
    /**
     * 记录总数
     */
    @ApiModelProperty(name = "totalSize", notes = "记录总数")
    private long totalSize;
    /**
     * 页码总数
     */
    @ApiModelProperty(name = "totalPages", notes = "页码总数")
    private int totalPages;
    /**
     * 数据模型
     */
    @ApiModelProperty(name = "content", notes = "数据模型")
    private List<?> content;

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

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<?> getContent() {
        return content;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }
}