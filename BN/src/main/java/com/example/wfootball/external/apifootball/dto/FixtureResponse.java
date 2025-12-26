package com.example.wfootball.external.apifootball.dto;

import lombok.Data;

@Data
public class FixtureResponse {

    private Fixture fixture;
    private League league;
    private Teams teams;
    private Goals goals;

    @Data
    public static class Fixture {
        private Integer id;
        private String date;
        private Status status;
        private Venue venue;

        @Data
        public static class Status {
            private String shortCode;
            private String longCode;
        }

        @Data
        public static class Venue {
            private String name;
        }
    }

    @Data
    public static class League {
        private Integer id;
        private String name;
        private Integer season;
    }

    @Data
    public static class Teams {
        private TeamInfo home;
        private TeamInfo away;

        @Data
        public static class TeamInfo {
            private Integer id;
            private String name;
        }
    }

    @Data
    public static class Goals {
        private Integer home;
        private Integer away;
    }
}
