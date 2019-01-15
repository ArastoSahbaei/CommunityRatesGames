package com.communityratesgames.util;

import java.util.List;
import javax.ws.rs.core.HttpHeaders;

public class AuthUtils {
    public static Long getHeaderToken(HttpHeaders header) {
        List<String> toklist = header.getRequestHeader("Authorization");
        if (toklist == null || toklist.size() == 0) {
            return null;
        }

        try {
            return Long.parseLong(toklist.get(0));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
