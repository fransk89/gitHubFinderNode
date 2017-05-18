package com.fjcaballero.gitHubFinder.controllers;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fjcaballero.gitHubFinder.service.IGitHubFinderService;

@Controller
public class GitHubFinderController {
	
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
   
    @Autowired
    private IGitHubFinderService gitHubFinderService;
  		
    
    @ResponseBody
	@RequestMapping(value="/getProgrammingLanguages")
	public List<String> getProgrammingLanguages(@RequestBody String request)
			throws IllegalArgumentException, Exception {
	 	
    	logger.info("GitHubFinderController.getProgrammingLanguages - Start"); 
		
	 	List<String> listLanguages = null;
	 	 		
		listLanguages = gitHubFinderService.getGitHubProgrammingLanguages(request);
		
		logger.info(String.format(
				"GitHubFinderController.getProgrammingLanguages - username %s, listlanguages %s.", request, listLanguages)
		);
		
		logger.info("GitHubFinderController.getProgrammingLanguages - Finish"); 
	    
		return listLanguages;
	}
}
