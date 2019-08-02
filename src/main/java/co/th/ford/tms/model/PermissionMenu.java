package co.th.ford.tms.model;

import lombok.Data;

@Data

public class PermissionMenu {
	
	private int permission_id;
	private int id_roles;	
	private int id_menu;
	private String list_menu;		
	private int menu_id;		


}
