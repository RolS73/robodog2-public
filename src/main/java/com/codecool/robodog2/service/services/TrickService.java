package com.codecool.robodog2.service.services;

import com.codecool.robodog2.dao.interfaces.TrickDao;
import com.codecool.robodog2.model.Trick;
import com.codecool.robodog2.service.DaoType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrickService {

    private DaoType daoType = DaoType.DATABASE;
    private TrickDao activeDao;

    public TrickService(TrickDao dao) {
        activeDao = dao;
    }

    public void addTrick(Trick trick) {
        activeDao.addTrick(trick);
    }

    public List<Trick> listTricks() {
        return activeDao.listTricks();
    }

    public Trick getTrick(long id) {
        return activeDao.getTrick(id);
    }

    public void updateTrick(Trick trick, long id){
        activeDao.updateTrick(trick, id);
    }

    public void deleteTrick(long id){
        activeDao.deleteTrick(id);
    }
}
