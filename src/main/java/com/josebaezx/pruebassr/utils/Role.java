package com.josebaezx.pruebassr.utils;

import java.util.Arrays;
import java.util.List;

public enum Role {
    CUSTOMER(Arrays.asList(Permission.READ_ALL_PRODUCTS)),
    ADMIN(Arrays.asList(Permission.READ_ALL_PRODUCTS, Permission.SAVE_ONE_PRODUCT, Permission.CREATE_PRODUCT, Permission.UPDATE_PRODUCT, Permission.DELETE_PRODUCT));
    private List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
