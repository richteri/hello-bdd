package hello.acceptance;

import hello.config.CustomJIRAConfiguration;
import hello.config.JiraStoryLoader;

import java.util.List;
import java.util.Set;

import net.thucydides.jbehave.ThucydidesJUnitStories;
import net.thucydides.plugins.jira.client.JerseyJiraClient;
import net.thucydides.plugins.jira.domain.IssueSummary;

import org.jbehave.core.configuration.Configuration;
import org.json.JSONException;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class AcceptanceTestSuite extends ThucydidesJUnitStories {

    private final JerseyJiraClient jira;

    private final CustomJIRAConfiguration config;

    public AcceptanceTestSuite() {
        config = new CustomJIRAConfiguration();
        jira = new JerseyJiraClient(
                config.getJiraUrl(),
                config.getJiraUser(),
                config.getJiraPassword(),
                config.getProject(),
                config.getCustomFieldNames());
    }

    /**
     * Overriding default JBehave story loader
     * @see net.thucydides.jbehave.ThucydidesJUnitStories#configuration()
     */
    @Override
    public Configuration configuration() {
        return super.configuration().useStoryLoader(new JiraStoryLoader(config, jira));
    }

    /**
     * Overriding default JBehave story paths
     * @see net.thucydides.jbehave.ThucydidesJUnitStories#storyPaths()
     */
    @Override
    public List<String> storyPaths() {
        Set<String> storyPaths = Sets.newHashSet();
        List<IssueSummary> issues;
        try {
            // find all issues where story is provided
            issues = jira.findByJQL("\"" + config.getAcceptanceFieldName() + "\" is not empty");
            for (IssueSummary issue : issues) {
                storyPaths.add(issue.getKey());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return Lists.newArrayList(storyPaths);
    }
}
