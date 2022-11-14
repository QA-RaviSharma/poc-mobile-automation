package tests;

import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;
import io.cucumber.datatable.TableEntryTransformer;
import models.login.LoginRequest;

import java.util.Locale;
import java.util.Map;

public class DataTableConfig implements TypeRegistryConfigurer {
    @Override
    public void configureTypeRegistry(TypeRegistry registry) {
        registry.defineDataTableType(new DataTableType(LoginRequest.class, new TableEntryTransformer<LoginRequest>() {
            @Override
            public LoginRequest transform(Map<String, String> entry) {
                return LoginRequest.createLoginData(entry);
            }
        }));
    }

    @Override
    public Locale locale() {
        return Locale.ENGLISH;
    }

}