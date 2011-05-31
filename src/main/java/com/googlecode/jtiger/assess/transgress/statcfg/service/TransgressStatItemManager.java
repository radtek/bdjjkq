package com.googlecode.jtiger.assess.transgress.statcfg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.googlecode.jtiger.assess.transgress.statcfg.StatCfgConstants;
import com.googlecode.jtiger.assess.transgress.statcfg.model.TransgressAction;
import com.googlecode.jtiger.assess.transgress.statcfg.model.TransgressStatItem;
import com.googlecode.jtiger.assess.transgress.statcfg.model.TransgressType;
import com.googlecode.jtiger.core.service.BaseGenericsManager;

/**
 * 统计项Manager
 * 
 * @author DELPHI
 * 
 */
@Service
public class TransgressStatItemManager extends
		BaseGenericsManager<TransgressStatItem> {
	/**
	 * 根据id得到违法类别
	 */
	public TransgressType getTransgressTypeById(String id) {
		return getDao().get(TransgressType.class, id);
	}

	/**
	 * 得到所有一级类别
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TransgressType> getFirstLevelTypes() {
		String hql = "from TransgressType tt where tt.type = ? order by tt.code";

		return getDao().query(hql, StatCfgConstants.TRANSGRESSS_TYPE_1);
	}

	/**
	 * 根据一级类别得到二级类别
	 * 
	 * @param firstLevelTypeId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TransgressType> getSecondLevelTypesByFirstLevel(
			String firstLevelTypeId) {
		String hql = "from TransgressType tt where tt.parentTransgressType.id = ? order by tt.code";

		return getDao().query(hql, firstLevelTypeId);
	}

	/**
	 * 根据二级类别得到违法行为
	 * 
	 * @param secondLevelTypeId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TransgressAction> getTransgressActionsBySecondLevelType(
			String secondLevelTypeId) {
		String hql = "from TransgressAction ta where ta.transgressType.id = ? order by ta.code";

		return getDao().query(hql, secondLevelTypeId);
	}

	/**
	 * 根据id得到违法行为
	 * 
	 * @param id
	 * @return
	 */
	public TransgressAction getTransgressActionById(String id) {
		String hql = "from TransgressAction ta where ta.id = ?";
		return getDao().get(TransgressAction.class, id);
	}
}
