package com.itheima.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.utils.CommonsUtils;





public class RegisterServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		//获得表单的数据
		Map<String,String[]> properties =request.getParameterMap();
		User user =new User();
		try {
			
			ConvertUtils.register(new Converter() {
				
				@Override
				public Object convert(Class clazz, Object value) {
					SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd");
					Date parse=null;
					try {
						parse=format.parse(value.toString());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}
			}, Date.class);
			BeanUtils.populate(user, properties);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//页面上没有的要封装起来
		user.setUid(CommonsUtils.getUUID());
		user.setTelephone(null);
		user.setState(0);
		user.setCode(CommonsUtils.getUUID());
		
		//将user传递给service层
		UserService service = new UserService();
		boolean isRegisterSuccess=service.regist(user);
		
		//判断是否注册成功
		if(isRegisterSuccess) {
			response.sendRedirect(request.getContextPath()+"/registerSuccess.jsp");
		}else {
			response.sendRedirect(request.getContextPath()+"/registerFail.jsp");
		}
		
	}
		
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
