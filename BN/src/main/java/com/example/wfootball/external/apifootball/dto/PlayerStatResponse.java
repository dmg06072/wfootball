package com.example.wfootball.external.apifootball.dto;

import lombok.Data;

@Data
public class PlayerStatResponse {

    private Player player;
    private Statistic[] statistics;   // 배열 1개가 기본

    @Data
    public static class Player {
        private Integer id;
        private String name;
    }

    @Data
    public static class Statistic {
        private Team team;
        private Goal goals;

        @Data
        public static class Team {
            private Integer id;
            private String name;
        }

        @Data
        public static class Goal {
            private Integer total;
            private Integer assists;
        }
    }
}
