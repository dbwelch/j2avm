package org.epistem.j2avm.bytecode;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.objectweb.asm.tree.analysis.Analyzer;
import org.objectweb.asm.tree.analysis.Interpreter;

/**
 * An analyzer that also builds a control flow graph
 *
 * @author nickmain
 */
public class ControlFlowAnalyzer extends Analyzer {

    private final Map<Integer, Collection<Integer>> edges = new HashMap<Integer, Collection<Integer>>();
    
    public ControlFlowAnalyzer( Interpreter interpreter ) {
        super( interpreter );
    }

    /**
     * Get the outgoing edges from a given instruction
     * 
     * @param insn the instruction index
     * @return collection of target instruction indices
     */
    public Collection<Integer> edgesFromInsn( int insn ) {
        Collection<Integer> insns = edges.get( insn );
        if( insns == null ) {
            edges.put( insn, insns = new HashSet<Integer>() );
        }
        
        return insns;
    }
    
    /** @see org.objectweb.asm.tree.analysis.Analyzer#newControlFlowEdge(int, int) */
    @Override
    protected void newControlFlowEdge( int fromInsn, int toInsn ) {
        edgesFromInsn( fromInsn ).add( toInsn );
    }

    /** @see org.objectweb.asm.tree.analysis.Analyzer#newControlFlowExceptionEdge(int, int) */
    @Override
    protected boolean newControlFlowExceptionEdge( int fromInsn, int toInsn ) {
        newControlFlowEdge( fromInsn, toInsn );
        return true;
    }
}
