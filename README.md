# graphql-with-spring-boot
A sample application with GraphQL and Spring Boot

# Mutation with input type
mutation{
    updateUser(userMutationInput : {
        id : 1,
        email :"new_email@ey.com",
        phone : "+48 901897321"
    }) {
        firstName,
        email,
        phone
    }
}
# Mutation with Query Variable
mutation($userMutationInput : UserMutationInput){
    updateUser(userMutationInput: $userMutationInput) {
        firstName,
        email,
        phone
    }
}
# Query Value
{
    "userMutationInput" : {
    "id" : 1,
    "email" :"new_email@ey.com",
    "phone" : "+48 801897321"
    }
}
