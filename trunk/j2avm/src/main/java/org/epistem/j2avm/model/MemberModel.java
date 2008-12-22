package org.epistem.j2avm.model;

/**
 * Base for member models
 *
 * @author nickmain
 */
public abstract class MemberModel extends AnnotatedModel {

    /**
     * The owning class.
     */
    public final ClassModel classModel;
    
    protected MemberModel( ClassModel classModel ) {
        this.classModel = classModel;
    }
}
