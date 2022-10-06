package com.xiaoyu.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (TItem)表实体类
 *
 * @author makejava
 * @since 2022-10-06 16:58:27
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_item")
public class Item {
    @TableId("item_id")
    private Integer itemId;
    
    private String content;
    
    private Date time;
    
    private String user;
    
    private Integer likes;



    /**
     * 获取主键值
     *
     * @return 主键值
     */

}

