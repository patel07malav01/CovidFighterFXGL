package com.almasb.fxglgames.covidfighter.components.Arrow;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.component.Required;
import com.almasb.fxglgames.covidfighter.EntityType;
import com.almasb.fxglgames.covidfighter.components.OwnerComponent;

@Required(OwnerComponent.class)
public class DownArrowComponent extends Component {

    private OwnerComponent owner;

    private double speed;

    public DownArrowComponent(double speed) {
        this.speed = speed;
    }

    @Override
    public void onUpdate(double tpf) {
        entity.translateY(owner.getValue() == (EntityType.PLAYER) ? tpf * speed : -tpf * speed);
    }
}