package org.epistem.jvm.code;


/**
 * A branch target label
 *
 * @author nickmain
 */
public final class Label extends Instruction {

    private String name;
    
    /**
     * @param name the target name - unique within an instruction list
     */
    public Label( String name ) {
        this.name = name;
    }
    
    /** @see org.epistem.jvm.code.Instruction#accept(org.epistem.jvm.code.InstructionVisitor) */
    @Override
    public void accept( InstructionVisitor visitor ) {
        visitor.visitLabel( this );
    }
    /**
     * Get the label name
     */
    public String name() {
        return name;
    }
}
