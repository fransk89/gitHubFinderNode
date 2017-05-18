package com.fjcaballero.gitHubFinder.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.junit.Before;
import org.junit.Test;

public class GitHubFinderServiceTests {
	
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    
    private static final String USER_NO_REPOSITORIES = "norepositoryuser";
    private static final String USER_REPOSITORY_NO_LANGUAGES = "usernolanguages";
    private static final String USER_NO_EXIST = "usernoexistusernoexist";
    private static final String TEST_USER = "fransk89";
    private static final String TEST_PROGRAMMING_LANGUAGE = "JavaScript";
    
    private RepositoryService repositoryService;
    private GitHubFinderService gitHubFinderService;
    

    @Before
    public void setUp() throws Exception {
    	repositoryService = new RepositoryService();
    	gitHubFinderService = new GitHubFinderService(repositoryService);
    }
	
	@Test
    public void testGetGitHubProgrammingLanguages() throws  IOException, IllegalArgumentException, Exception {		
		
		List<String> listLanguages = null;
		String username = null;
		
		try {
			gitHubFinderService.getGitHubProgrammingLanguages(username);
		} catch (IllegalArgumentException e) {
			logger.warn("Expected");
		} catch (Exception e) {
			logger.warn("No expected");
			throw e;
		}
		
		try {
			username = "";
			gitHubFinderService.getGitHubProgrammingLanguages(username);
		} catch (IllegalArgumentException e) {
			logger.warn("Expected");
		} catch (Exception e) {
			logger.warn("No Expected");
			throw e;
		}
				
		username = USER_NO_EXIST;
		assertNull(gitHubFinderService.getGitHubProgrammingLanguages(username));
		
		username = USER_NO_REPOSITORIES;
		assertNull(gitHubFinderService.getGitHubProgrammingLanguages(username));
		
		username = USER_REPOSITORY_NO_LANGUAGES;
		listLanguages = gitHubFinderService.getGitHubProgrammingLanguages(username);
		assertNull(listLanguages.get(0));	
		
		username = TEST_USER;
		listLanguages = gitHubFinderService.getGitHubProgrammingLanguages(username);
		assertEquals(TEST_PROGRAMMING_LANGUAGE, (String)listLanguages.get(0));
	}
}
