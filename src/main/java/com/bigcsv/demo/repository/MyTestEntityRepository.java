package com.bigcsv.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bigcsv.demo.entity.MyTestEntity;
import java.util.stream.Stream;

import javax.persistence.QueryHint;


@Repository
public interface MyTestEntityRepository extends CrudRepository<MyTestEntity, Integer> {
	
	@Query("SELECT e FROM MyTestEntity e WHERE ownerId = :ownerId")
	@QueryHints(value = @QueryHint(name = "org.hibernate.fetchSize", value = "0"))
	Stream<MyTestEntity> findAllByOwnerId(@Param("ownerId") Integer ownerId);

}
