/*
 * XMISerializer
 * ruicouto in 21/jul/2015
 */
package xmi.util;

import java.lang.reflect.Field;

/**
 * Helper class to serialize objects to XMI.
 * Still in tests
 * @author ruicouto
 */
public class XMISerializer {
    
    /**
     * XMISerializer is a helper class
     */
    private XMISerializer() {}
    
    /**
     * Given an object instance, serializes the object to XMI.
     * Still under development
     * @param o
     * @return 
     */
    public static String serialize(Object o) {
        System.out.println("--serialize");
        String res = "<";
        res += (o.getClass().getSimpleName().startsWith("UML")?"UML":"XMI")+":"+o.getClass().getSimpleName().substring(3);
        res += " ";
        for(Field f : o.getClass().getDeclaredFields()) {
            try {
                f.setAccessible(true);
                Object value = f.get(o);
                res += f.getName()+"='"+value+"' ";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(res);
        System.out.println("--end serialize");
        return "";
    }
}
