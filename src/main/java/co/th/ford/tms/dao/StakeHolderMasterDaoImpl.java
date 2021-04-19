package co.th.ford.tms.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.th.ford.tms.model.Report1;
import co.th.ford.tms.model.ReportStakeHolder;
import co.th.ford.tms.model.StakeHolderMaster;
import co.th.ford.tms.model.Truck;

@Repository("StakeHolderMasterDao")
public class StakeHolderMasterDaoImpl extends AbstractDao<Integer, StakeHolderMaster> implements StakeHolderMasterDao {

	@SuppressWarnings("unchecked")
	public List<StakeHolderMaster> findAllStakeHolder() {
		Criteria criteria = createEntityCriteria();
		return (List<StakeHolderMaster>) criteria.list();
	}
	
	public StakeHolderMaster findByStakeHolder(String StakeHolder) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("STAKE_HOLDER_ID", StakeHolder));
		//criteria.add(Restrictions.eq("STAKE_HOLDER_UN_ID", StakeHolder));
		return (StakeHolderMaster) criteria.uniqueResult();
	}
	
	public StakeHolderMaster findByStakeHolderName(String StakeHolder) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("STAKE_HOLDER_NAME", StakeHolder));
		return (StakeHolderMaster) criteria.uniqueResult();
	}
	
	public StakeHolderMaster findByStakeHolderId(int StakeHolder) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("STAKE_HOLDER_UN_ID", StakeHolder));
		return (StakeHolderMaster) criteria.uniqueResult();
	}
	
	@Override
	public void saveStakeHolder(StakeHolderMaster StakeHolder) {
		persist(StakeHolder);
	}
	

	 @SuppressWarnings("unchecked")
	 public List<ReportStakeHolder> displayAllReportStakeHolder(){
		 
		
			Query query = getSession().createSQLQuery(
					  " SELECT shm.STAKE_HOLDER_UN_ID, shm.STAKE_HOLDER_ID, shm.STAKE_HOLDER_TYPE, shm.STAKE_HOLDER_SALE_TYPE, st.SALE_TYPE_NAME, "
					+ " shm.STAKE_HOLDER_NAME, shm.STAKE_HOLDER_ADDRESS, shm.STAKE_HOLDER_TEL_NO, shm.STAKE_HOLDER_FAX_NO, shm.STAKE_HOLDER_MOBILE_NO, "
					+ " shm.STAKE_HOLDER_EMAIL, shm.STAKE_HOLDER_PROJECT, p.PROJECT_NAME_NAME, shm.STAKE_HOLDER_STATUS "
					+ " FROM tb_stake_holder_master shm, tb_sale_type st, tb_project_name p "
					+ " WHERE shm.STAKE_HOLDER_SALE_TYPE = st.SALE_TYPE_ID "
					+ " AND shm.STAKE_HOLDER_PROJECT = p.PROJECT_NAME_ID ");		
						

			List<ReportStakeHolder> allList = new ArrayList<ReportStakeHolder>();
			List<Object[]> list=query.list();
			if(list!=null && list.size() >0)
				for (Object[] obj : list) {
					ReportStakeHolder reportStakeHolder = new ReportStakeHolder();
					
					reportStakeHolder.setStakeHolderUnID(obj[0].toString());
					reportStakeHolder.setStakeHolderID(obj[1].toString());
					reportStakeHolder.setStakeHolderType(obj[2].toString());
					reportStakeHolder.setStakeHolderSaleTypeNo(obj[3]==null?"":obj[3].toString());
					reportStakeHolder.setStakeHolderSaleTypeName(obj[4]==null?"":obj[4].toString());
					reportStakeHolder.setStakeHolderName(obj[5]==null?"":obj[5].toString());
					reportStakeHolder.setStakeHolderAddress(obj[6]==null?"":obj[6].toString());
					reportStakeHolder.setStakeHolderTel(obj[7]==null?"":obj[7].toString());
					reportStakeHolder.setStakeHolderFax(obj[8]==null?"":obj[8].toString());
					reportStakeHolder.setStakeHolderMobile(obj[9]==null?"":obj[9].toString());
					reportStakeHolder.setStakeHolderEmail(obj[10]==null?"":obj[10].toString());
					reportStakeHolder.setStakeHolderProject(obj[11]==null?"":obj[11].toString());
					reportStakeHolder.setStakeHolderProjectName(obj[12]==null?"":obj[12].toString());
					reportStakeHolder.setStakeHolderStatus(obj[13]==null?"":obj[13].toString());

					
					allList.add(reportStakeHolder);
				}
			
			return allList;
		}
}
