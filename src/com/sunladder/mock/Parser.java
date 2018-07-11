package com.sunladder.mock;

import com.sunladder.mock.anno.MockType;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public final class Parser {

    public static void parseInstance(Object object, Field field) {
        field.setAccessible(true);
        MockType.define userDefine = field.getAnnotation(MockType.define.class);
        if (userDefine == null) {
            Parser.parseText(object, field);
            Parser.parseImg(object, field);
            Parser.parseList(object, field);
            Parser.parseMap(object, field);
        }
    }

    private static void parseText(Object object, Field field) {
        MockType.text annoText = field.getAnnotation(MockType.text.class);
        if (annoText != null && field.getType() == String.class) {
            try {
                field.set(object, annoText.type().getLength() + "");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static void parseImg(Object object, Field field) {
        MockType.img annoImg = field.getAnnotation(MockType.img.class);
        if (annoImg != null && field.getType() == String.class) {
            try {
                field.set(object, annoImg.type().getUrl());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static void parseList(Object object, Field field) {
        MockType.list annoList = field.getAnnotation(MockType.list.class);
        if (annoList != null) {
            int size = annoList.size();
            ArrayList<Object> newList = new ArrayList<>(size);

            Type genericType = field.getGenericType();
            ParameterizedType parameterizedType = genericType instanceof ParameterizedType
                    ? ((ParameterizedType) genericType)
                    : null;
            Class aClass = (Class) parameterizedType.getActualTypeArguments()[0];

            try {
                for (int i = 0; i < size; i++) {
                    newList.add(aClass.newInstance());
                }

                field.set(object, newList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void parseMap(Object object, Field field) {
        MockType.map annoMap = field.getAnnotation(MockType.map.class);
        if (annoMap != null) {
            int size = annoMap.size();
            HashMap<Object, Object> newMap = new HashMap<>(size);

            Type genericType = field.getGenericType();
            ParameterizedType parameterizedType = genericType instanceof ParameterizedType
                    ? ((ParameterizedType) genericType)
                    : null;

            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            System.out.println(actualTypeArguments.length + " " + Arrays.toString(actualTypeArguments));
            System.out.println("-------------");
            Class aClass = (Class) actualTypeArguments[1];

            try {
                for (int i = 0; i < size; i++) {
                    newMap.put(String.valueOf(i), aClass.newInstance());
                }

                field.set(object, newMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
