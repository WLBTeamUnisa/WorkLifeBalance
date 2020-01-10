package it.unisa.wlb.test.utils;

import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.utils.DenyAdmin;
import it.unisa.wlb.utils.Utils;

/**
 * The aims of this class is testing DenyAdmin.class
 * 
 * @author Vincenzo Fabiano
 *
 */
class DenyAdminTest {

	@Mock
	private HttpServletRequest request;
	
	@Mock
	private ServletResponse response;
	
	@Mock
	private HttpSession session;
	
	@Mock
	private RequestDispatcher dispatcher;
	
	@Mock
	private FilterChain chain;
	
	private Admin admin;
	
	private DenyAdmin denyAdmin;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		denyAdmin = new DenyAdmin();
		admin = new Admin();
		admin.setEmail("m.rossi1@wlbadmin.it");
		admin.setName("Marco");
		admin.setSurname("Rossi");
		admin.setPassword(Utils.generatePwd("MarcoRossi1."));
	}

	@Test
	void accessDeniedTest() throws IOException, ServletException {
		String path = "WEB-INF/DenyAccess.jsp";
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(admin);
		when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		denyAdmin.doFilter(request, response, chain);
		verify(request).getRequestDispatcher(captor.capture());
		assertEquals(path, captor.getValue());
	}

}
