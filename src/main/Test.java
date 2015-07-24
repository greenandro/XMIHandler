/*
 * Test
 * ruicouto in 24/jul/2015
 */
package main;

import xmi.metamodel.XMI;

/**
 *
 * @author ruicouto
 */
public class Test {
    public static void main(String[] args) throws Exception {
        XMI xmi = Parser.parse("state.xmi", HandlerFactory.getHandler(HandlerFactory.ARGOUML));
        System.out.println(xmi.toXmi());
    }
}
