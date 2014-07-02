package com.fix.obd.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.fix.obd.web.dao.YY_UserDao;
import com.fix.obd.web.model.YY_User;
import com.fix.obd.web.service.UserService;

@Component
public class UserServiceImpl implements UserService{
	@Resource
	private YY_UserDao userDao;
	public YY_UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(YY_UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public JSONObject getPersonalUserJSON() {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		json.put("total", 0);
		json.put("rows", null);
		try {
			List<YY_User> personal_user_list = userDao.findByHQL("from YY_User where role = 'member'");
			if(personal_user_list.size()>0){
				JSONObject[] user_json_list = new JSONObject[personal_user_list.size()];
				for(int i=0;i<personal_user_list.size();i++){
					YY_User selected_user = personal_user_list.get(i);
					JSONObject selected_json = new JSONObject();
					selected_json.put("email", selected_user.getEmail());
					selected_json.put("realname", selected_user.getRealname());
					selected_json.put("nickname", selected_user.getNickname());
					selected_json.put("obdnumber", selected_user.getObdnumber());
					selected_json.put("carnumber", selected_user.getCarnumber());
					selected_json.put("tel",selected_user.getTel());
					selected_json.put("idnumber", selected_user.getIdnumber());
					selected_json.put("cartype", selected_user.getCartype());
					user_json_list[i] = selected_json;
				}
				json.clear();
				json.put("total",personal_user_list.size());
				json.put("rows", user_json_list);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public JSONObject getBusinessUserJSON() {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		json.put("total", 0);
		json.put("rows", null);
		try {
			List<YY_User> personal_user_list = userDao.findByHQL("from YY_User where role = 'business'");
			if(personal_user_list.size()>0){
				JSONObject[] user_json_list = new JSONObject[personal_user_list.size()];
				for(int i=0;i<personal_user_list.size();i++){
					YY_User selected_user = personal_user_list.get(i);
					JSONObject selected_json = new JSONObject();
					selected_json.put("email", selected_user.getEmail());
					selected_json.put("realname", selected_user.getRealname());
					selected_json.put("tel",selected_user.getTel());
					user_json_list[i] = selected_json;
				}
				json.clear();
				json.put("total",personal_user_list.size());
				json.put("rows", user_json_list);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public String getPersonalUserId() {
		// TODO Auto-generated method stub
		try {
			List<YY_User> personal_user_list = userDao.findByHQL("from YY_User where role = 'member'");
			if(personal_user_list.size()>0){
				String user_id = "";
				for(int i=0;i<personal_user_list.size();i++){
					int index  = personal_user_list.get(i).getId();
					user_id += index + ",";
				}
				return user_id.substring(0,user_id.lastIndexOf(","));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getBusinessUserId() {
		// TODO Auto-generated method stub
		try {
			List<YY_User> personal_user_list = userDao.findByHQL("from YY_User where role = 'business'");
			if(personal_user_list.size()>0){
				String user_id = "";
				for(int i=0;i<personal_user_list.size();i++){
					int index  = personal_user_list.get(i).getId();
					user_id += index + ",";
				}
				return user_id.substring(0,user_id.lastIndexOf(","));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean modifyUser(YY_User user) {
		// TODO Auto-generated method stub
		try {
			List<YY_User> user_list = userDao.findByHQL("from YY_User where id = '" + user.getId()+ "'");
			if(user_list.size()>0){
				YY_User original_user = user_list.get(0);
				user.setPassword(original_user.getPassword());
				user.setRole(original_user.getRole());
				userDao.updateUser(user);
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteUser(String selected_id) {
		// TODO Auto-generated method stub
		try {
			List<YY_User> user_list = userDao.findByHQL("from YY_User where id = '" + selected_id + "'");
			if(user_list.size()>0){
				YY_User original_user = user_list.get(0);
				userDao.deleteUser(original_user);
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addUser(YY_User user) {
		// TODO Auto-generated method stub
		userDao.addUser(user);
		return true;
	}
}
