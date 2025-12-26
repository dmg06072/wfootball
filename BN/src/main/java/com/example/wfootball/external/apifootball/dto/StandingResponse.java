package com.example.wfootball.external.apifootball.dto;

import lombok.Data;

@Data
public class StandingResponse {

    private League league;

    @Data
    public static class League {
        private Integer id;
        private String name;
        private Integer season;
        private Standing[][] standings;  // standings[0] 안에 리스트가 들어감
    }

    @Data
    public static class Standing {
        private Integer rank;
        private Team team;
        private Integer points;
        private Integer goalsDiff;
        private Record all;

        @Data
        public static class Team {
            private Integer id;
            private String name;
        }

        @Data
        public static class Record {
            private Integer played;
            private Integer win;
            private Integer draw;
            private Integer lose;
        }
    }
}
