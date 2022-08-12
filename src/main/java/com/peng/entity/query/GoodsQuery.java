package com.peng.entity.query;

import lombok.Data;

import java.io.Serializable;

/**
 * @author peng
 * @create 2022-08-01 00:31
 */
@Data
public class GoodsQuery implements Serializable {
    private String searchText;//查询变量
}
