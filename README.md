# graphql-with-spring-boot
A sample application with GraphQL and Spring Boot

# Query
query{
    user(id:1){
    firstName,
    allPostsByUser{
            id,
            mediaType
        }
    }
}

# Mutation
mutation{
    createUser(firstName:"ARUNA", lastName:"SWAIN", dob:"1988-08-01",email:"ARUNA.SWAIN@EY.com"){
        id
    }
}

mutation{
    createPost(mediaType:"video", content:"text.mp4", userID:1){
        id,
        mediaType
    }
}

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


# Priority Order of resolver matching
schema
allPostsByUser:
com.techtalk.graphql.dao.entity.User.allPostsByUser()
com.techtalk.graphql.dao.entity.User.getAllPostsByUser()
com.techtalk.graphql.dao.entity.User.allPostsByUser