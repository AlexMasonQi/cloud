package com.alex.cloud.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AudioLibrary implements Serializable
{
    private Integer id;

    private String audioName;

    private String audioPath;

    private String audioTime;

    private String createUser;

    private String createTime;

    private String updateUser;

    private String updateTime;
}
