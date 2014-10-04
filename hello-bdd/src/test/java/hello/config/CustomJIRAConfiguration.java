package hello.config;

import java.util.List;
import java.util.Set;

import net.thucydides.core.guice.Injectors;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.plugins.jira.requirements.JIRARequirementsConfiguration;
import net.thucydides.plugins.jira.service.SystemPropertiesJIRAConfiguration;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Extends default JIRA config class
 * to process custom field that sores acceptance criteria scenarios
 * @author richteri
 *
 */
public class CustomJIRAConfiguration extends SystemPropertiesJIRAConfiguration {
    
    /**
     * Property key for acceptance criteria custom field name
     */
    public static final String JIRA_ACCEPTANCE_FIELD = "jira.acceptance.field";

    private final EnvironmentVariables environmentVariables;
    
    private final List<String> customFieldNames;
    
    public CustomJIRAConfiguration() {
        super();
        environmentVariables = Injectors.getInjector().getProvider(EnvironmentVariables.class).get();
        customFieldNames = readCustomFieldNames();
    }
    
    /**
     * Building list of custom fields to process
     * jira.custom.field.*; jira.narrative.field; jira.acceptance.field
     * @return list of custom field names
     */
    private List<String> readCustomFieldNames() {
        Set<String> customFieldNames = Sets.newHashSet(
                getAcceptanceFieldName(),
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
    
    /**
     * Get the configured acceptance criteria custom field name
     * @return
     */
    public String getAcceptanceFieldName() {
        return environmentVariables.getProperty(JIRA_ACCEPTANCE_FIELD);
    }
}
