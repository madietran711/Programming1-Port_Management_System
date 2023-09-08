package service;

import java.util.Map;

public interface Courier {
    public boolean loadContainer();

    public boolean unloadContainer();

    public boolean move();

    public boolean refuel();

    public Map<entities.Container, Integer> getTotalContainerWithType();

    public int getTotalContainer();

    public boolean canMoveToPortWithCurrentLoad();
}
