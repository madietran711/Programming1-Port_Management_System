package service;

import java.util.Map;

public interface Courier {
    public void loadContainer();

    public void unloadContainer();

    public void move();

    public void refuel();

    // for the 4 methods above, shouldn't it return a boolean value to verify if the
    // method can be carried out
    public Map<entities.Container, Integer> getTotalContainerWithType();

    public int getTotalContainer();

    public boolean canMoveToPortWithCurrentLoad();
}
