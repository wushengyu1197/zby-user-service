package com.shopping.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_info")
public class UserInfo {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //手机号
    @TableField(value = "phone")
    private String phone;

    //'性别 0=未知 1=男 2=女',
    @TableField(value = "sex")
    private String sex;

    //名称
    @TableField(value = "name")
    private String name;

    //'创建时间
    @TableField(value = "create_time")
    private Date createTime;

    //自动更新时间戳
    @TableField(value = "update_time")
    private Date updateTime;
}
