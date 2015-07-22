/*
 * HandlerFactory
 * ruicouto in 22/jul/2015
 */
package main;

import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author ruicouto
 */
public class HandlerFactory {
    
    /**
     * Parser for argoUML
     */
    public static final int ARGOUML = 1;
    
    /**
     * Get a parser handler. Use the static variables to specify the kind
     * @param kind
     * @return 
     */
    public static DefaultHandler getHandler(int kind) {
        DefaultHandler dh = null;
        switch(kind) {
            case ARGOUML:
                dh = new ArgoUMLHandler();
                break;
        }
        return dh;
    }
    
}
