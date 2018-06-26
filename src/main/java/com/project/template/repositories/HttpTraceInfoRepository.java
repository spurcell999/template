package com.project.template.repositories;

import com.project.template.domain.HttpTraceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;


@Repository
@Transactional
public interface HttpTraceInfoRepository extends JpaRepository<HttpTraceInfo, String> {
    HttpTraceInfo findBySession(String session) throws NoResultException;
    HttpTraceInfo findByOpenApiRequestId(String openApiRequestId) throws NoResultException;
    List<HttpTraceInfo> findByPartnerId(String partnerId);
}
