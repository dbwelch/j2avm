package org.epistem.j2avm.bytecode;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.analysis.AnalyzerException;
import org.objectweb.asm.tree.analysis.Frame;
import org.objectweb.asm.tree.analysis.SourceInterpreter;

/**
 * Analyzes a method to determine the frames and the local variable lifespans.
 *
 * @author nickmain
 */
public class MethodAnalyzer {

    private final ClassNode owner;
    private final MethodNode method;
    private final ControlFlowAnalyzer analyzer;
    private final Frame[] frames;
    
    /**
     * @param owner the owning class
     * @param method the method to analyze
     */
    public MethodAnalyzer( ClassNode owner, MethodNode method ) 
        throws AnalyzerException {
        
        this.owner  = owner;
        this.method = method;
        
        analyzer = new ControlFlowAnalyzer( new SourceInterpreter() );
        analyzer.analyze( owner.name.replace( '.', '/' ), method );
        frames = analyzer.getFrames();
    }
    
    /**
     * Get the frame for a given instruction index
     */
    public Frame frameFor( int insn ) {
        return frames[insn];
    }
}
