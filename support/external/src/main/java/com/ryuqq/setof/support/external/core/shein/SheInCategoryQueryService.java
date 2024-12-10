package com.ryuqq.setof.support.external.core.shein;

import com.ryuqq.setof.support.external.core.ExternalSyncCategoryCommand;
import com.ryuqq.setof.support.external.core.shein.dto.SheInCategoryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SheInCategoryQueryService {

    private final SheInClient sheInClient;

    public SheInCategoryQueryService(SheInClient sheInClient) {
        this.sheInClient = sheInClient;
    }

    public List<ExternalSyncCategoryCommand> fetchSheInCategory() {
        ResponseEntity<SheInResponse<List<SheInCategoryResponse>>> response = sheInClient.fetchCategories();

        if (response.getStatusCode().is2xxSuccessful()) {
            SheInResponse<List<SheInCategoryResponse>> body = response.getBody();
            if (body != null) {
                List<SheInCategoryResponse> categories = body.getInfo().data();
                return categories.stream()
                        .flatMap(category -> flattenCategory(category).stream())
                        .toList();
            }
        }
        return List.of();
    }

    private List<ExternalSyncCategoryCommand> flattenCategory(SheInCategoryResponse category) {
        List<ExternalSyncCategoryCommand> commands = new ArrayList<>();

        commands.add(new ExternalSyncCategoryCommand(
                String.valueOf(category.categoryId()),
                String.valueOf(category.productTypeId()),
                category.categoryName()
        ));

        if (category.children() != null && !category.children().isEmpty()) {
            for (SheInCategoryResponse child : category.children()) {
                commands.addAll(flattenCategory(child));
            }
        }

        return commands;
    }

}
