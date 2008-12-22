package org.epistem.jvm.code.analysis;

/**
 * A reference to a variable
 *
 * @author nickmain
 */
public class VariableRef {

    private Variable var;
    
    /**
     * Get the referenced variable
     */
    public Variable variable() {
        return var;
    }
    
    /*pkg*/ public VariableRef( Variable var ) {
        setVar( var );
    }
    
    /*pkg*/ void setVar( Variable var ) {
        this.var = var;
    }
    
}
