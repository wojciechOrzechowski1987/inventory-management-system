package com.worzech.inventorymanagementsystem.constant;

public class SecurityConstant {
    public static final long EXPIRATION_TIME = 3_600_000; // 1 hour expressed in milliseconds
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token nie może być zweryfikowany";
    public static final String COMPANY = "Telecom";
    public static final String IMS = "Inventory Management System";
    public static final String AUTHORITIES = "authorities";
    public static final String FORBIDDEN_MESSAGE = "Musisz się zalogować by oglądać tą stronę";
    public static final String ACCESS_DENIED_MESSAGE = "Nie masz uprawnień do oglądania tej strony";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String[] PUBLIC_URLS = {"/user/login", "/h2-console/**"};

}
