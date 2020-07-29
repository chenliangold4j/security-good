package com.phantom5702.oauth.model;

import lombok.Data;

/**
 * @version 1.0
 **/
@Data
public class PermissionDto {
    private String id;
    private String code;
    private String description;
    private String url;

}
