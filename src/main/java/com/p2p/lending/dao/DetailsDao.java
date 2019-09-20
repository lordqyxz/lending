package com.p2p.lending.dao;

import java.util.List;

import com.p2p.lending.entity.Details;

public interface DetailsDao extends  BaseDao<Object, Details>  {
	public List<Details> detailslist(Integer ids);

	public void detailsadd(Details notice);
	
	public Details detailsget(Integer ids);
	
	public void detailsdel(Integer ids);
	public void deleteByPid(Integer pid);
	
	/*


	public void detailsupd(Details notice);

	*/

}
