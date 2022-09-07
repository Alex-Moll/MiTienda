package com.miTienda.app.auth.utility;

public enum RoleEnum {
    USER, ADMIN;

    private static final String PREFIX = "ROLE_";
    public String getFullRoleName() {
        return PREFIX + name();
    }

    public String getSimpleRoleName() {
        return name();
    }
}
