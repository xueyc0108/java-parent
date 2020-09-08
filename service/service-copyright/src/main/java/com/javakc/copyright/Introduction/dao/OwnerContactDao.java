package com.javakc.copyright.Introduction.dao;

import com.javakc.commonutils.jpa.base.dao.BaseDao;
import com.javakc.copyright.Introduction.entity.OwnerContact;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OwnerContactDao extends BaseDao<OwnerContact,Integer> {

    @Query("select o.id from Owner o where o.copyrightName like %?1%")
    public Integer findIdByName(String name);

    @Query("select o from OwnerContact o where o.owner.copyrightName = ?1")
    public List<OwnerContact> findOwnerContactList(String copyrightName);

}
