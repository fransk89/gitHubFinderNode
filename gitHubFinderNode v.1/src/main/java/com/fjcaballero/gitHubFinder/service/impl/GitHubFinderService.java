package com.fjcaballero.gitHubFinder.service.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.stereotype.Service;

import com.fjcaballero.gitHubFinder.service.IGitHubFinderService;
import com.fjcaballero.gitHubFinder.util.Util;


@Service
public class GitHubFinderService implements IGitHubFinderService {
	
	private final Logger logger = Logger.getLogger(GitHubFinderService.class);
	
	private RepositoryService repositoryService;
	
	/**
	 * Initialize a new instance of the GitHubFinderService class.
	 */
	public GitHubFinderService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}
	
	
	/**
	 * Get the user´s favourite progamming languages in GitHub.
	 * 
	 * @param User userName
	 * @param GitHubBean gitHub
	 * 
	 * @return List<String>
	 * @throws IllegalArgumentException, IOException, Exception
	 */
	
	public List<String> getGitHubProgrammingLanguages(String userName) throws IllegalArgumentException  {
		
		logger.info("GitHubFinderService.getGitHubProgrammingLanguages - Start");
		
		List<Repository> listRepositories = null;
		List<String> listLanguages = null;
		
		try {
			
			if (!Util.isEmpty(userName)) {
				
				//	Get the User public repositories in GitHub Api
				logger.info("GitHubFinderService.getGitHubProgrammingLanguages - Getting the repositories - userName: " + userName);
				listRepositories = repositoryService.getRepositories(userName);
			
				//	Check if the list is not empty
				if (!Util.isEmpty(listRepositories)) {
					logger.debug("GitHubFinderService.getGitHubProgrammingLanguages - listRepositories not empty");
					
					//	Sort the list according to the most favourite programming language
					listLanguages = getListLanguagesSorted(listRepositories);
					if (Util.isEmpty(listLanguages)) {
						logger.debug("Repositories without programming languages.");
					}
				} else {
					logger.debug("User with no repositories in GitHub.");
				}
			} else {
				throw new IllegalArgumentException("Invalid input values.");
			}
			
		} catch (IllegalArgumentException e) {
			logger.warn(e.getMessage());
			throw e;
		} catch (IOException e) {
			logger.warn("Username does not exist.");
		} 		
		
		logger.info("GitHubFinderService.getGitHubProgrammingLanguages - Finish");
		
		return listLanguages;
	}
	
	/**
	 * Get the list of programming languages sorted by most favourites.
	 * We consider most favourites order as the number of repositories with 
	 * the same programming language
	 * 
	 * @param List<Repository> listRepositories
	 * 
	 * @return List<String>
	 */
	
	private List<String> getListLanguagesSorted (List<Repository> listRepositories) {
		
		logger.info("GitHubFinderService.getListLanguagesSorted - Start");
		
		HashMap<String, Integer> hashNotSorted = null;
		HashMap<String, Integer> hashSorted = null;
		List<String> languagesSorted = null;
				 
		try {
			
			if (!Util.isEmpty(listRepositories)) {
				
				//	Create a HashMap using all repositories:
				//	key (Programming Language) - value (Number of times in the repositories)
				//	Example: Java-3
				Iterator<Repository> it= listRepositories.iterator();
				hashNotSorted = new HashMap();
				
				while(it.hasNext()) {
					//	We get each repository
					Repository repository = it.next();
					//	We check if it does not exist in the HashMap
					if (hashNotSorted.containsKey(repository.getLanguage())) {
						//	If it already exists we add to the HashMap with an incremental value
						Integer val = hashNotSorted.get(repository.getLanguage());
						hashNotSorted.put(repository.getLanguage(), val + 1);
					} else {
						//	If it does not exist we add to the HashMap
						hashNotSorted.put(repository.getLanguage(), 1);
					}
				}
				
				logger.debug("GitHubFinderService.getListLanguagesSorted - hashNotSorted: " + hashNotSorted.toString());
				
				if (!Util.isEmpty(hashNotSorted)) {
					
					//	We sort HashMap according to the Value
					hashSorted = (HashMap<String, Integer>) Util.sortHashMap(hashNotSorted);
					logger.debug("GitHubFinderService.getListLanguagesSorted - hashSorted: " + hashSorted.toString());
					
					// We convert HashMap to List
					if (!hashSorted.isEmpty()) {
						languagesSorted = new ArrayList();
					    for (Map.Entry<String, Integer> entry : hashSorted.entrySet())
					    {
					    	languagesSorted.add((String) entry.getKey());
					    }
					}
				}
				
				logger.debug("GitHubFinderService.getListLanguagesSorted - languagesSorted: " + languagesSorted.toString());
			}
		} catch (Exception e) {
			logger.warn("Unexpected error." + e.getMessage());
		}
		
		logger.info("GitHubFinderService.getListLanguagesSorted - Finish");
		
		return languagesSorted;
	}
}
