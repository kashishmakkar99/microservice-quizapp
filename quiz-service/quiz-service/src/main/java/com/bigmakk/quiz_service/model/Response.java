package com.bigmakk.quiz_service.model;

public class Response {

    private Integer id;

    public String getAns() {
        return ans;
    }

    public Response() {
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public Response(Integer id, String ans) {
        this.id = id;
        this.ans = ans;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String ans;
}
