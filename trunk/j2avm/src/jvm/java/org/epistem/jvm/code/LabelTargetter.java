package org.epistem.jvm.code;

import java.util.Collection;


/**
 * Implemented by instructions and exception blocks that target labels
 *
 * @author nickmain
 */
public interface LabelTargetter {

    /**
     * Gather all the targetted labels into the given collection
     */
    public void gatherLabels( Collection<String> labels );
}
