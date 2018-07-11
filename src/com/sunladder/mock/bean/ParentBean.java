package com.sunladder.mock.bean;


import com.sunladder.mock.anno.MockType;

import java.util.List;
import java.util.Map;

public class ParentBean extends BaseBean {

    @MockType.text()
    String textDefault;

    @MockType.text(type = MockType.TypeText.SHORT)
    String textShort;

    @MockType.text(type = MockType.TypeText.NORMAL)
    String textNormal;

    @MockType.text(type = MockType.TypeText.LONG)
    String textLong;

    @MockType.img()
    String imgDefault;

    @MockType.img(type = MockType.TypeImg.SMALL)
    String imgSmall;

    @MockType.img(type = MockType.TypeImg.NORMAL)
    String imgNormal;

    @MockType.img(type = MockType.TypeImg.BIG)
    String imgBig;

    @MockType.list
    List<String> listDefault;

    @MockType.list(size = 20)
    List<String> list20;

    @MockType.map
    Map<String, String> mapDefault;

    @MockType.map(size = 20)
    Map<String, String> map20;

    /*with child*/
    @MockType.list
    List<ChildBean> listChild;

    @MockType.define
    @MockType.map
    Map<String, ChildBean> mapChild;
}
