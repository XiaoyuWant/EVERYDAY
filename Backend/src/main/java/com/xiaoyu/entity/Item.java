package com.xiaoyu.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_item")
public class Item {

    //自增
    @TableId(value = "item_id", type = IdType.AUTO)
    private Integer itemId;
    
    private String content;
    
    private Date time;
    
    private String user;
    
    private Integer likes;

}

