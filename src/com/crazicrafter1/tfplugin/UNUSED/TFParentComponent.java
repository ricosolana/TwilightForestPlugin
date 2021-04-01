package com.crazicrafter1.tfplugin.generation.structure;

import java.util.ArrayList;

public interface TFParentComponent {

    ArrayList<TFComponent> children = new ArrayList<>();

    ArrayList<TFComponent> getChildren();

    void addChild(TFComponent child);

}
