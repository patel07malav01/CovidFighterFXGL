package com.almasb.fxglgames.covidfighter.components.Vaccine;

import com.almasb.fxglgames.covidfighter.EntityType;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.component.Required;
import com.almasb.fxglgames.covidfighter.components.OwnerComponent;

@Required(OwnerComponent.class)
public class LeftVaccineComponent extends Component {

    private OwnerComponent owner;

    private double speed;

    public LeftVaccineComponent(double speed) {
        this.speed = speed;
    }

    @Override
    public void onUpdate(double tpf) {
        entity.translateX(owner.getValue() == (EntityType.PLAYER) ? -tpf * speed : tpf * speed);
    }
}