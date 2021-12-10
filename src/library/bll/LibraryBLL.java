package library.bll;

import library.dal.AbstractDAL;

public abstract class LibraryBLL {
	protected AbstractDAL dal;
	
	public String[] getDisplayColumnNames() {
		return dal.getPrettyColumnNames();
	}
	
	public String[] getMethodNames() {
		return dal.getMethodNames();
	}
}
