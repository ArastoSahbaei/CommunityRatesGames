
package com.communityratesgames.user;

import java.util.List;
import java.util.ArrayList;

public class AuthToken {
    private Long token;
    private Long userId;

    public AuthToken(Long token, Long userId) {
        this.token = token;
        this.userId = userId;
    }

    public Long getToken() {
        return this.token;
    }

    public Long getUserId() {
        return this.userId;
    }

    private static long loginIndex = 0;
    private static List<AuthToken> logins = new ArrayList<AuthToken>();

    public static Long generateNewToken(Long userId) {
        logins.add(new AuthToken(++loginIndex, userId));
        return loginIndex;
    }
    
    private static int find(Long token) {
        // Assume that the auth table is sorted.
        int index = logins.size() / 2;
        int step = index / 2;
        long current = 0;
        
        if (logins.size() == 0) {
            return -1;
        }

        do {
            // Binary search
            current = logins.get(index).token;
            if (current == token) {
                return index;
            } else if (current < token) {
                index += step;
            } else if (current > token) {
                index -= step;
            }
            step /= 2;
        } while (step != 0);
        return -1;
    }

    public static Long getUserId(Long token) {
        int index = find(token);
        if (index == -1) {
            return -1L;
        } else {
            return logins.get(index).userId;
        }
    }

    public static boolean close(Long token) {
        int index = find(token);
        if (index == -1) {
            return false;
        } else {
            logins.remove(index);
            return true;
        }
    }
}
