package com.example.task.Repository.Rep;

import com.example.task.Other.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SockRepository extends PagingAndSortingRepository<Socks,Long>, JpaSpecificationExecutor<Socks>, JpaRepository<Socks, Long> {



}
//"select b from Band b where b.name = :name and b.playersCount = :count"