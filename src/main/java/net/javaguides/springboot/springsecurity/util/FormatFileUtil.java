package net.javaguides.springboot.springsecurity.util;

import org.springframework.stereotype.Component;

@Component
public class FormatFileUtil {

    public String getFormat(String fileName) {
        int index = fileName.indexOf(".");
        return fileName.substring(index + 1);
    }
}
