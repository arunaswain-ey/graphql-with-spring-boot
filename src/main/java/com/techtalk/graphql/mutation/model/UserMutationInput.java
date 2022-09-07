package com.techtalk.graphql.mutation.model;

import graphql.schema.GraphQLInputType;
import lombok.Data;

@Data
public class UserMutationInput implements GraphQLInputType {
    @Override
    public String getName() {
        return "userMutationInput";
    }

    private int ID;
    private String email;
    private String phone;
}
