
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
        if (logins.size() == 0) {
            return -1;
        }

        for (int i = 0; i < logins.size(); i++) {
            if (logins.get(i).token.equals(token)) {
                return i;
            }
        }

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
