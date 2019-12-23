package com.antin.kit.common.vo;

public class ResultPageVO extends ResultVO {

    private String pages;
    private String elements;

    public ResultPageVO() {
    }

    public ResultPageVO(String pages, String elements) {
        this.pages = pages;
        this.elements = elements;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getElements() {
        return elements;
    }

    public void setElements(String elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResultPageVO{");
        sb.append("pages='").append(pages).append('\'');
        sb.append(", elements='").append(elements).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
