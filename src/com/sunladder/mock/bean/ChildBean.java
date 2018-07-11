package com.sunladder.mock.bean;

import com.sunladder.mock.anno.MockType;

import java.util.List;
import java.util.Map;

public class ChildBean extends BaseBean {

    @MockType.img(type = MockType.TypeImg.BIG)
    String imgBig;

    @MockType.list
    List<String> listDefault;

    @MockType.map
    Map<String, String> mapDefault;
}
