package com.jf.mydemo.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wjie
 * @date: 2018/1/18 0018
 * @time: 15:50
 * To change this template use File | Settings | File and Templates.
 */
@Component
@ConfigurationProperties(prefix="book")
@PropertySource("classpath:book.properties")
public class BookBean implements Serializable{

    private String name1;
    private String author1;
    private String price1;

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getAuthor1() {
        return author1;
    }

    public void setAuthor1(String author1) {
        this.author1 = author1;
    }

    public String getPrice1() {
        return price1;
    }

    public void setPrice1(String price1) {
        this.price1 = price1;
    }

    @Override
    public String toString() {
        return "BookBean{" +
                "name1='" + name1 + '\'' +
                ", author1='" + author1 + '\'' +
                ", price1='" + price1 + '\'' +
                '}';
    }
}
