package com.mdud.spotifyfavorites.logging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    private String method;
    private String message;
    private LocalDateTime date;
}

