package org.epistem.jvm.code;

import java.util.Collection;

import org.epistem.jvm.type.ObjectType;

/**
 * An exception handler
 *
 * @author nickmain
 */
public final class ExceptionHandler implements LabelTargetter {

    public final String start;
    public final String end;
    public final String handler;
    public final ObjectType exceptionType;
    
    public ExceptionHandler( String start, String end, 
                             String handler, ObjectType exceptionType ) {
        this.start   = start;
        this.end     = end;
        this.handler = handler;
        this.exceptionType = exceptionType;
    }

    /** @see org.epistem.jvm.code.LabelTargetter#gatherLabels(java.util.Collection) */
    public void gatherLabels( Collection<String> labels ) {
        labels.add( start );
        labels.add( end );
        labels.add( handler );        
    }
}
