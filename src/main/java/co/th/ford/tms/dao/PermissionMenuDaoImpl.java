package co.th.ford.tms.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import co.th.ford.tms.model.PermissionMenu;

@Repository("permissionMenuDao")

public class PermissionMenuDaoImpl extends AbstractDao <Integer,PermissionMenu> implements PermissionMenuDao{
	
	@SuppressWarnings("unchecked")
	public List<PermissionMenu> getPermissionMenu(int idroless){
		Query query = getSession().createSQLQuery("select pe.permission_id,pe.id_roles,pe.id_menu,me.list_menu,me.menu_id from tb_permission pe inner join tb_menu me "
				+ "on pe.id_menu = me.menu_id where pe.id_roles = :idroless ");
		
		query.setInteger("idroless", idroless);
		
		List<PermissionMenu> allList = new ArrayList<PermissionMenu>();
		List<Object[]> list=query.list();
		if(list!=null && list.size() >0)
			for (Object[] obj : list) {
				PermissionMenu permissionMenu = new PermissionMenu();
				
				permissionMenu.setPermission_id(obj[0]==null?0:((Integer)obj[0]).intValue());
				permissionMenu.setId_roles(obj[1]==null?0:((Integer)obj[1]).intValue());
				permissionMenu.setId_menu(obj[2]==null?0:((Integer)obj[2]).intValue());
				permissionMenu.setList_menu(obj[3]==null?"":obj[3].toString());
				permissionMenu.setMenu_id(obj[4]==null?0:((Integer)obj[4]).intValue());
				allList.add(permissionMenu);
				//System.out.println("T#E#S#T#Q#U#E#R#Y"+permissionMenu);
		
			}
		
		return allList;
	}
	
	

}
