package hello.config;

import net.thucydides.plugins.jira.client.JerseyJiraClient;
import net.thucydides.plugins.jira.domain.IssueSummary;

import org.jbehave.core.io.StoryLoader;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JiraStoryLoader implements StoryLoader {
    
    Logger LOG = LoggerFactory.getLogger(JiraStoryLoader.class);
   
    private final String acceptanceCriteriaFieldName;
    
    /**
     * REST API client
     */
    private JerseyJiraClient jira;

    public JiraStoryLoader(CustomJIRAConfiguration config, JerseyJiraClient jira) {
        this.jira = jira;
        acceptanceCriteriaFieldName = config.getAcceptanceFieldName();
    }

    /**
     * Reading story from JIRA
     */
    @Override
    public String loadStoryAsText(String storyKey) {
        
        // Add JIRA issue key to the acceptance criteria scenario
        String storyText = "Meta:\n@issue " + storyKey + "\n\n";
        
        LOG.debug("Loading story " + storyText);
        
        try {
            IssueSummary issue = jira.findByKey(storyKey).get();
            
            if (issue.customField(acceptanceCriteriaFieldName).isPresent()) {
                storyText += issue.customField(acceptanceCriteriaFieldName).get().asString();
            }
                
            LOG.debug(storyText);
            
        } catch (JSONException e) {
            LOG.error("Cannot load story with key: " + storyKey, e);
        }
        
        return storyText;
    }

}
