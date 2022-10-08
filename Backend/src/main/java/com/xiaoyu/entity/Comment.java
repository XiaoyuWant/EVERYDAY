package com.xiaoyu.entity;

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
@TableName("t_comment")
public class Comment  {

    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;
    
    private String content;
    
    private String userName;
    
    private Integer itemId;

}

