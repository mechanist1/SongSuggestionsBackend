package org.example.apitestingwitherrorthrowing.Dtos;

import lombok.Data;

import java.util.List;

@Data
public class PlayListCreateRequest {
    private String name;
    private Long userId;
    private List<Long> songIds;
}