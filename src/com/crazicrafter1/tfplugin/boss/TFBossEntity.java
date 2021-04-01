package com.crazicrafter1.tfplugin.boss;

import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public abstract class TFBossEntity {// extends TFBoss {

    private Entity holder;

    protected TFBossEntity(Entity entity) {
        this.holder = entity;
    }

    public abstract void update();

    protected Entity getHolder() {
        return this.holder;

    }

    protected Vector getTargetVect(Entity other) {
        return holder.getLocation().toVector().multiply(-1).add(other.getLocation().toVector());
    }
}
