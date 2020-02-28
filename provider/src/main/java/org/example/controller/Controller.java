package org.example.controller;

import org.apache.commons.lang3.StringUtils;
import org.example.exceptions.InvalidQueryParameterException;
import org.example.exceptions.QueryParameterRequiredException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@RestController
public class Controller {

    @GetMapping("/provider")
    public Map<String, Serializable> providerJson(@RequestParam(required = false) String validDate) {
        if (isNotEmpty(validDate)) {
            try {
                LocalDateTime.parse(validDate);
                Map<String, Serializable> map = new HashMap<>(3);
                map.put("test", "NO");
                map.put("validDate",
                        OffsetDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")));
                map.put("count", 1000);
                return map;
            } catch (DateTimeParseException e) {
                throw new InvalidQueryParameterException("'" + validDate + "' is not a date", e);
            }
        } else {
            throw new QueryParameterRequiredException("validDate is required");
        }
    }
}
