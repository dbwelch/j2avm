package org.epistem.jvm.code.analysis;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.epistem.jvm.code.*;

/**
 * Utility for walking a code graph
 *
 * @author nickmain
 */
public abstract class CodeWalker<INFO> {

    private final InstructionList list;    
    private final LinkedList<Visit<INFO>> queue = 
        new LinkedList<Visit<INFO>>();
    
    private class Visit<INFO_> {
        private final Instruction instruction;
        private final INFO_   info;
        Visit( Instruction instruction, INFO_ info ) {
            this.instruction = instruction;
            this.info        = info;
        }
    }
    
    /**
     * Seed the visit queue with the first instruction in the list
     */
    protected CodeWalker( InstructionList list, INFO info ) {
        this.list = list;
        Instruction first = list.first();
        if( first == null ) return;
        
        enqueue( first, info );
    }
    
    /**
     * Perform the walk until the queue is empty
     */
    protected final void execute() {
        while( ! queue.isEmpty() ) {
            Visit<INFO> visit = queue.removeFirst();
            Instruction instruction = visit.instruction;
            
            INFO info = visit( instruction, visit.info );
            
            if( info == null ) continue;
            
            //enqueue follow-on instructions
            if( instruction instanceof BranchInstruction ) {
                BranchInstruction branch = (BranchInstruction) instruction;
                Set<String> targets = new HashSet<String>();
                branch.gatherLabels( targets );
                
                for( String label : targets ) {
                    Label labelInsn = list.labelForName( label );
                    //assume this is not null
                    
                    enqueue( labelInsn, info );
                }
            }
            
            if(! (instruction instanceof AbrubtFlow )) {
                Instruction next = instruction.next();
                if( next != null ) {
                    enqueue( next, info );
                }
            }
        }
    }
    
    /**
     * Enqueue a visit
     */
    protected final void enqueue( Instruction instruction, INFO info ) {
        Visit<INFO> visit = new Visit<INFO>( instruction, info );
        queue.add( visit );
    }
    
    /**
     * Visit an instruction and return the info that is included in visits to
     * the downstream instruction(s)
     * 
     * @param instruction the instruction to visit
     * @param info the info for the visit
     * 
     * @return may be null to skip downstream visits
     */
    protected abstract INFO visit( Instruction instruction, INFO info );  
    
    
}
