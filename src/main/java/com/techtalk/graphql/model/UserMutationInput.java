package com.techtalk.graphql.model;

import graphql.schema.GraphQLInputType;
import lombok.Data;

@Data
public class UserMutationInput implements GraphQLInputType {
    @Override
    public String getName() {
        return "userMutationInput";
    }

    private long ID;
    private String email;
}
