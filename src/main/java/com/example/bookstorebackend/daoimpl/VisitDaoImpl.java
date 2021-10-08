package com.example.bookstorebackend.daoimpl;

import com.example.bookstorebackend.dao.VisitDao;
import com.example.bookstorebackend.entity.HomeVisit;
import com.example.bookstorebackend.repository.HomeVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class VisitDaoImpl implements VisitDao {
    @Autowired
    private HomeVisitRepository homeVisitRepository;
    @Override
    public void addCount(Integer count){
        HomeVisit homeVisit=homeVisitRepository.getOne(1);
        Date time=new Date();
        homeVisit.setTime(time);
        homeVisit.setCounts(count);
        homeVisitRepository.save(homeVisit);
    }

    @Override
    public Integer getCount(){
        return homeVisitRepository.getOne(1).getCounts();
    }

}
