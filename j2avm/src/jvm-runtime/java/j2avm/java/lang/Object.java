package j2avm.java.lang;

import org.epistem.j2avm.annotations.runtime.FlashTargetClass;
import org.epistem.j2avm.annotations.runtime.Translator;
import org.epistem.j2avm.asm.AVM2_ASM;
import org.epistem.j2avm.translator.impl.framework.JavaFrameworkClassAugmentingTranslator;

import static flash.Flash.*;
import flash.FlashObject;
import flash.utils.Dictionary;

/**
 * Runtime support for Object
 *
 * @author nickmain
 */
@Translator( JavaFrameworkClassAugmentingTranslator.class )
@FlashTargetClass( FlashObject.class )
public class Object {
    
    private static int nextHash = 1;
    private static Dictionary hashCodes = new Dictionary( true );
    
    /** @see java.lang.Object#equals(java.lang.Object) */
    public boolean equals( java.lang.Object obj ) {
        return this == obj;
    }

    /** @see java.lang.Object#hashCode() */
    public int hashCode() {
//        trace( "hashCode()" );
        
        AVM2_ASM.getPublicProperty( hashCodes, this );
//        AVM2_ASM.traceStackTop();
        int hash = AVM2_ASM.popInt();
//        trace( "hash 1= " + hash );
        if( hash < 1 ) { // cannot do hash == 0 since undefined == 0 is false
//            trace( "new hash" );
            hash = nextHash++;
//            trace( "hash 2= " + hash );
            
            if( nextHash > 10000 ) nextHash = 1;
            AVM2_ASM.setPublicProperty( hashCodes, this, hash );            
        }
        
        return hash;
    }

    /** @see java.lang.Object#toString() */
    public java.lang.String toString() {
        return "java.lang.Object@" + hashCode();
    }
}
