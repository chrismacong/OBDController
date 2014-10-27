package com.fix.obd.web.dao;

import java.util.List;

import com.fix.obd.web.model.MemberNotice;

public interface MemberNoticeDao {
	public List<MemberNotice> getAllMemberNotice();

	public void addMemberNotice(MemberNotice memberNotice);

	public void removeAllMemberNotice();

	public void deleteMemberNotice(MemberNotice memberNotice);

	public void updateMemberNotice(MemberNotice memberNotice);

	public List<MemberNotice> findByHQL(String hql) throws Exception;
}
