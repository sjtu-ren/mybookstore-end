package com.example.bookstorebackend.serviceimpl;

import com.example.bookstorebackend.dao.VisitDao;
import com.example.bookstorebackend.service.HomeVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeVisitServiceImpl implements HomeVisitService {
    @Autowired
    private VisitDao visitDao;

    @Override
    public void updateCount(Integer count){visitDao.addCount(count);}

    @Override
    public Integer getCount(){
        return visitDao.getCount();
    }

}
