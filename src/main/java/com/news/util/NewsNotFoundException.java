package com.news.util;

import java.io.IOException;

public class NewsNotFoundException extends IOException {
    public NewsNotFoundException(String msg) {
        super(msg);
    }
}
