package com.news.util;

import java.io.IOException;

public class NewsTypeNotFoundException extends IOException {
    public NewsTypeNotFoundException(String msg) {
        super(msg);
    }
}
