package com.example.shareIngJdbc.demo;

/**
 * @author :xie
 * Email: 1487471733@qq.com
 * Date: 2018/10/5
 * Time: 11:17
 * Describe:
 */
public class Transaction {

    private final int id;
    private final Integer value;
    private final Type type;

    public Transaction(int id, Integer value, Type type) {
        this.id = id;
        this.value = value;
        this.type = type;
    }

    public enum Type {
        A, B, C, D, GEOCERY
    }

    public int getId() {return id;}
    public Integer getValue() {return value;}
    public Type getType() {return type;}


}
