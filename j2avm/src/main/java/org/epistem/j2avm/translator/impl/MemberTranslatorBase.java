package org.epistem.j2avm.translator.impl;

import org.epistem.j2avm.translator.ClassTranslator;
import org.epistem.j2avm.translator.MemberTranslator;

import com.anotherbigidea.flash.avm2.model.AVM2Name;
import com.anotherbigidea.flash.avm2.model.AVM2QName;

/**
 * Base for member translators
 *
 * @author nickmain
 */
public abstract class MemberTranslatorBase implements MemberTranslator {

    protected final ClassTranslator classTranslator;
    protected final String jvmName;
    protected final AVM2QName avm2Name;
    protected final AVM2Name avm2Type;
    
    /** @see org.epistem.j2avm.translator.MemberTranslator#getClassTranslator() */
    public final ClassTranslator getClassTranslator() {
        return classTranslator;
    }

    /** @see org.epistem.j2avm.translator.MemberTranslator#getJVMName() */
    public final String getJVMName() {
        return jvmName;
    }
    
    /** @see org.epistem.j2avm.translator.MemberTranslator#getAVM2Name() */
    public final AVM2QName getAVM2Name() {
        return avm2Name;
    }

    /** @see org.epistem.j2avm.translator.MemberTranslator#getAVM2Type() */
    public final AVM2Name getAVM2Type() {
        return avm2Type;
    }

    protected MemberTranslatorBase( ClassTranslator classTranslator, 
                                    String jvmName,
                                    AVM2QName avm2Name,
                                    AVM2Name avm2Type ) {
        this.classTranslator = classTranslator;
        this.jvmName         = jvmName;
        this.avm2Name        = avm2Name;
        this.avm2Type        = avm2Type;
    }
}
