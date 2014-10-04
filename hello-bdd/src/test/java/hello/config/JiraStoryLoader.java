package hello.config;

import net.thucydides.plugins.jira.client.JerseyJiraClient;
import net.thucydides.plugins.jira.domain.IssueSummary;

import org.jbehave.core.io.StoryLoader;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JiraStoryLoader implements StoryLoader {
    
    Logger LOG = LoggerFactory.getLogger(JiraStoryLoader.class);
    
    private JerseyJiraClient jira;

    public JiraStoryLoader(JerseyJiraClient jira) {
        this.jira = jira;
    }

    @Override
    public String loadStoryAsText(String storyKey) {
        
        String storyText = "Meta:\n@issue " + storyKey + "\n\n";
        
        LOG.debug("Loading story " + storyText);
        
        try {
            IssueSummary issue = jira.findByKey(storyKey).get();
            
            if (issue.customField("Acceptance Criteria").isPresent()) {
                storyText += issue.customField("Acceptance Criteria").get().asString();
            }
                
            LOG.debug(storyText);
            
        } catch (JSONException e) {
            LOG.error("Cannot load story with key: " + storyKey, e);
        }
        
        return storyText;
    }

}
