package com.estel.dao;

import com.estel.entity.Level;

import java.util.List;

public interface LevelDAO extends GenericDAO<Level, Long> {

    public Level addLevel(Level Level);
    public Level getLevelById(Long Id);
    public List<Level> listLevels();
    public void updateLevel(Level Level);
}
