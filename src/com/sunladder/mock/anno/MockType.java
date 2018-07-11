package com.sunladder.mock.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface MockType {

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface define {

    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface text {
        TypeText type() default TypeText.NORMAL;
    }

    enum TypeText {
        SHORT(10),
        NORMAL(100),
        LONG(1000);

        private int length;

        TypeText(int length) {
            this.length = length;
        }

        public int getLength() {
            return length;
        }
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface img {
        TypeImg type() default TypeImg.SMALL;
    }

    enum TypeImg {
        SMALL("small"),
        NORMAL("normal"),
        BIG("big");

        private String url;

        TypeImg(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface list {
        int size() default 10;
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface map {
        int size() default 10;
    }
}
