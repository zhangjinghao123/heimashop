package com.itheima.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Product;
import com.itheima.service.ProductService;



public class IndexServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductService service =new ProductService();
		//׼��������Ʒ----List<Product>
		List<Product> hotProductList = service.findHotProductList();
		
		//׼��������Ʒ----List<Product>
		List<Product> newProductList = service.findNewProductList();
		
		request.setAttribute("hotProductList",hotProductList);
		request.setAttribute("newProductList",newProductList);
		
		request.getRequestDispatcher("/index.jsp").forward(request,response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
