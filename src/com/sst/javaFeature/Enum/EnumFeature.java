package com.sst.javaFeature.Enum;

import java.util.EnumMap;
import java.util.HashMap;

/**
 * @author shui 实例通过一个标识来获取枚举的实例,而不是通过遍历对比
	父类Enum中有final类型的name字段和final类型的name()方法，所以添加name字段是不会有什么影响的
 */

enum SexEnum implements EnumMessage{
    MAN("M", "男"), WOMAN("F", "女");//后加；，且必须在最前边

    private String code;
    private String desc;

    /**
		tij中说构造方法必须是package或者private
     * @param code
     * @param desc
     */
    SexEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /** 
    * @author : sst HJ
    * @date 创建时间：2018年1月12日 下午6:10:03 
    * @version 1.0 
    * @param code
    * @return 
    * @since   
    * @方法说明：以这种方案实现时，需要在每个枚举类中都定义类似上述结构的方法。当项目中的枚举类较多时，显得代码冗余。
    */
    public static SexEnum getSexEnumByCode(String code) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (code.equals(sexEnum.getCode())) {
                return sexEnum;
            }
        }
        return null;
    }
    @Override
    public String toString() {
        return super.toString();//返回的是常量的值
    }

    @Override
    public Object getValue() {
        //此处需要根据枚举对象的哪个属性返回枚举对象，就return该属性
        return desc;
    }

}

public class EnumFeature {
    public static void main(String[] args) {
    	for(SexEnum sexEnum : SexEnum.values()){
    		System.out.println(sexEnum + " ===" + sexEnum.name());
    	}
        SexEnum male = SexEnum.getSexEnumByCode("M");
        System.out.println(male);
        //SexEnum zEnum = new SexEnum("aaa","aaa");//不能初始化
        System.out.println(EnumUtil.getEnumObject("女", SexEnum.class));//MAN
        
        
        HashMap<String, Object> map = new HashMap<String,Object>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);
        map.put("5", 5);
    }
}
