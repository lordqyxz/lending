package com.p2p.lending.dao;

import java.util.List;
import java.util.Map;

import com.p2p.lending.pojo.Certifrecord;

public interface CertifrecordDao extends BaseDao<Object, Object> {

	public List<Certifrecord> queryCertifrecord(Map<String, Object> map);

	public int updateCertifrecord(Map<String, Object> map);

	public int addCertifrecord(Map<String, Object> map);

}
