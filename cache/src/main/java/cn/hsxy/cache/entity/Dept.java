package cn.hsxy.cache.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class Dept implements Serializable {
    String id;
    String name;
    String location;
}
