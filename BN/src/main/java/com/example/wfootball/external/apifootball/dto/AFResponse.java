package com.example.wfootball.external.apifootball.dto;

import lombok.Data;
import java.util.List;

/** API-Football 공통 Response Wrapper */
@Data
public class AFResponse<T> {
    private String get;
    private List<T> response;
}
