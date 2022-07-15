package com.sedatbsp.url.infrastructure.common.constant;

public class SwaggerConstant {
    /**
     * Controller Path static value in String type.
     */
    public static final String ADAPTERS_PATH = "com.sedatbsp.url.infrastructure.adapters";

    public static final String URL_CONTROLLER_PATH = pathGenerate("url");

    public static final String USER_CONTROLLER_PATH = pathGenerate("user");

    private static String pathGenerate(String adapterName) {
        return ADAPTERS_PATH + "." + adapterName + ".rest.controller";
    }
}
