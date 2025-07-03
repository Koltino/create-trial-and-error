package com.koltino.trialanderror.tools;

import java.util.UUID;

public final class VaultKeyHelper {
    private static final ThreadLocal<UUID> keyUserThreadLocal = new ThreadLocal<>();

    public static void setKeyUser(UUID uuid){
        keyUserThreadLocal.set(uuid);
    }
    public static UUID getKeyUser(){
        return keyUserThreadLocal.get();
    }
    public static void clearKeyUser(){
        keyUserThreadLocal.remove();
    }
}
