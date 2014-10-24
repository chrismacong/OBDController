package com.fix.obd.web.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fix.obd.util.MessageUtil;
import com.fix.obd.web.model.util.CharacterIterator;
import com.fix.obd.web.service.CharacterService;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

@Controller
@RequestMapping("/character") 
public class CharacterControl {
	private static final Logger logger = LoggerFactory.getLogger(CharacterControl.class);
	@Resource
	private CharacterService characterService;
	public CharacterService getCharacterService() {
		return characterService;
	}
	public void setCharacterService(CharacterService characterService) {
		this.characterService = characterService;
	}
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listResult(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		logger.debug("--------character--------");
		Map<String,Object> model = new HashMap<String,Object>();
		String terminalId = request.getParameter("terminalId");
		terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
		model.put("terminalId", terminalId);
		List<CharacterIterator> list = characterService.getAllCharacterIdAndName();
		String[] parameter_list = characterService.getParameterList(terminalId);
		for(int i=0;i<list.size();i++){
			CharacterIterator c = list.get(i);
			c.setCanswer(parameter_list[i]);
			list.set(i, c);
		}
		model.put("character_list",list);
		return new ModelAndView("CharacterPage",model);
	}
	@RequestMapping(value = "/getParameter", method = RequestMethod.GET)
	public void getParameter(HttpServletRequest request,HttpServletResponse response){
		try {
			logger.debug("-----getParameter-----");
			String terminalId = request.getParameter("terminalId");
			terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
			String characterSentence = request.getParameter("charactersentence");
			response.setCharacterEncoding("utf-8");
			boolean temp = characterService.haveSentParameters(terminalId,characterSentence);
			PrintWriter pw = null;
			pw=response.getWriter();
			JSONObject jsonObject = new JSONObject(); 
			if(temp)
				jsonObject.put("success", "true");
			pw.print(jsonObject.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/setParameter", method = RequestMethod.GET)
	public void setParameter(HttpServletRequest request,HttpServletResponse response){
		try {
			logger.debug("-----setParameter-----");
			String terminalId = request.getParameter("terminalId");
			terminalId = MessageUtil.frontCompWithZore(terminalId, 20);
			String characterSentence = request.getParameter("charactersentence");
			response.setCharacterEncoding("utf-8");
			boolean temp = characterService.haveSetParameters(terminalId,characterSentence);
			PrintWriter pw = null;
			pw=response.getWriter();
			JSONObject jsonObject = new JSONObject(); 
			if(temp)
				jsonObject.put("success", "true");
			pw.print(jsonObject.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
