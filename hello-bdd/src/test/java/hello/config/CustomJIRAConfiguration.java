package hello.config;

import java.util.List;
import java.util.Set;

import net.thucydides.core.guice.Injectors;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.plugins.jira.requirements.JIRARequirementsConfiguration;
import net.thucydides.plugins.jira.service.SystemPropertiesJIRAConfiguration;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class CustomJIRAConfiguration extends SystemPropertiesJIRAConfiguration {
    
    public static final String JIRA_STORY_FIELD = "jira.story.field";

    private final EnvironmentVariables environmentVariables;
    
    private final List<String> customFieldNames;
    
    public CustomJIRAConfiguration() {
        super();
        environmentVariables = Injectors.getInjector().getProvider(EnvironmentVariables.class).get();
        customFieldNames = readCustomFieldNames();
    }
    
    private List<String> readCustomFieldNames() {
        Set<String> customFieldNames = Sets.newHashSet(
                getStoryFieldName(),
                environmentVariables.getProperty(JIRARequirementsConfiguration.JIRA_CUSTOM_NARRATIVE_FIELD.getName()));
        
        int customFieldIndex = 1;
        String customFieldName = environmentVariables.getProperty(JIRARequirementsConfiguration.JIRA_CUSTOM_FIELD.getName() + "." + customFieldIndex++);
        while (customFieldName!=null) {
            customFieldNames.add(customFieldName);
        }
        customFieldNames.remove(null);
        return Lists.newArrayList(customFieldNames);
    }
    
    public List<String> getCustomFieldNames() {
        return customFieldNames;
    }
    
    public String getStoryFieldName() {
        return environmentVariables.getProperty(JIRA_STORY_FIELD);
    }
}
