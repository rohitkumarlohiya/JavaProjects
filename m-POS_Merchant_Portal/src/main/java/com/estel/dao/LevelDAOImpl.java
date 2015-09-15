package com.estel.dao;

import com.estel.entity.Level;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class LevelDAOImpl extends GenericDAOImpl<Level, Long> implements LevelDAO {


    @Transactional
    public Level addLevel(Level Level) {
        return (Level) this.read(this.create(Level));
    }


    @Transactional
    public Level getLevelById(Long Id) {
        return (Level) this.read(Id);
    }


    @SuppressWarnings("unchecked")
    @Transactional
    public List<Level> listLevels() {
        return getSession().createQuery("from Level").list();
    }


    @Transactional
    public void updateLevel(Level Level) {
        getSession().update(Level);

    }

}
