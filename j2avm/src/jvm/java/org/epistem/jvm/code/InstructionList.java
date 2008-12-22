package org.epistem.jvm.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A list of instructions and exception handlers
 *
 * @author nickmain
 */
public final class InstructionList {

    private Instruction first;
    private Instruction last;
    private int count;
    private Map<String, Label> labels = new HashMap<String, Label>();
    
    /**
     * The exception handlers - in order from most specific to least
     */
    public List<ExceptionHandler> exceptionHandlers = new ArrayList<ExceptionHandler>();
    
    /**
     * Get the instruction count
     */
    public int size() {
        return count;
    }
    
    /**
     * Get the first instruction
     * 
     * @return may be null
     */
    public Instruction first() {
        return first;
    }
    
    /**
     * Get the last instruction
     * 
     * @return may be null
     */
    public Instruction last() {
        return last;
    }
    
    /**
     * Get the label with the given name
     * @param name the label name
     * @return null if the label does not exist
     */
    public Label labelForName( String name ) {
        return labels.get( name );
    }
    
    /**
     * Append an instruction
     */
    public void append( Instruction instruction ) {
        insertAfter( last, instruction );
    }
    
    /**
     * Insert a new instruction after a preceding one
     * 
     * @param existing the existing instruction to insert after
     * @param newInstruction the new instruction
     */
    public void insertAfter( Instruction existing, Instruction newInstruction ) {
        if( existing != null && existing.list != this ) {
            throw new IllegalArgumentException( "existing instruction not part of this list" );
        }
        if( newInstruction.list != null ) newInstruction.remove();
        if( newInstruction.list == this ) return;
        
        Instruction prev = existing;
        Instruction next = (existing != null ) ? existing.next : first;
        
        newInstruction.list = this;
        newInstruction.prev = prev;
        newInstruction.next = next;
        
        if( prev != null ) {
            prev.next = newInstruction;
        }
        else {
            first = newInstruction;
        }
 
        if( next != null ) {
            next.prev = newInstruction;
        }
        else {
            last = newInstruction;
        }
        
        count++;
        
        if( newInstruction instanceof Label ) {
            Label label = (Label) newInstruction;
            labels.put( label.name(), label );
        }
    }
    
    /*pkg*/ void remove( Instruction instruction ) {
        if( instruction.list != this ) return;
        
        Instruction prev = instruction.prev;
        Instruction next = instruction.next;
     
        instruction.list = null;
        instruction.next = null;
        instruction.prev = null;
        
        if( prev != null ) {
            prev.next = next;
        }
        else {
            first = next;
        }
        
        if( next != null ) {
            next.prev = prev;
        }
        else {
            last = prev;
        }
        
        count--;
    }
    
    /**
     * Accept a visitor
     */
    public void accept( InstructionVisitor visitor ) {
        visitor.visitStart( this );
    
        for( Instruction i = first; i != null; i = i.next ) {
            i.accept( visitor );
        }        
        
        visitor.visitHandlers();        
        for( ExceptionHandler handler : exceptionHandlers ) {
            visitor.visitHandler( handler );
        }
        
        visitor.visitEnd();
    }
    
    //TODO: variables
}
