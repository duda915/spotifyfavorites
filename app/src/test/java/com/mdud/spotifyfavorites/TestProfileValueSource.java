package com.mdud.spotifyfavorites;

import org.springframework.test.annotation.ProfileValueSource;

public class TestProfileValueSource implements ProfileValueSource {

    @Override
    public String get(String s) {
        switch (s) {
            case "integration":
                return String.valueOf(TestConfig.INTEGRATION_TESTS);
            default:
                return "false";
        }
    }
}
