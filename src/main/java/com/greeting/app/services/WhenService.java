package com.greeting.app.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class WhenService {

    @Value("${whom.service.zone.id}")
    private String zoneId;

    public String when() {
        return Instant.now()
                .atZone(ZoneId.of(zoneId))
                .format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

}
