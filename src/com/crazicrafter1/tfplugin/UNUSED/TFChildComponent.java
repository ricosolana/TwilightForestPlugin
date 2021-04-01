package com.crazicrafter1.tfplugin.generation.structure;

public abstract class TFChildComponent extends TFComponent {

    private TFParentComponent parent;

    public TFChildComponent(int x, int y, int z, ComponentFace face, TFParentComponent parent) {
        super(x, y, z, face);
        this.parent = parent;
    }

    // iterate later on
    public TFParentComponent getParent() {
        return parent;
    }



}
